package io.com.github.joaovictorjpg.domen.repository;

import io.com.github.joaovictorjpg.domen.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    @Query("select p from Pedido p left join fetch p.itemPedidos where p.id = :id")
    Optional<Pedido> findByIdFetchItens(@Param("id") Integer id);
}
