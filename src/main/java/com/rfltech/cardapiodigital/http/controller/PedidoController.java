package com.rfltech.cardapiodigital.http.controller;

import com.rfltech.cardapiodigital.http.data.DefaultResponse;
import com.rfltech.cardapiodigital.http.data.WebhookResponsePedido;
import com.rfltech.cardapiodigital.service.TransferirPedidosCardapioToSischef;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    private final TransferirPedidosCardapioToSischef transferirPedidosCardapioToSischef;

    public PedidoController(TransferirPedidosCardapioToSischef transferirPedidosCardapioToSischef) {
        this.transferirPedidosCardapioToSischef = transferirPedidosCardapioToSischef;
    }

    @PostMapping
    public ResponseEntity<DefaultResponse> receberPedido(@RequestBody WebhookResponsePedido callback) {
        transferirPedidosCardapioToSischef.integrarPedidoToSischef(callback.getPedido());
        return ResponseEntity.ok(new DefaultResponse("ok"));
    }
}
