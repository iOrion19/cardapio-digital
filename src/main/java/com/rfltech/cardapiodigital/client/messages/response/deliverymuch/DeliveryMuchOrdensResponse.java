package com.rfltech.cardapiodigital.client.messages.response.deliverymuch;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeliveryMuchOrdensResponse {

    private List<Docs> docs;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Docs {

        @JsonProperty("products")
        private List<Produtos> produtos;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Produtos {

        @JsonProperty("name")
        private String nome;

        @JsonProperty("total_price")
        private Double valorTotal;

        @JsonProperty("description")
        private String descricao;
    }
}