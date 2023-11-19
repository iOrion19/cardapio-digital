package com.rfltech.cardapiodigital.client;

import com.rfltech.cardapiodigital.client.messages.requests.NovoPedidoSischef;
import com.rfltech.cardapiodigital.client.messages.response.RetornoStatusSischef;
import com.rfltech.cardapiodigital.config.SischefFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "sischef", url = "${sischef.host}", configuration = SischefFeignConfig.class)
public interface SischefClient {
    @PostMapping(
            name = "inserirPedido"
    )
    ResponseEntity<String> criarPedido(@RequestBody NovoPedidoSischef var1);

    @GetMapping(
            name = "procurarPedido",
            path = {"/idUnicoIntegracao/{idUnicoIntegracao}"}
    )
    ResponseEntity<RetornoStatusSischef> buscarPedido(@PathVariable("idUnicoIntegracao") String var1);

    @GetMapping(
            name = "detalharPedido",
            path = {"/detalharPedido/{idRef}"}
    )
    ResponseEntity<RetornoStatusSischef> detalharPedido(@PathVariable("idRef") String var1);

}
