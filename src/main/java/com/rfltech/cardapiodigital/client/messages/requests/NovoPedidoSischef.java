package com.rfltech.cardapiodigital.client.messages.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@JsonIgnoreProperties(
        ignoreUnknown = true
)
public class NovoPedidoSischef {
    private String id;
    private String idUnicoIntegracao;
    private LocalDateTime dataPedido;
    private LocalDateTime createdAt;
    private String tipoDelivery = "PENDENTE";
    private String tipoPedido;
    private String situacao;
    private String descricao;
    private List<ItemSischef> itens;
    private BigDecimal valorTotal;
    private BigDecimal troco;
    private BigDecimal valorDesconto;
    @JsonProperty("codigo_desconto")
    private String codigoDesconto;
    private BigDecimal valorTaxaEntrega;
    private PessoaSischef pessoa;
    private List<Pagamento> pagamentos;

    public NovoPedidoSischef() {
    }

    public List<Pagamento> getPagamentos() {
        return this.pagamentos;
    }

    public void setPagamentos(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }

    public String getCodigoDesconto() {
        return this.codigoDesconto;
    }

    public void setCodigoDesconto(String codigoDesconto) {
        this.codigoDesconto = codigoDesconto;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipoDelivery() {
        return this.tipoDelivery;
    }

    public void setTipoDelivery(String tipoDelivery) {
        this.tipoDelivery = tipoDelivery;
    }

    public String getIdUnicoIntegracao() {
        return this.idUnicoIntegracao;
    }

    public void setIdUnicoIntegracao(String idUnicoIntegracao) {
        this.idUnicoIntegracao = idUnicoIntegracao;
    }

    public LocalDateTime getDataPedido() {
        return this.dataPedido;
    }

    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public PessoaSischef getPessoa() {
        return this.pessoa;
    }

    public BigDecimal getValorTaxaEntrega() {
        return this.valorTaxaEntrega;
    }

    public void setValorTaxaEntrega(BigDecimal valorTaxaEntrega) {
        this.valorTaxaEntrega = valorTaxaEntrega;
    }

    public void setPessoa(PessoaSischef pessoa) {
        this.pessoa = pessoa;
    }

    public String getTipoPedido() {
        return this.tipoPedido;
    }

    public void setTipoPedido(String tipoPedido) {
        this.tipoPedido = tipoPedido;
    }

    public String getSituacao() {
        return this.situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public List<ItemSischef> getItens() {
        return this.itens;
    }

    public void setItens(List<ItemSischef> itens) {
        this.itens = itens;
    }

    public BigDecimal getValorTotal() {
        return this.valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getTroco() {
        return this.troco;
    }

    public void setTroco(BigDecimal troco) {
        this.troco = troco;
    }

    public BigDecimal getValorDesconto() {
        return this.valorDesconto;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValorDesconto(BigDecimal valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public static class PessoaSischef {
        private String id;
        private String nome;
        private String endereco;
        private String numero;
        private String telefone;
        private String bairro;
        private String cep;
        private String complemento;
        private Estado estado;
        private Municipio municipio;

        public PessoaSischef() {
        }

        public String getId() {
            return this.id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getEndereco() {
            return this.endereco;
        }

        public void setEndereco(String endereco) {
            this.endereco = endereco;
        }

        public String getNome() {
            return this.nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getNumero() {
            return this.numero;
        }

        public void setNumero(String numero) {
            this.numero = numero;
        }

        public String getTelefone() {
            return this.telefone;
        }

        public void setTelefone(String telefone) {
            this.telefone = telefone;
        }

        public String getBairro() {
            return this.bairro;
        }

        public void setBairro(String bairro) {
            this.bairro = bairro;
        }

        public String getCep() {
            return this.cep;
        }

        public void setCep(String cep) {
            this.cep = cep;
        }

        public String getComplemento() {
            return this.complemento;
        }

        public void setComplemento(String complemento) {
            this.complemento = complemento;
        }

        public Estado getEstado() {
            return this.estado;
        }

        public void setEstado(Estado estado) {
            this.estado = estado;
        }

        public Municipio getMunicipio() {
            return this.municipio;
        }

        public void setMunicipio(Municipio municipio) {
            this.municipio = municipio;
        }
    }

    public static class Pagamento {
        private Long id;
        private FormaPagamento formaPagamento;
        private BigDecimal valor;

        public Pagamento() {
        }

        public Long getId() {
            return this.id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public FormaPagamento getFormaPagamento() {
            return this.formaPagamento;
        }

        public void setFormaPagamento(FormaPagamento formaPagamento) {
            this.formaPagamento = formaPagamento;
        }

        public BigDecimal getValor() {
            return this.valor;
        }

        public void setValor(BigDecimal valor) {
            this.valor = valor;
        }

        public static class FormaPagamento {
            private String descricao;
            private String tipo;
            private String pagamento;

            public FormaPagamento() {
            }

            public String getDescricao() {
                return this.descricao;
            }

            public void setDescricao(String descricao) {
                this.descricao = descricao;
            }

            public String getTipo() {
                return this.tipo;
            }

            public void setTipo(String tipo) {
                this.tipo = tipo;
            }

            public String getPagamento() {
                return this.pagamento;
            }

            public void setPagamento(String pagamento) {
                this.pagamento = pagamento;
            }
        }
    }

    public static class Municipio {
        private String nome;

        public Municipio() {
        }

        public String getNome() {
            return this.nome;
        }

        public void setNome(String sigla) {
            this.nome = sigla;
        }
    }

    public static class Estado {
        private String sigla;

        public Estado() {
        }

        public String getSigla() {
            return this.sigla;
        }

        public void setSigla(String sigla) {
            this.sigla = sigla;
        }
    }

    public static class ProdutoSischef {
        private String id;
        private String nome;
        private String descricao;

        public ProdutoSischef() {
        }

        public String getId() {
            return this.id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNome() {
            return this.nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getDescricao() {
            return this.descricao;
        }

        public void setDescricao(String descricao) {
            this.descricao = descricao;
        }
    }

    public static class ItemSischef {
        private ProdutoSischef produto;
        private BigDecimal valorTotal;
        private BigDecimal valorUnitario;
        private List<SubItemPedido> subItens;
        private Long quantidade;
        private String codigoExterno;
        @JsonIgnore
        private String obs;

        public ItemSischef() {
        }

        public ProdutoSischef getProduto() {
            return this.produto;
        }

        public String getObservacao() {
            return this.obs;
        }

        public void setProduto(ProdutoSischef produto) {
            this.produto = produto;
        }

        public String getObs() {
            return this.obs;
        }

        public void setObs(String obs) {
            this.obs = obs;
        }

        public BigDecimal getValorTotal() {
            return this.valorTotal;
        }

        public void setValorTotal(BigDecimal valorTotal) {
            this.valorTotal = valorTotal;
        }

        public BigDecimal getValorUnitario() {
            return this.valorUnitario;
        }

        public void setValorUnitario(BigDecimal valorUnitario) {
            this.valorUnitario = valorUnitario;
        }

        public List<SubItemPedido> getSubItens() {
            return this.subItens;
        }

        public void setSubItens(List<SubItemPedido> subItens) {
            this.subItens = subItens;
        }

        public Long getQuantidade() {
            return this.quantidade;
        }

        public void setQuantidade(Long quantidade) {
            this.quantidade = quantidade;
        }

        public String getCodigoExterno() {
            return this.codigoExterno;
        }

        public void setCodigoExterno(String codigoExterno) {
            this.codigoExterno = codigoExterno;
        }

        @JsonIgnoreProperties(
                ignoreUnknown = true
        )
        public static class SubItemPedido {
            private String descricao;
            private BigDecimal valorTotal;
            private BigDecimal valorUnitario;
            private List<ItemSischef> subItens;
            private Long quantidade;
            private String codigoExterno;

            public SubItemPedido() {
            }

            public String getDescricao() {
                return this.descricao;
            }

            public void setDescricao(String descricao) {
                this.descricao = descricao;
            }

            public BigDecimal getValorTotal() {
                return this.valorTotal;
            }

            public void setValorTotal(BigDecimal valorTotal) {
                this.valorTotal = valorTotal;
            }

            public BigDecimal getValorUnitario() {
                return this.valorUnitario;
            }

            public void setValorUnitario(BigDecimal valorUnitario) {
                this.valorUnitario = valorUnitario;
            }

            public List<ItemSischef> getSubItens() {
                return this.subItens;
            }

            public void setSubItens(List<ItemSischef> subItens) {
                this.subItens = subItens;
            }

            public Long getQuantidade() {
                return this.quantidade;
            }

            public void setQuantidade(Long quantidade) {
                this.quantidade = quantidade;
            }

            public String getCodigoExterno() {
                return this.codigoExterno;
            }

            public void setCodigoExterno(String codigoExterno) {
                this.codigoExterno = codigoExterno;
            }
        }
    }

}
