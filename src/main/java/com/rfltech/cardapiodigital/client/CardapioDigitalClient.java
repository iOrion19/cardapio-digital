package com.rfltech.cardapiodigital.client;

import com.rfltech.cardapiodigital.client.messages.response.OrderCardapioDigital;
import com.rfltech.cardapiodigital.client.params.QueryParamOrder;
import com.rfltech.cardapiodigital.config.CardapioDigitalFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "cardapio-digital", url = "${cardapio-digital.host}", configuration = CardapioDigitalFeignConfig.class)
public interface CardapioDigitalClient {

    @GetMapping(name = "getOrders", path = "/delivery/getorders")
    ResponseEntity<OrderCardapioDigital> getOrders(@SpringQueryMap QueryParamOrder queryParms);
}
