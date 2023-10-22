package com.rfltech.cardapiodigital.client.messages.response;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemCardapioDigital {

    @JsonProperty(value = "cod_pdv")
    private String codigoExterno;

    private String nome;

    private BigDecimal valor;

    private Long quantidade;

    private boolean resgatado;

    private List<ItemCardapioDigital> complementos;

    private String descricao;

    public boolean isResgatado() {
        return resgatado;
    }

    public void setResgatado(boolean resgatado) {
        this.resgatado = resgatado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public List<ItemCardapioDigital> getComplementos() {
        return complementos;
    }

    public void setComplementos(List<ItemCardapioDigital> complementos) {
        this.complementos = complementos;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigoExterno() {
        return codigoExterno;
    }

    public void setCodigoExterno(String codigoExterno) {
        this.codigoExterno = codigoExterno;
    }
}
