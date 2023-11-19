package com.rfltech.cardapiodigital.repository;

import com.rfltech.cardapiodigital.model.PedidoStatus;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface PedidoStatusRepository extends JpaRepository<PedidoStatus, Long>  {

    List<PedidoStatus> findPedidoStatusByStatusIn(List<String> statusList);

}
