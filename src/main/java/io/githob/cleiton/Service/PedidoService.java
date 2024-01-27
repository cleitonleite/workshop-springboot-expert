package io.githob.cleiton.Service;

import io.githob.cleiton.domain.entity.Pedido;
import io.githob.cleiton.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);
}
