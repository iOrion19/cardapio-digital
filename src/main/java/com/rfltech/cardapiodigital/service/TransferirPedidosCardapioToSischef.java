package com.rfltech.cardapiodigital.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rfltech.cardapiodigital.client.CardapioDigitalClient;
import com.rfltech.cardapiodigital.client.SischefClient;
import com.rfltech.cardapiodigital.client.messages.requests.NovoPedidoSischef;
import com.rfltech.cardapiodigital.client.messages.response.OrderCardapioDigital;
import com.rfltech.cardapiodigital.client.messages.response.PedidoResponseCardapioDigital;
import com.rfltech.cardapiodigital.mapper.CardapioSischefMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransferirPedidosCardapioToSischef {

    private static final Logger logger = LoggerFactory.getLogger(TransferirPedidosCardapioToSischef.class);

    private final CardapioDigitalClient cardapioDigitalClient;
    private final SischefClient sischefClient;

    private final ObjectMapper objectMapper;
    private final CardapioSischefMapper mapper;

    public TransferirPedidosCardapioToSischef(CardapioDigitalClient cardapioDigitalClient, SischefClient sischefClient, ObjectMapper objectMapper) {
        this.cardapioDigitalClient = cardapioDigitalClient;
        this.sischefClient = sischefClient;
        this.objectMapper = objectMapper;
        this.mapper = CardapioSischefMapper.INSTANCE;
    }

    public void integrarPedidoToSischef(PedidoResponseCardapioDigital pedido) {
        atualizarResgastes(pedido);
        NovoPedidoSischef sischefOrder = mapper.toSischefOrder(pedido);
        sischefOrder.setPessoa(mapper.toSischefPessoa(pedido.getUsuario(), pedido.getEndereco()));
        ResponseEntity<String> stringResponseEntity = sischefClient.criarPedido(sischefOrder);
        logger.info(stringResponseEntity.getBody());
    }


    public void atualizarResgastes(PedidoResponseCardapioDigital cardapioDigitalOrder) {
        BigDecimal valorDesconto = cardapioDigitalOrder.getValorDesconto();
        cardapioDigitalOrder.getItens().forEach(itemCardapio -> {
            if (itemCardapio.isResgatado()) {
                itemCardapio.setNome(itemCardapio.getNome() + " (Resgatado)");
                valorDesconto.add(itemCardapio.getValor());
            }
        });
        cardapioDigitalOrder.setValorDesconto(valorDesconto);

    }


}
