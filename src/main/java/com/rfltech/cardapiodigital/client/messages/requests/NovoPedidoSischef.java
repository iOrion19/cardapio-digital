package com.rfltech.cardapiodigital.client.messages.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class NovoPedidoSischef {

    private String id;
    private String idUnicoIntegracao;
    private LocalDateTime dataPedido;
    private LocalDateTime createdAt;

    private String tipoDelivery;
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
        this.tipoDelivery = "PENDENTE";
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }

    public String getCodigoDesconto() {
        return codigoDesconto;
    }

    public void setCodigoDesconto(String codigoDesconto) {
        this.codigoDesconto = codigoDesconto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipoDelivery() {
        return tipoDelivery;
    }

    public void setTipoDelivery(String tipoDelivery) {
        this.tipoDelivery = tipoDelivery;
    }

    public String getIdUnicoIntegracao() {
        return idUnicoIntegracao;
    }

    public void setIdUnicoIntegracao(String idUnicoIntegracao) {
        this.idUnicoIntegracao = idUnicoIntegracao;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public PessoaSischef getPessoa() {
        return pessoa;
    }

    public BigDecimal getValorTaxaEntrega() {
        return valorTaxaEntrega;
    }

    public void setValorTaxaEntrega(BigDecimal valorTaxaEntrega) {
        this.valorTaxaEntrega = valorTaxaEntrega;
    }

    public void setPessoa(PessoaSischef pessoa) {
        this.pessoa = pessoa;
    }

    public String getTipoPedido() {
        return tipoPedido;
    }

    public void setTipoPedido(String tipoPedido) {
        this.tipoPedido = tipoPedido;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public List<ItemSischef> getItens() {
        return itens;
    }

    public void setItens(List<ItemSischef> itens) {
        this.itens = itens;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValorDesconto(BigDecimal valorDesconto) {
        this.valorDesconto = valorDesconto;
    }



    public static class ItemSischef {

        private ProdutoSischef produto;

        private BigDecimal valorTotal;

        private BigDecimal valorUnitario;

        private List<ItemSischef> subItens;

        private Long quantidade;
        private String codigoExterno;

        public ProdutoSischef getProduto() {
            return produto;
        }

        public void setProduto(ProdutoSischef produto) {
            this.produto = produto;
        }

        public BigDecimal getValorTotal() {
            return valorTotal;
        }

        public void setValorTotal(BigDecimal valorTotal) {
            this.valorTotal = valorTotal;
        }

        public BigDecimal getValorUnitario() {
            return valorUnitario;
        }

        public void setValorUnitario(BigDecimal valorUnitario) {
            this.valorUnitario = valorUnitario;
        }

        public List<ItemSischef> getSubItens() {
            return subItens;
        }

        public void setSubItens(List<ItemSischef> subItens) {
            this.subItens = subItens;
        }

        public Long getQuantidade() {
            return quantidade;
        }

        public void setQuantidade(Long quantidade) {
            this.quantidade = quantidade;
        }

        public String getCodigoExterno() {
            return codigoExterno;
        }

        public void setCodigoExterno(String codigoExterno) {
            this.codigoExterno = codigoExterno;
        }
    }

    public static class ProdutoSischef {

        private String id;
        private String nome;
        private String descricao;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getDescricao() {
            return descricao;
        }

        public void setDescricao(String descricao) {
            this.descricao = descricao;
        }
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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getEndereco() {
            return endereco;
        }

        public void setEndereco(String endereco) {
            this.endereco = endereco;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getNumero() {
            return numero;
        }

        public void setNumero(String numero) {
            this.numero = numero;
        }

        public String getTelefone() {
            return telefone;
        }

        public void setTelefone(String telefone) {
            this.telefone = telefone;
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

        public String getComplemento() {
            return complemento;
        }

        public void setComplemento(String complemento) {
            this.complemento = complemento;
        }

        public Estado getEstado() {
            return estado;
        }

        public void setEstado(Estado estado) {
            this.estado = estado;
        }

        public Municipio getMunicipio() {
            return municipio;
        }

        public void setMunicipio(Municipio municipio) {
            this.municipio = municipio;
        }


    }

    public static class Estado {
        private String sigla;

        public String getSigla() {
            return sigla;
        }

        public void setSigla(String sigla) {
            this.sigla = sigla;
        }
    }

    public static class Municipio {
        private String nome;

        public String getNome() {
            return nome;
        }

        public void setNome(String sigla) {
            this.nome = sigla;
        }
    }

    public static class Pagamento {

        private Long id;
        private FormaPagamento formaPagamento;

        private BigDecimal valor;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public FormaPagamento getFormaPagamento() {
            return formaPagamento;
        }

        public void setFormaPagamento(FormaPagamento formaPagamento) {
            this.formaPagamento = formaPagamento;
        }

        public BigDecimal getValor() {
            return valor;
        }

        public void setValor(BigDecimal valor) {
            this.valor = valor;
        }

        public static class FormaPagamento {

            private String descricao;
            private String tipo;

            public String getDescricao() {
                return descricao;
            }

            public void setDescricao(String descricao) {
                this.descricao = descricao;
            }

            public String getTipo() {
                return tipo;
            }

            public void setTipo(String tipo) {
                this.tipo = tipo;
            }
        }
    }


}
