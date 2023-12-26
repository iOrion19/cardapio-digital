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

        @JsonProperty("delivery_form")
        private DeliveryForm deliveryForm;

        @JsonProperty("consumer")
        private Usuario usuario;
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
    public static class DeliveryForm {

        @JsonProperty("address")
        private Endereco endereco;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Usuario {

        @JsonProperty("name")
        private String nome;

        @JsonProperty("phone")
        private String telefone;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Endereco {

        @JsonProperty("street")
        private String rua;

        @JsonProperty("number")
        private String numero;

        @JsonProperty("district")
        private String bairro;

        @JsonProperty("city")
        private String cidade;

        @JsonProperty("state_acronym")
        private String uf;

        @JsonProperty("postal_code")
        private String cep;

        @JsonProperty("complement")
        private String complemento;

        @JsonProperty("reference")
        private String referencia;

        @JsonProperty("lat")
        private String latitude;

        @JsonProperty("lng")
        private String longitude;
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