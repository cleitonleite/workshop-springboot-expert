package io.githob.cleiton.Service.impl;

import io.githob.cleiton.Service.PedidoService;
import io.githob.cleiton.domain.repository.Pedidos;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    private Pedidos repository;

    public PedidoServiceImpl(Pedidos repository) {
        this.repository = repository;
    }
}
