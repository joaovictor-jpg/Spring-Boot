package io.com.github.joaovictorjpg.domen.repository;

import io.com.github.joaovictorjpg.domen.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosRepository extends JpaRepository<Produto, Integer> {
}
