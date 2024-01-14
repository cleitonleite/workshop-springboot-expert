package io.githob.cleiton.domain.repository;

import io.githob.cleiton.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Pedidos extends JpaRepository<Pedido, Integer> {
}
