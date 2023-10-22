package com.rfltech.cardapiodigital.client.messages.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PedidoResponseCardapioDigital {

    private Long ref;

    private String obs;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(value = "created_at")
    private LocalDateTime createdAt;

    private BigDecimal total;

    private BigDecimal troco;

    @JsonProperty(value = "valor_desconto")
    private BigDecimal valorDesconto;

    @JsonProperty(value = "valor_entrega")
    private BigDecimal valorTaxaEntrega;
    private Integer delivery;

    private List<ItemCardapioDigital> itens;

    private UsuarioCardapioDigital usuario;

    @JsonProperty("cpf_cnpj")
    private String cpfCnpj;

    @JsonProperty("codigo_desconto")
    private String codigoDesconto;

    @JsonProperty(value = "meios_de_pagamento")
    private MeiosPagamento pagamentos;

    @JsonProperty(value = "endereco_de_entrega")
    private EnderecoEntregaCardapioDigital endereco;

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public MeiosPagamento getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(MeiosPagamento pagamentos) {
        this.pagamentos = pagamentos;
    }

    public String getCodigoDesconto() {
        return codigoDesconto;
    }

    public void setCodigoDesconto(String codigoDesconto) {
        this.codigoDesconto = codigoDesconto;
    }

    public UsuarioCardapioDigital getUsuario() {
        return usuario;
    }

    public BigDecimal getValorTaxaEntrega() {
        return valorTaxaEntrega;
    }

    public void setValorTaxaEntrega(BigDecimal valorTaxaEntrega) {
        this.valorTaxaEntrega = valorTaxaEntrega;
    }

    public void setUsuario(UsuarioCardapioDigital usuario) {
        this.usuario = usuario;
    }

    public EnderecoEntregaCardapioDigital getEndereco() {
        return endereco;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public void setEndereco(EnderecoEntregaCardapioDigital endereco) {
        this.endereco = endereco;
    }

    public Long getRef() {
        return ref;
    }

    public void setRef(Long ref) {
        this.ref = ref;
    }

    public Integer getDelivery() {
        return delivery;
    }

    public void setDelivery(Integer delivery) {
        this.delivery = delivery;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getTroco() {
        return troco;
    }

    public void setTroco(BigDecimal troco) {
        this.troco = troco;
    }

    public BigDecimal getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(BigDecimal valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public List<ItemCardapioDigital> getItens() {
        return itens;
    }

    public void setItens(List<ItemCardapioDigital> itens) {
        this.itens = itens;
    }

    public static class UsuarioCardapioDigital {

        private String nome;
        private String sobrenome;
        private String telefone;
        @JsonProperty(value = "data_nascimento")
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate dataNascimento;

        private String genero;

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getSobrenome() {
            return sobrenome;
        }

        public void setSobrenome(String sobrenome) {
            this.sobrenome = sobrenome;
        }



        public String getTelefone() {
            return telefone;
        }

        public void setTelefone(String telefone) {
            this.telefone = telefone;
        }

        public LocalDate getDataNascimento() {
            return dataNascimento;
        }

        public void setDataNascimento(LocalDate dataNascimento) {
            this.dataNascimento = dataNascimento;
        }

        public String getGenero() {
            return genero;
        }

        public void setGenero(String genero) {
            this.genero = genero;
        }
    }

    public static class EnderecoEntregaCardapioDigital {

        private String logradouro;
        private String numero;
        private String complemento;
        private String referencia;
        private String bairro;
        private String cep;
        private String cidade;
        private String uf;

        public String getLogradouro() {
            return logradouro;
        }

        public void setLogradouro(String logradouro) {
            this.logradouro = logradouro;
        }

        public String getNumero() {
            return numero;
        }

        public void setNumero(String numero) {
            this.numero = numero;
        }

        public String getComplemento() {
            return complemento;
        }

        public void setComplemento(String complemento) {
            this.complemento = complemento;
        }

        public String getReferencia() {
            return referencia;
        }

        public void setReferencia(String referencia) {
            this.referencia = referencia;
        }

        public String getBairro() {
            return bairro;
        }

        public void setBairro(String bairro) {
            this.bairro = bairro;
        }

        public String getCep() {
            return cep;
        }

        public void setCep(String cep) {
            this.cep = cep;
        }

        public String getCidade() {
            return cidade;
        }

        public void setCidade(String cidade) {
            this.cidade = cidade;
        }

        public String getUf() {
            return uf;
        }

        public void setUf(String uf) {
            this.uf = uf;
        }
    }

    public static class MeiosPagamento {

        private List<Meio> meios;

        public List<Meio> getMeios() {
            return meios;
        }

        public void setMeios(List<Meio> meios) {
            this.meios = meios;
        }
    }

    public static class Meio {

        @JsonProperty(value = "forma_id")
        private String id;

        private BigDecimal valor;

        private BigDecimal troco;

        private String categoria;

        private String nome;

        private String tag;

        private String img;

        public String getId() {
            return id;
        }

        public void setId(String formaId) {
            this.id = id;
        }

        public BigDecimal getValor() {
            return valor;
        }

        public void setValor(BigDecimal valor) {
            this.valor = valor;
        }

        public BigDecimal getTroco() {
            return troco;
        }

        public void setTroco(BigDecimal troco) {
            this.troco = troco;
        }

        public String getCategoria() {
            return categoria;
        }

        public void setCategoria(String categoria) {
            this.categoria = categoria;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
