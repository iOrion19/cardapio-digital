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

import java.util.Arrays;
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

        cardapioDigitalClient.criarPedido(criarPedidoCardapio(apiResponse));
    }

    public CriarPedidoCardapio criarPedidoCardapio(DeliveryMuchOrdensResponse apiResponse) {
        CriarPedidoCardapio criarPedidoCardapio = new CriarPedidoCardapio();

        CriarPedidoCardapio.Dados dados = new CriarPedidoCardapio.Dados();
        CriarPedidoCardapio.Pedido pedido = new CriarPedidoCardapio.Pedido();
        CriarPedidoCardapio.Item item = new CriarPedidoCardapio.Item();
        item.setNome(apiResponse.getDocs().get(0).getProdutos().get(0).getNome());
        pedido.setItens(Arrays.asList(new CriarPedidoCardapio.Item()));

        dados.setPedido(pedido);
        return criarPedidoCardapio;
    }

    public DeliveryMuchAuthRequest deliveryMuchAuthRequest() {
        return DeliveryMuchAuthRequest.builder()
                .clientSecret(deliveryMuchProperties.getAutenticacao().getClientSecret())
                .clientId(deliveryMuchProperties.getAutenticacao().getClientId())
                .userName(deliveryMuchProperties.getAutenticacao().getUserName())
                .password(deliveryMuchProperties.getAutenticacao().getPassWord())
                .grantType(deliveryMuchProperties.getAutenticacao().getGrantType()).build();
    }
}