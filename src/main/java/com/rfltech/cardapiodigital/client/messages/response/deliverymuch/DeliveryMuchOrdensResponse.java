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

        @JsonProperty("quantity")
        private int quantidade;

        private int id;

        @JsonProperty("id_erp")
        private String idErp;

        private List<Groups> groups;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Groups {

        @JsonProperty("subgroups")
        private List<SubGroups> subGroups;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class SubGroups {

        @JsonProperty("items")
        private List<Item> itens;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Item {

        @JsonProperty("name")
        private String nome;

        @JsonProperty("price")
        private Double preco;

        @JsonProperty("description")
        private String descricao;

        private int id;

        @JsonProperty("id_erp")
        private String idErp;
    }
}