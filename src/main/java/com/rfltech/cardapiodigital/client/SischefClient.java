package com.rfltech.cardapiodigital.client;

import com.rfltech.cardapiodigital.client.messages.requests.NovoPedidoSischef;
import com.rfltech.cardapiodigital.config.SischefFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "sischef", url = "${sischef.host}", configuration = SischefFeignConfig.class)
public interface SischefClient {

    @PostMapping(name = "inserirPedido")
    ResponseEntity<String> criarPedido(@RequestBody NovoPedidoSischef request);

}
