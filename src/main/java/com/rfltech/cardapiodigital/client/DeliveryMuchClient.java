package com.rfltech.cardapiodigital.client;

import com.rfltech.cardapiodigital.client.messages.response.deliverymuch.DeliveryMuchOrdensResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "delivery-much", url = "${delivery-much.api.host}")
public interface DeliveryMuchClient {

    @GetMapping(value = "/orders")
    DeliveryMuchOrdensResponse ordens(@RequestHeader(value = "Authorization") String authorization);
}