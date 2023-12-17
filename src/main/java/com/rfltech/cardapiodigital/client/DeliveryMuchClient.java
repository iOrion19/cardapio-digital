package com.rfltech.cardapiodigital.client;

import com.rfltech.cardapiodigital.client.messages.requests.DeliveryMuchAuthRequest;
import com.rfltech.cardapiodigital.client.messages.response.DeliveryMuchAuthResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "delivery-much", url = "${delivery-much.autenticacao.host}")
public interface DeliveryMuchClient {

    @PostMapping
    DeliveryMuchAuthResponse autenticar(@RequestBody DeliveryMuchAuthRequest DeliveryMuchAuthRequest);
}