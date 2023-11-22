package com.rfltech.cardapiodigital.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rfltech.cardapiodigital.client.CardapioDigitalClient;
import com.rfltech.cardapiodigital.client.SischefClient;
import com.rfltech.cardapiodigital.client.messages.requests.NovoPedidoSischef;
import com.rfltech.cardapiodigital.client.messages.response.PedidoResponseCardapioDigital;
import com.rfltech.cardapiodigital.enums.MeioPagamentoEnum;
import com.rfltech.cardapiodigital.mapper.CardapioSischefMapper;
import com.rfltech.cardapiodigital.model.PedidoStatus;
import com.rfltech.cardapiodigital.model.enumerator.StatusEnum;
import com.rfltech.cardapiodigital.repository.PedidoStatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class TransferirPedidosCardapioToSischef {

    private static final Logger logger = LoggerFactory.getLogger(TransferirPedidosCardapioToSischef.class);
    private final CardapioDigitalClient cardapioDigitalClient;
    private final SischefClient sischefClient;
    private final ObjectMapper objectMapper;
    private final CardapioSischefMapper mapper;
    private final PedidoStatusRepository repository;

    public TransferirPedidosCardapioToSischef(CardapioDigitalClient cardapioDigitalClient, SischefClient sischefClient, ObjectMapper objectMapper, PedidoStatusRepository repository) {
        this.cardapioDigitalClient = cardapioDigitalClient;
        this.sischefClient = sischefClient;
        this.objectMapper = objectMapper;
        this.repository = repository;
        this.mapper = CardapioSischefMapper.INSTANCE;
    }

    public void integrarPedidoToSischef(PedidoResponseCardapioDigital pedido) throws JsonProcessingException {
        NovoPedidoSischef sischefOrder = this.mapper.toSischefOrder(pedido);
        this.atualizarDescricao(pedido, sischefOrder);
        this.atualizarPagamentoOnline(pedido, sischefOrder);
        this.calcularValorTotal(sischefOrder);
        sischefOrder.setPessoa(this.mapper.toSischefPessoa(pedido.getUsuario(), pedido.getEndereco()));
        ResponseEntity<String> stringResponseEntity = this.sischefClient.criarPedido(sischefOrder);
        logger.info("=========== REQUEST ==================");
        logger.info(this.objectMapper.writeValueAsString(sischefOrder));
        logger.info("=========== FIM REQUEST ==================");
        logger.info(stringResponseEntity.getBody());
        PedidoStatus pedidoStatus = new PedidoStatus(pedido.getRef(), StatusEnum.PENDENTE.name(), this.extrairIdUnicoIntegracao((String) stringResponseEntity.getBody()));
        this.repository.save(pedidoStatus);
    }

    private void atualizarResgastes(PedidoResponseCardapioDigital cardapioDigitalOrder) {
        cardapioDigitalOrder.getItens().forEach((itemCardapio) -> {
            if (itemCardapio.isResgatado()) {
                itemCardapio.setNome(itemCardapio.getNome() + " (Resgatado)");
                List<BigDecimal> itensCardapio = (List) itemCardapio.getComplementos().stream().map((x) -> {
                    return x.getValor();
                }).collect(Collectors.toList());
                cardapioDigitalOrder.setValorDesconto(cardapioDigitalOrder.getValorDesconto().add(itemCardapio.getValor()).add((BigDecimal) itensCardapio.stream().reduce(BigDecimal.ZERO, BigDecimal::add)));
            }

        });
    }

    private Long extrairIdUnicoIntegracao(String response) {
        String idUnicoIntegracao = null;
        Pattern pattern = Pattern.compile("#(\\d+)\\s");
        Matcher matcher = pattern.matcher(response);
        if (matcher.find()) {
            idUnicoIntegracao = matcher.group(1);
        }

        return StringUtils.hasText(idUnicoIntegracao) ? Long.valueOf(idUnicoIntegracao) : null;
    }

    private void calcularValorTotal(NovoPedidoSischef sischefOrder) {
        BigDecimal valorTotal = BigDecimal.ZERO;
        sischefOrder.getItens().forEach((item) -> {
            item.setValorTotal(item.getValorTotal().multiply(BigDecimal.valueOf(item.getQuantidade())));
            item.getSubItens().stream().forEach((subItem) -> {
                subItem.setValorTotal(subItem.getValorTotal().multiply(BigDecimal.valueOf(item.getQuantidade())));
            });
        });
    }

    private void atualizarDescricao(PedidoResponseCardapioDigital response, NovoPedidoSischef sischefOrder) {
        StringBuilder descricao = new StringBuilder();
        if (StringUtils.hasText(response.getObs())) {
            descricao.append(response.getObs() + " ");
        }

        if (StringUtils.hasText(response.getCpfCnpj())) {
            descricao.append("Nota fiscal: " + response.getCpfCnpj());
        }

        if (StringUtils.hasText(response.getCodigoDesconto())) {
            descricao.append("\n Código desconto: " + response.getCodigoDesconto());
        }

        if (Objects.nonNull(response.getMensagem()) && Objects.nonNull(response.getMensagem().getCampos())) {
            PedidoResponseCardapioDigital.Campos campos = response.getMensagem().getCampos();
            descricao.append("\n Mensagem: ").append(campos.getMensagem()).append("\n");
            if (campos.getAnonimo().equals("0")) {
                descricao.append("\n De: ").append(campos.getDeText()).append("\n");
            }

            descricao.append("\n Para: ").append(campos.getPara()).append("\n");
            descricao.append("\n Nome Destinatário: ").append(campos.getNomeDestinatario()).append("\n");
            descricao.append("\n Telefone Destinatário: ").append(campos.getTelefoneDestinatario());
        }

        sischefOrder.setDescricao(descricao.toString());
    }

    private void atualizarPagamentoOnline(PedidoResponseCardapioDigital response, NovoPedidoSischef sischefOrder) {
        if (sischefOrder.getPagamentos().isEmpty()) {
            NovoPedidoSischef.Pagamento pagamentoOnline = new NovoPedidoSischef.Pagamento();
            pagamentoOnline.setValor(sischefOrder.getValorTotal().add(sischefOrder.getValorTaxaEntrega()).subtract(sischefOrder.getValorDesconto()));
            NovoPedidoSischef.Pagamento.FormaPagamento formaPagamento = new NovoPedidoSischef.Pagamento.FormaPagamento();
            formaPagamento.setDescricao("Pagamento Online");
            formaPagamento.setTipo(response.getMeioPagamento());
            formaPagamento.setPagamento("ONLINE");
            pagamentoOnline.setFormaPagamento(formaPagamento);
            sischefOrder.getPagamentos().add(pagamentoOnline);
            return;
        }
        String codigoCardapio = MeioPagamentoEnum.obterCodigoCardapio(response.getIdMeioPagamento());
        sischefOrder.getPagamentos().get(0).getFormaPagamento().setCodigoExterno(codigoCardapio);
    }
}