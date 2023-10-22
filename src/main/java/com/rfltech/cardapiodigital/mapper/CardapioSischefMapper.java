package com.rfltech.cardapiodigital.mapper;

import com.rfltech.cardapiodigital.client.messages.requests.NovoPedidoSischef;
import com.rfltech.cardapiodigital.client.messages.response.ItemCardapioDigital;
import com.rfltech.cardapiodigital.client.messages.response.PedidoResponseCardapioDigital;
import com.rfltech.cardapiodigital.constants.SischefConstants;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Mapper
public interface CardapioSischefMapper {
    CardapioSischefMapper INSTANCE = Mappers.getMapper(CardapioSischefMapper.class);

    @Mapping(source = "ref", target = "id")
    @Mapping(source = "ref", target = "idUnicoIntegracao")
    @Mapping(source = "total", target = "valorTotal")
    @Mapping(source = "createdAt", target = "dataPedido")
    @Mapping(source = "usuario", target = "pessoa")
    @Mapping(target = "pagamentos", source = "pagamentos", qualifiedByName = "toPagamentosSischef")
    NovoPedidoSischef toSischefOrder(PedidoResponseCardapioDigital response);

    @Mapping(source = "nome", target = "produto.nome")
    @Mapping(source = "descricao", target = "produto.descricao")
    @Mapping(source = "valor", target = "valorTotal")
    @Mapping(source = "valor", target = "valorUnitario")
    @Mapping(source = "complementos", target = "subItens")
    NovoPedidoSischef.ItemSischef toSischefItem(ItemCardapioDigital response);

    @Mapping(target = "usuario.nome", expression = "java(usuario.getNome() + \" \" + usuario.getSobrenome())")
    @Mapping(source = "endereco.uf", target = "estado.sigla")
    @Mapping(source = "endereco.logradouro", target = "endereco")
    @Mapping(source = "endereco.cidade", target = "municipio.nome")
    NovoPedidoSischef.PessoaSischef toSischefPessoa(PedidoResponseCardapioDigital.UsuarioCardapioDigital usuario, PedidoResponseCardapioDigital.EnderecoEntregaCardapioDigital endereco);

    @Mapping(source = "nome", target = "formaPagamento.descricao")
    @Mapping(source = "categoria", target = "formaPagamento.tipo")
    NovoPedidoSischef.Pagamento toSischefPagamento (PedidoResponseCardapioDigital.Meio pagamento);

    @Named("toPagamentosSischef")
    default List<NovoPedidoSischef.Pagamento> toPagamentosSischef(PedidoResponseCardapioDigital.MeiosPagamento meiosPagamento) {
        return meiosPagamento.getMeios().stream().map(this::toSischefPagamento).collect(Collectors.toList());
    }


    @AfterMapping
    default void afterMapping(@MappingTarget NovoPedidoSischef novoPedidoSischef, PedidoResponseCardapioDigital response) {
        novoPedidoSischef.setTipoPedido(SischefConstants.TIPO_PEDIDO_RETIRADA);

        if (Objects.isNull(response.getDelivery()) || response.getDelivery().equals(1)) {
            novoPedidoSischef.setTipoPedido(SischefConstants.TIPO_PEDIDO_DELIVERY);
            novoPedidoSischef.setTipoDelivery(SischefConstants.TIPO_PEDIDO_DELIVERY);
        }

        if (Objects.isNull(response.getEndereco()))
            novoPedidoSischef.setTipoDelivery(SischefConstants.TIPO_PEDIDO_RETIRADA);

        StringBuilder descricao = new StringBuilder();

        if (StringUtils.hasText(response.getCpfCnpj()))
            descricao.append("Nota fiscal: " + response.getCpfCnpj());

        if (StringUtils.hasText(response.getCodigoDesconto()))
            descricao.append("\n Código desconto: " + response.getCodigoDesconto());

        novoPedidoSischef.setDescricao(descricao.toString());

//        novoPedidoSischef.setDescricao(response.getItens().stream().map(ItemCardapioDigital::getDescricao).collect(Collectors.joining(",")));


        //MOCK ENTENDER DATA
//        LocalDateTime now = LocalDateTime.now();
//        novoPedidoSischef.setDataPedido(now);
//        novoPedidoSischef.setCreatedAt(now);
    }


}
