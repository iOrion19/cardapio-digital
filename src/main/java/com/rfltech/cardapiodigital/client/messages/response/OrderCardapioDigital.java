package com.rfltech.cardapiodigital.client.messages.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderCardapioDigital {

    @JsonProperty(value = "pedidos_count")
    private Long quantidadePedidos;
    private List<PedidoResponseCardapioDigital> pedidos;

    public Long getQuantidadePedidos() {
        return quantidadePedidos;
    }

    public void setQuantidadePedidos(Long quantidadePedidos) {
        this.quantidadePedidos = quantidadePedidos;
    }

    public List<PedidoResponseCardapioDigital> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<PedidoResponseCardapioDigital> pedidos) {
        this.pedidos = pedidos;
    }
}
