package com.rfltech.cardapiodigital.client.messages.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(
        ignoreUnknown = true
)
public class RetornoStatusSischef {
    private String id;
    private String idUnicoIntegracao;
    private String situacao;

    public RetornoStatusSischef() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUnicoIntegracao() {
        return this.idUnicoIntegracao;
    }

    public void setIdUnicoIntegracao(String idUnicoIntegracao) {
        this.idUnicoIntegracao = idUnicoIntegracao;
    }

    public String getSituacao() {
        return this.situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

}