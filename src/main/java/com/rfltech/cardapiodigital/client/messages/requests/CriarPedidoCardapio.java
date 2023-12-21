package com.rfltech.cardapiodigital.client.messages.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CriarPedidoCardapio {

    private String provedor;

    private String id_externo;

    private Dados dados;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Dados {

        private Pedido pedido;

        private List<Pagamento> pagamento;

        private ValoresEntrega valores_entrega;

        private EnderecoEntrega endereco_de_entrega;

        private Usuario usuario;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Pedido {

        private String obs;

        private String data_agendamento;

        private String meio_de_entrega;

        private String cpf_cnpj;

        private int agendamento;

        private int numero_de_pessoas;

        private List<Item> itens;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Item {

        private int quantidade;

        private String obs;

        private double valor;

        private String id_alloy;

        private String id_externo;

        private String nome;

        private String descricao;

        private double valor_desconto;

        private List<Complemento> complementos;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Complemento {

        private int quantidade;

        private double valor;

        private String id_alloy;

        private String id_externo;

        private String nome;

        private String descricao;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Pagamento {

        private int forma_de_pagamento;

        private double total;

        private double troco_para;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class ValoresEntrega {

        private double valor_entrega;

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

        private String coordenadas_cliente;
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

        private String telefone_codigo_pais;

        private String data_nascimento;

        private String genero;
    }
}