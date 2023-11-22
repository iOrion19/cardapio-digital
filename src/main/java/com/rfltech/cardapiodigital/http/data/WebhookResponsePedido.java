package com.rfltech.cardapiodigital.http.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rfltech.cardapiodigital.client.messages.response.PedidoResponseCardapioDigital;

import java.time.LocalDate;

public class WebhookResponsePedido {

    private String evento;

    private String modulo;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;

    private PedidoResponseCardapioDigital pedido;

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public PedidoResponseCardapioDigital getPedido() {
        return pedido;
    }

    public void setPedido(PedidoResponseCardapioDigital pedido) {
        this.pedido = pedido;
    }
}
