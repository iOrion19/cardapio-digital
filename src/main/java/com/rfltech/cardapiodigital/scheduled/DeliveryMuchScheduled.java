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

        String orderId = cardapioDigitalClient.criarPedido(criarPedidoCardapio(apiResponse));
        System.out.println(orderId);
    }

    public DeliveryMuchAuthRequest deliveryMuchAuthRequest() {
        return DeliveryMuchAuthRequest.builder()
                .clientSecret(deliveryMuchProperties.getAutenticacao().getClientSecret())
                .clientId(deliveryMuchProperties.getAutenticacao().getClientId())
                .userName(deliveryMuchProperties.getAutenticacao().getUserName())
                .password(deliveryMuchProperties.getAutenticacao().getPassWord())
                .grantType(deliveryMuchProperties.getAutenticacao().getGrantType()).build();
    }


    public CriarPedidoCardapio criarPedidoCardapio(DeliveryMuchOrdensResponse apiResponse) {
        CriarPedidoCardapio criarPedidoCardapio = new CriarPedidoCardapio();

        criarPedidoCardapio.setProvedor(deliveryMuchProperties.getApi().getProvedor());
        criarPedidoCardapio.setIdExterno(deliveryMuchProperties.getApi().getIdExterno());
        criarPedidoCardapio.setDados(new CriarPedidoCardapio.Dados());
        CriarPedidoCardapio.Pedido pedido = new CriarPedidoCardapio.Pedido();

        for (DeliveryMuchOrdensResponse.Docs documento : apiResponse.getDocs()) {

            criarPedidoCardapio.getDados().setEnderecoEntrega(
                    criarEnderecoEntrega(documento.getDeliveryForm().getEndereco()));

            criarPedidoCardapio.getDados().setUsuario(criarUsuario(documento.getUsuario()));

            String meioEntrega = documento.getDeliveryForm().getMetodoPagamento()
                    .equalsIgnoreCase("pickup ") ? "retirada" : "delivery";

            pedido.setMeioEntrega(meioEntrega);

            for (DeliveryMuchOrdensResponse.Produtos produto : documento.getProdutos()) {

                List<CriarPedidoCardapio.Complemento> complementos = new ArrayList<>();

                for (DeliveryMuchOrdensResponse.Groups group : produto.getGroups()) {

                    for (DeliveryMuchOrdensResponse.SubGroups subGrupo : group.getSubGroups()) {

                        for (DeliveryMuchOrdensResponse.Item item : subGrupo.getItens()) {
                            complementos.add(criarComplemento(item));
                        }
                    }
                    break;
                }

                CriarPedidoCardapio.Item item = criarCardapioItem(produto);
                item.setComplementos(complementos);
                pedido.setItens(List.of(item));

                break;
            }
        }

        criarPedidoCardapio.getDados().setPedido(pedido);

        return criarPedidoCardapio;
    }

    private CriarPedidoCardapio.Usuario criarUsuario(DeliveryMuchOrdensResponse.Usuario usuario) {
        CriarPedidoCardapio.Usuario cardapioUsuario = new CriarPedidoCardapio.Usuario();

        cardapioUsuario.setNome(usuario.getNome());
        cardapioUsuario.setTelefone(usuario.getTelefone());

//        cardapioUsuario.setEmail("");
//        cardapioUsuario.setCpf("");
//        cardapioUsuario.setGenero("");
//        cardapioUsuario.setDataNascimento("");
//        cardapioUsuario.setTelefoneCodigoPais("");

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
        cardapioItem.setValor(produto.getValorTotal());
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
}