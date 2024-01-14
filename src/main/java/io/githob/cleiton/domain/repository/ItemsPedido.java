package io.githob.cleiton.domain.repository;

import io.githob.cleiton.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsPedido extends JpaRepository<ItemPedido, Integer> {
}
