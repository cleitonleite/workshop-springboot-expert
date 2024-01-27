package io.githob.cleiton.Service;

import io.githob.cleiton.domain.entity.Pedido;
import io.githob.cleiton.rest.dto.PedidoDTO;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);
}
