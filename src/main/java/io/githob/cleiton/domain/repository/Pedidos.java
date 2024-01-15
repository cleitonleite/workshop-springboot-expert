package io.githob.cleiton.domain.repository;

import io.githob.cleiton.domain.entity.Cliente;
import io.githob.cleiton.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Pedidos extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente(Cliente cliente);
}
