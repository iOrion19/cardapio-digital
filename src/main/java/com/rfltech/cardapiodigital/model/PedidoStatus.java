package com.rfltech.cardapiodigital.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
@Table(
        name = "PEDIDO_STATUS"
)
@Entity
public class PedidoStatus {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;
    @Column(
            name = "ID_REF"
    )
    private Long idRef;
    @Column(
            name = "STATUS"
    )
    private String status;
    @Column(
            name = "ID_INTEGRACAO"
    )
    private Long idIntegracao;
    @Column(
            name = "DATA_CRIACAO"
    )
    private LocalDateTime dataCriacao;

    public PedidoStatus() {
    }

    public PedidoStatus(Long idRef, String status, Long idIntegracao) {
        this.idRef = idRef;
        this.status = status;
        this.idIntegracao = idIntegracao;
        this.dataCriacao = LocalDateTime.now();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdIntegracao() {
        return this.idIntegracao;
    }

    public void setIdIntegracao(Long idIntegracao) {
        this.idIntegracao = idIntegracao;
    }

    public Long getIdRef() {
        return this.idRef;
    }

    public void setIdRef(Long idRef) {
        this.idRef = idRef;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDataCriacao() {
        return this.dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}