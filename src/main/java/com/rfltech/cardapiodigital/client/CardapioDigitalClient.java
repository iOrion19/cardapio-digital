package com.rfltech.cardapiodigital.client;

import com.rfltech.cardapiodigital.client.messages.requests.CriarPedidoCardapio;
import com.rfltech.cardapiodigital.client.messages.response.OrderCardapioDigital;
import com.rfltech.cardapiodigital.client.params.QueryParamOrder;
import com.rfltech.cardapiodigital.config.CardapioDigitalFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cardapio-digital", url = "${cardapio-digital.host}", configuration = CardapioDigitalFeignConfig.class)
public interface CardapioDigitalClient {

    @GetMapping(
            name = "getOrders",
            path = {"/delivery/getorders"}
    )
    ResponseEntity<OrderCardapioDigital> getOrders(@SpringQueryMap QueryParamOrder var1);

    @PostMapping(
            name = "updateOrderNextStatus",
            path = {"delivery/changestatus"}
    )
    ResponseEntity<String> updateNextStatusOrder(@RequestParam(name = "pedido_ref") String var1);

    @PostMapping(
            name = "cancelOrder",
            path = {"delivery/cancelaPedido"}
    )
    ResponseEntity<String> cancelOrder(@RequestParam(name = "pedido_ref") String var1);

    @PostMapping(value = "/delivery/createOrder")
    String criarPedido(@RequestBody CriarPedidoCardapio criarPedidoCardapio);
}