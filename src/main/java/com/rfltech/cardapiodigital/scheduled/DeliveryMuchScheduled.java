package com.rfltech.cardapiodigital.scheduled;

import com.rfltech.cardapiodigital.client.CardapioDigitalClient;
import com.rfltech.cardapiodigital.client.DeliveryMuchAuthClient;
import com.rfltech.cardapiodigital.client.DeliveryMuchClient;
import com.rfltech.cardapiodigital.client.messages.requests.CriarPedidoCardapio;
import com.rfltech.cardapiodigital.client.messages.requests.DeliveryMuchAuthRequest;
import com.rfltech.cardapiodigital.client.messages.response.deliverymuch.DeliveryMuchAuthResponse;
import com.rfltech.cardapiodigital.client.messages.response.deliverymuch.DeliveryMuchOrdensResponse;
import com.rfltech.cardapiodigital.config.properties.DeliveryMuchProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class DeliveryMuchScheduled {

    @Autowired
    private DeliveryMuchAuthClient deliveryMuchAuthClient;

    @Autowired
    private DeliveryMuchClient deliveryMuchClient;

    @Autowired
    private CardapioDigitalClient cardapioDigitalClient;

    @Autowired
    private DeliveryMuchProperties deliveryMuchProperties;

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.SECONDS)
    public void buscarOrdens() {
        DeliveryMuchAuthResponse authResponse = deliveryMuchAuthClient.autenticar(deliveryMuchAuthRequest());
        DeliveryMuchOrdensResponse apiResponse = deliveryMuchClient.ordens("Bearer ".concat(authResponse.getTokenAcesso()));

        for (DeliveryMuchOrdensResponse.Docs documento : apiResponse.getDocs()) {
            String responseCardapio = cardapioDigitalClient.criarPedido(criarPedidoCardapio(documento));
            System.out.println(responseCardapio);
        }
    }

    public DeliveryMuchAuthRequest deliveryMuchAuthRequest() {
        return DeliveryMuchAuthRequest.builder()
                .clientSecret(deliveryMuchProperties.getAutenticacao().getClientSecret())
                .clientId(deliveryMuchProperties.getAutenticacao().getClientId())
                .userName(deliveryMuchProperties.getAutenticacao().getUserName())
                .password(deliveryMuchProperties.getAutenticacao().getPassWord())
                .grantType(deliveryMuchProperties.getAutenticacao().getGrantType()).build();
    }


    public CriarPedidoCardapio criarPedidoCardapio(DeliveryMuchOrdensResponse.Docs documento) {
        CriarPedidoCardapio criarPedidoCardapio = new CriarPedidoCardapio();
        CriarPedidoCardapio.Pedido pedido = new CriarPedidoCardapio.Pedido();

        criarPedidoCardapio.setProvedor(deliveryMuchProperties.getApi().getProvedor());
        criarPedidoCardapio.setIdExterno(deliveryMuchProperties.getApi().getIdExterno());

        criarPedidoCardapio.setDados(new CriarPedidoCardapio.Dados());

        criarPedidoCardapio.getDados().setEnderecoEntrega(
                criarEnderecoEntrega(documento.getDeliveryForm().getEndereco()));

        criarPedidoCardapio.getDados().setUsuario(criarUsuario(documento.getUsuario()));

        CriarPedidoCardapio.Pagamento pagamento = criarFormaPagamentoCardapio(documento.getPagamento());
        criarPedidoCardapio.getDados().setPagamento(List.of(pagamento));

        String metodoPagamento = documento.getDeliveryForm().getMetodoPagamento().toLowerCase();
        pedido.setMeioEntrega(obterMeioEntregaCardapio(metodoPagamento));

        criarPedidoCardapio.getDados().setValoresEntrega(criarValoreaEntrega(documento.getPagamento()));

        List<CriarPedidoCardapio.Item> itens = new ArrayList<>();

        for (DeliveryMuchOrdensResponse.Produtos produto : documento.getProdutos()) {

            CriarPedidoCardapio.Item itemCardapio = criarCardapioItem(produto);

            List<CriarPedidoCardapio.Complemento> complementos = new ArrayList<>();

            for (DeliveryMuchOrdensResponse.Groups group : produto.getGroups()) {

                for (DeliveryMuchOrdensResponse.SubGroups subGrupo : group.getSubGroups()) {

                    for (DeliveryMuchOrdensResponse.Item item : subGrupo.getItens()) {
                        complementos.add(criarComplemento(item));
                    }
                }
            }
            itemCardapio.setComplementos(complementos);
            itens.add(itemCardapio);
        }

        pedido.setItens(itens);
        criarPedidoCardapio.getDados().setPedido(pedido);

        return criarPedidoCardapio;
    }

    private CriarPedidoCardapio.ValoresEntrega criarValoreaEntrega(DeliveryMuchOrdensResponse.Pagamento pagamento) {
        CriarPedidoCardapio.ValoresEntrega valoresEntrega = new CriarPedidoCardapio.ValoresEntrega();
        valoresEntrega.setValorEntrega(pagamento.getEntrega());

        return valoresEntrega;
    }

    private CriarPedidoCardapio.Usuario criarUsuario(DeliveryMuchOrdensResponse.Usuario usuario) {
        CriarPedidoCardapio.Usuario cardapioUsuario = new CriarPedidoCardapio.Usuario();

        cardapioUsuario.setNome(usuario.getNome());
        cardapioUsuario.setTelefone(usuario.getTelefone());

        return cardapioUsuario;
    }

    private CriarPedidoCardapio.EnderecoEntrega criarEnderecoEntrega(DeliveryMuchOrdensResponse.Endereco endereco) {
        CriarPedidoCardapio.EnderecoEntrega enderecoEntrega = new CriarPedidoCardapio.EnderecoEntrega();

        enderecoEntrega.setLogradouro(endereco.getRua());
        enderecoEntrega.setNumero(endereco.getNumero());
        enderecoEntrega.setBairro(endereco.getBairro());
        enderecoEntrega.setCidade(endereco.getCidade());
        enderecoEntrega.setUf(endereco.getUf());
        enderecoEntrega.setCep(endereco.getCep());
        enderecoEntrega.setComplemento(endereco.getComplemento());
        enderecoEntrega.setReferencia(endereco.getReferencia());

        String coordenadas = endereco.getLatitude().concat(", ").concat(endereco.getLongitude());
        enderecoEntrega.setCoordenadas(coordenadas);

        return enderecoEntrega;
    }

    private static CriarPedidoCardapio.Item criarCardapioItem(DeliveryMuchOrdensResponse.Produtos produto) {
        CriarPedidoCardapio.Item cardapioItem = new CriarPedidoCardapio.Item();

        cardapioItem.setNome(produto.getNome());
        cardapioItem.setQuantidade(produto.getQuantidade());
        cardapioItem.setValor(produto.getOpcoes().getTamanho().getPreco());
        cardapioItem.setDescricao(produto.getDescricao());
        cardapioItem.setIdExterno(String.valueOf(produto.getId()));
        cardapioItem.setIdAlloy("");

        return cardapioItem;
    }

    private static CriarPedidoCardapio.Complemento criarComplemento(DeliveryMuchOrdensResponse.Item item) {
        CriarPedidoCardapio.Complemento complemento = new CriarPedidoCardapio.Complemento();
        complemento.setNome(item.getNome());
        complemento.setValor(item.getPreco());
        complemento.setDescricao(item.getDescricao());
        complemento.setIdExterno(String.valueOf(item.getId()));
        complemento.setIdAlloy("");
        complemento.setQuantidade(1);

        return complemento;
    }

    private int getFormaPagamentoCardapio(String formaPagamento) {
        switch (formaPagamento.toLowerCase()) {
            case "money":
                return 6;
            case "machine":
                return 28;
            case "online":
                return 35;
            default:
                return 0;
        }
    }

    private CriarPedidoCardapio.Pagamento criarFormaPagamentoCardapio(DeliveryMuchOrdensResponse.Pagamento pagamento) {
        CriarPedidoCardapio.Pagamento pagamentoCardapio = new CriarPedidoCardapio.Pagamento();

        String formaPagamento = pagamento.getPagamentoForma().getMetodo();
        pagamentoCardapio.setFormaPagamento(getFormaPagamentoCardapio(formaPagamento));
        pagamentoCardapio.setTotal(pagamento.getTotal());

        return pagamentoCardapio;
    }

    private String obterMeioEntregaCardapio(String metodoPagamento) {
        return metodoPagamento.equals("pickup") ? "retirada" : "delivery";
    }
}