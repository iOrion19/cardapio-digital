package com.rfltech.cardapiodigital.client.messages.response;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemCardapioDigital {

    @JsonProperty("cod_pdv")
    private String codigoExterno;
    private String nome;
    private BigDecimal valor;
    private Long quantidade;
    private boolean resgatado;
    private String obs;
    private List<ItemCardapioDigital> complementos;
    private String descricao;

    public ItemCardapioDigital() {
    }

    public boolean isResgatado() {
        return this.resgatado;
    }

    public void setResgatado(boolean resgatado) {
        this.resgatado = resgatado;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValor() {
        return this.valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Long getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public String getObs() {
        return this.obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public List<ItemCardapioDigital> getComplementos() {
        return this.complementos;
    }

    public void setComplementos(List<ItemCardapioDigital> complementos) {
        this.complementos = complementos;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigoExterno() {
        return this.codigoExterno;
    }

    public void setCodigoExterno(String codigoExterno) {
        this.codigoExterno = codigoExterno;
    }
}
