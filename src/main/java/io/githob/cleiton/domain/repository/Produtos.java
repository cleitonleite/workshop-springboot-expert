package io.githob.cleiton.domain.repository;

import io.githob.cleiton.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto, Integer> {
}
