package com.rfltech.cardapiodigital.scheduled;

import com.rfltech.cardapiodigital.client.CardapioDigitalClient;
import com.rfltech.cardapiodigital.client.SischefClient;
import com.rfltech.cardapiodigital.client.messages.response.RetornoStatusSischef;
import com.rfltech.cardapiodigital.model.PedidoStatus;
import com.rfltech.cardapiodigital.model.enumerator.StatusEnum;
import com.rfltech.cardapiodigital.repository.PedidoStatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class AtualizarPedidoScheduled {
    private static final Logger logger = LoggerFactory.getLogger(AtualizarPedidoScheduled.class);
    private final PedidoStatusRepository repository;
    private final SischefClient sischefClient;
    private final CardapioDigitalClient cardapioDigitalClient;

    public AtualizarPedidoScheduled(PedidoStatusRepository repository, SischefClient sischefClient, CardapioDigitalClient cardapioDigitalClient) {
        this.repository = repository;
        this.sischefClient = sischefClient;
        this.cardapioDigitalClient = cardapioDigitalClient;
    }

    @Scheduled(fixedRate = 30000L)
    public void atualizarPedidos() {
        logger.info("INICIALIZANDO ATUALIZAÇÃO DOS PEDIDOS");
        List<PedidoStatus> pedidoStatusByStatusIn = this.repository.findPedidoStatusByStatusIn(List.of(StatusEnum.CONFIRMADO.name(), StatusEnum.ENVIADO.name(), StatusEnum.PENDENTE.name()));
        logger.info("ENCONTRADO {} PEDIDOS PARA ATUALIZAR", pedidoStatusByStatusIn.size());
        pedidoStatusByStatusIn.forEach((p) -> {
            try {
                RetornoStatusSischef pedidoSischef = this.sischefClient.buscarPedido(String.valueOf(p.getIdIntegracao())).getBody();
                if (Objects.nonNull(pedidoSischef) && !pedidoSischef.getSituacao().equalsIgnoreCase(p.getStatus())) {
                    if (pedidoSischef.getSituacao().equalsIgnoreCase(StatusEnum.CANCELADO.name())) {
                        logger.info(this.cardapioDigitalClient.cancelOrder(String.valueOf(p.getIdRef())).getBody());
                        p.setStatus(pedidoSischef.getSituacao());
                        this.repository.save(p);
                        return;
                    }

                    logger.info(this.cardapioDigitalClient.updateNextStatusOrder(String.valueOf(p.getIdRef())).getBody());
                    p.setStatus(pedidoSischef.getSituacao());
                    this.repository.save(p);
                }
            } catch (Exception ex) {
                logger.error(ex.getMessage());
            }
        });
    }
}