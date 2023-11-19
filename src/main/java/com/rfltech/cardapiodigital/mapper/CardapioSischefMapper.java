package com.rfltech.cardapiodigital.mapper;

import com.rfltech.cardapiodigital.client.messages.requests.NovoPedidoSischef;
import com.rfltech.cardapiodigital.client.messages.response.ItemCardapioDigital;
import com.rfltech.cardapiodigital.client.messages.response.PedidoResponseCardapioDigital;
import com.rfltech.cardapiodigital.constants.SischefConstants;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Mapper
public interface CardapioSischefMapper {
    CardapioSischefMapper INSTANCE = (CardapioSischefMapper)Mappers.getMapper(CardapioSischefMapper.class);

    @Mappings({@Mapping(
            source = "ref",
            target = "id"
    ), @Mapping(
            source = "ref",
            target = "idUnicoIntegracao"
    ), @Mapping(
            source = "createdAt",
            target = "dataPedido"
    ), @Mapping(
            source = "usuario",
            target = "pessoa"
    ), @Mapping(
            target = "pagamentos",
            source = "pagamentos",
            qualifiedByName = {"toPagamentosSischef"}
    )})
    NovoPedidoSischef toSischefOrder(PedidoResponseCardapioDigital var1);

    @Mappings({@Mapping(
            source = "nome",
            target = "produto.nome"
    ), @Mapping(
            source = "descricao",
            target = "produto.descricao"
    ), @Mapping(
            source = "valor",
            target = "valorTotal"
    ), @Mapping(
            source = "valor",
            target = "valorUnitario"
    ), @Mapping(
            source = "complementos",
            target = "subItens"
    )})
    NovoPedidoSischef.ItemSischef toSischefItem(ItemCardapioDigital var1);

    @Mappings({@Mapping(
            target = "usuario.nome",
            expression = "java(usuario.getNome() + \" \" + usuario.getSobrenome())"
    ), @Mapping(
            source = "endereco.uf",
            target = "estado.sigla"
    ), @Mapping(
            source = "endereco.logradouro",
            target = "endereco"
    ), @Mapping(
            source = "endereco.cidade",
            target = "municipio.nome"
    )})
    NovoPedidoSischef.PessoaSischef toSischefPessoa(PedidoResponseCardapioDigital.UsuarioCardapioDigital usuario, PedidoResponseCardapioDigital.EnderecoEntregaCardapioDigital endereco);

    @Mappings({@Mapping(
            source = "nome",
            target = "formaPagamento.descricao"
    ), @Mapping(
            source = "categoria",
            target = "formaPagamento.tipo"
    )})
    NovoPedidoSischef.Pagamento toSischefPagamento(PedidoResponseCardapioDigital.Meio response);

    @Mappings({@Mapping(
            source = "nome",
            target = "descricao"
    ), @Mapping(
            source = "valor",
            target = "valorUnitario"
    ), @Mapping(
            source = "valor",
            target = "valorTotal"
    )})
    NovoPedidoSischef.ItemSischef.SubItemPedido toSischefSubItem(ItemCardapioDigital response);

    @Named("toPagamentosSischef")
    default List<NovoPedidoSischef.Pagamento> toPagamentosSischef(PedidoResponseCardapioDigital.MeiosPagamento meiosPagamento) {
        return (List)(Objects.nonNull(meiosPagamento) ? (List)meiosPagamento.getMeios().stream().map(this::toSischefPagamento).collect(Collectors.toList()) : new ArrayList());
    }

    @AfterMapping
    default void afterMapping(@MappingTarget NovoPedidoSischef novoPedidoSischef, PedidoResponseCardapioDigital response) {
        novoPedidoSischef.setTipoPedido("RETIRADA");
        if (Objects.isNull(response.getDelivery()) || response.getDelivery().equals(1)) {
            novoPedidoSischef.setTipoPedido("ENTREGAR");
            novoPedidoSischef.setTipoDelivery("ENTREGAR");
        }

        if (Objects.isNull(response.getEndereco())) {
            novoPedidoSischef.setTipoDelivery("RETIRADA");
        }

        if (Objects.nonNull(response.getPagamentos()) && Objects.nonNull(response.getPagamentos().getMeios())) {
            response.getPagamentos().getMeios().stream().forEach((p) -> {
                if (Objects.nonNull(p.getTroco())) {
                    novoPedidoSischef.setTroco(BigDecimal.valueOf(Double.valueOf(p.getTroco().replace(",", "."))));
                }

            });
        }

    }
}
