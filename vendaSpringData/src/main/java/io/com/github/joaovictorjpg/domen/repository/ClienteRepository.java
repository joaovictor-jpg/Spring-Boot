package io.com.github.joaovictorjpg.domen.repository;

import io.com.github.joaovictorjpg.domen.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    //select c from Cliente c where c.nome like :nome;
    //List<Cliente> findByNomeLike(String nome);

    //@Query(value = " select c from Cliente c where c.nome like :nome ")
    @Query(value = " select * from cliente c where c.nome like '%:nome%' ", nativeQuery = true)
    List<Cliente> buscarPorNome(@Param("nome") String nome);

    //retorna somente um cliente por nome
    Cliente findOneByNome(String nome);

    @Query(value = " select c from Cliente c where c.nome = :nome ")
    @Modifying
    void deleteByNome(@Param("nome") String nome);

    //boolean de nome
    Boolean existsByNome(String nome);

    @Query(value = " select c from Cliente c join fetch c.pedidos where c.id= :id ")
    Cliente findClienteFetchPedidos(@Param("id") Integer id);
}
