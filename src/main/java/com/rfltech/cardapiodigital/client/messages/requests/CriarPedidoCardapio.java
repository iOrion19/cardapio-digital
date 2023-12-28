package com.rfltech.cardapiodigital.client.messages.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CriarPedidoCardapio {

    private String provedor;

    @JsonProperty("id_externo")
    private String idExterno;

    private Dados dados;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Dados {

        private Pedido pedido;

        private List<Pagamento> pagamento;

        @JsonProperty("valores_entrega")
        private ValoresEntrega valoresEntrega;

        @JsonProperty("endereco_de_entrega")
        private EnderecoEntrega enderecoEntrega;

        private Usuario usuario;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Pedido {

        @JsonProperty("obs")
        private String observacao;

        private String data_agendamento;

        @JsonProperty("meio_de_entrega")
        private String meioEntrega;

        private String cpf_cnpj;

        private int agendamento;

        private int numero_de_pessoas;

        private List<Item> itens;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Item {

        @JsonProperty("obs")
        private String observacao;

        @JsonProperty("id_alloy")
        private String idAlloy;

        private int quantidade;

        private double valor;

        @JsonProperty("id_externo")
        private String idExterno;

        private String nome;

        private String descricao;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("valor_desconto")
        private Double valorDesconto;

        private List<Complemento> complementos;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Complemento {

        private int quantidade;

        private double valor;

        @JsonProperty("id_alloy")
        private String idAlloy;

        @JsonProperty("id_externo")
        private String idExterno;

        private String nome;

        private String descricao;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Pagamento {

        @JsonProperty("forma_de_pagamento")
        private int formaPagamento;

        private double total;

        @JsonProperty("troco_para")
        private double trocoPara;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class ValoresEntrega {

        @JsonProperty("valor_entrega")
        private double valorEntrega;

        private double valor_desconto_entrega;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class EnderecoEntrega {

        private String logradouro;

        private String numero;

        private String complemento;

        private String referencia;

        private String bairro;

        private String cep;

        private String cidade;

        private String uf;

        @JsonProperty("coordenadas_cliente")
        private String coordenadas;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Usuario {

        private String nome;

        private String sobrenome;

        private String cpf;

        private String email;

        private String telefone;

        @JsonProperty("telefone_codigo_pais")
        private String telefoneCodigoPais;

        @JsonProperty("data_nascimento")
        private String dataNascimento;

        private String genero;
    }
}