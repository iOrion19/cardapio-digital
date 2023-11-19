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
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("total_com_descontos")
    private BigDecimal totalComDesconto;
    @JsonProperty("total")
    private BigDecimal valorTotal;
    private String troco;
    @JsonProperty("meio_de_pagamento")
    private String meioPagamento;
    @JsonProperty("valor_desconto")
    private BigDecimal valorDesconto;
    @JsonProperty("valor_entrega")
    private BigDecimal valorTaxaEntrega;
    private Integer delivery;
    private List<ItemCardapioDigital> itens;
    private UsuarioCardapioDigital usuario;
    @JsonProperty("mensagem_no_cartao")
    private MensagemCartao mensagem;
    @JsonProperty("cpf_cnpj")
    private String cpfCnpj;
    @JsonProperty("codigo_desconto")
    private String codigoDesconto;
    @JsonProperty("meios_de_pagamento")
    private MeiosPagamento pagamentos;
    @JsonProperty("endereco_de_entrega")
    private EnderecoEntregaCardapioDigital endereco;

    public PedidoResponseCardapioDigital() {
    }

    public BigDecimal getValorTotal() {
        return this.valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getObs() {
        return this.obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public MensagemCartao getMensagem() {
        return this.mensagem;
    }

    public void setMensagem(MensagemCartao mensagem) {
        this.mensagem = mensagem;
    }

    public String getMeioPagamento() {
        return this.meioPagamento;
    }

    public void setMeioPagamento(String meioPagamento) {
        this.meioPagamento = meioPagamento;
    }

    public MeiosPagamento getPagamentos() {
        return this.pagamentos;
    }

    public void setPagamentos(MeiosPagamento pagamentos) {
        this.pagamentos = pagamentos;
    }

    public String getCodigoDesconto() {
        return this.codigoDesconto;
    }

    public void setCodigoDesconto(String codigoDesconto) {
        this.codigoDesconto = codigoDesconto;
    }

    public UsuarioCardapioDigital getUsuario() {
        return this.usuario;
    }

    public BigDecimal getValorTaxaEntrega() {
        return this.valorTaxaEntrega;
    }

    public void setValorTaxaEntrega(BigDecimal valorTaxaEntrega) {
        this.valorTaxaEntrega = valorTaxaEntrega;
    }

    public void setUsuario(UsuarioCardapioDigital usuario) {
        this.usuario = usuario;
    }

    public EnderecoEntregaCardapioDigital getEndereco() {
        return this.endereco;
    }

    public String getCpfCnpj() {
        return this.cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public void setEndereco(EnderecoEntregaCardapioDigital endereco) {
        this.endereco = endereco;
    }

    public Long getRef() {
        return this.ref;
    }

    public void setRef(Long ref) {
        this.ref = ref;
    }

    public Integer getDelivery() {
        return this.delivery;
    }

    public void setDelivery(Integer delivery) {
        this.delivery = delivery;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public BigDecimal getTotalComDesconto() {
        return this.totalComDesconto;
    }

    public void setTotalComDesconto(BigDecimal totalComDesconto) {
        this.totalComDesconto = totalComDesconto;
    }

    public String getTroco() {
        return this.troco;
    }

    public void setTroco(String troco) {
        this.troco = troco;
    }

    public BigDecimal getValorDesconto() {
        return this.valorDesconto;
    }

    public void setValorDesconto(BigDecimal valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public List<ItemCardapioDigital> getItens() {
        return this.itens;
    }

    public void setItens(List<ItemCardapioDigital> itens) {
        this.itens = itens;
    }

    public static class MensagemCartao {
        private Campos campos;

        public MensagemCartao() {
        }

        public Campos getCampos() {
            return this.campos;
        }

        public void setCampos(Campos campos) {
            this.campos = campos;
        }
    }

    public static class MeiosPagamento {
        private List<Meio> meios;

        public MeiosPagamento() {
        }

        public List<Meio> getMeios() {
            return this.meios;
        }

        public void setMeios(List<Meio> meios) {
            this.meios = meios;
        }
    }

    public static class UsuarioCardapioDigital {
        private String nome;
        private String sobrenome;
        private String telefone;
        @JsonProperty("data_nascimento")
        @JsonFormat(
                pattern = "yyyy-MM-dd"
        )
        private LocalDate dataNascimento;
        private String genero;

        public UsuarioCardapioDigital() {
        }

        public String getNome() {
            return this.nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getSobrenome() {
            return this.sobrenome;
        }

        public void setSobrenome(String sobrenome) {
            this.sobrenome = sobrenome;
        }

        public String getTelefone() {
            return this.telefone;
        }

        public void setTelefone(String telefone) {
            this.telefone = telefone;
        }

        public LocalDate getDataNascimento() {
            return this.dataNascimento;
        }

        public void setDataNascimento(LocalDate dataNascimento) {
            this.dataNascimento = dataNascimento;
        }

        public String getGenero() {
            return this.genero;
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

        public EnderecoEntregaCardapioDigital() {
        }

        public String getLogradouro() {
            return this.logradouro;
        }

        public void setLogradouro(String logradouro) {
            this.logradouro = logradouro;
        }

        public String getNumero() {
            return this.numero;
        }

        public void setNumero(String numero) {
            this.numero = numero;
        }

        public String getComplemento() {
            return this.complemento;
        }

        public void setComplemento(String complemento) {
            this.complemento = complemento;
        }

        public String getReferencia() {
            return this.referencia;
        }

        public void setReferencia(String referencia) {
            this.referencia = referencia;
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

        public String getCidade() {
            return this.cidade;
        }

        public void setCidade(String cidade) {
            this.cidade = cidade;
        }

        public String getUf() {
            return this.uf;
        }

        public void setUf(String uf) {
            this.uf = uf;
        }
    }

    public static class Campos {
        @JsonProperty("de_text")
        private String deText;
        private String anonimo;
        @JsonProperty("para_text")
        private String para;
        @JsonProperty("mensagem_text")
        private String mensagem;
        @JsonProperty("nome_destinatario")
        private String nomeDestinatario;
        @JsonProperty("telefone_destinatario")
        private String telefoneDestinatario;

        public Campos() {
        }

        public String getDeText() {
            return this.deText;
        }

        public void setDeText(String deText) {
            this.deText = deText;
        }

        public String getAnonimo() {
            return this.anonimo;
        }

        public void setAnonimo(String anonimo) {
            this.anonimo = anonimo;
        }

        public String getPara() {
            return this.para;
        }

        public void setPara(String para) {
            this.para = para;
        }

        public String getMensagem() {
            return this.mensagem;
        }

        public void setMensagem(String mensagem) {
            this.mensagem = mensagem;
        }

        public String getNomeDestinatario() {
            return this.nomeDestinatario;
        }

        public void setNomeDestinatario(String nomeDestinatario) {
            this.nomeDestinatario = nomeDestinatario;
        }

        public String getTelefoneDestinatario() {
            return this.telefoneDestinatario;
        }

        public void setTelefoneDestinatario(String telefoneDestinatario) {
            this.telefoneDestinatario = telefoneDestinatario;
        }
    }

    public static class Meio {
        @JsonProperty("forma_id")
        private String id;
        private BigDecimal valor;
        private String troco;
        private String categoria;
        private String nome;
        private String tag;
        private String img;

        public Meio() {
        }

        public String getId() {
            return this.id;
        }

        public void setId(String formaId) {
            this.id = this.id;
        }

        public BigDecimal getValor() {
            return this.valor;
        }

        public void setValor(BigDecimal valor) {
            this.valor = valor;
        }

        public String getTroco() {
            return this.troco;
        }

        public void setTroco(String troco) {
            this.troco = troco;
        }

        public String getCategoria() {
            return this.categoria;
        }

        public void setCategoria(String categoria) {
            this.categoria = categoria;
        }

        public String getNome() {
            return this.nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getTag() {
            return this.tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getImg() {
            return this.img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
