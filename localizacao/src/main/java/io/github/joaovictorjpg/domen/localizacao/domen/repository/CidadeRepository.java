package io.github.joaovictorjpg.domen.localizacao.domen.repository;

import io.github.joaovictorjpg.domen.localizacao.domen.entity.Cidade;
import io.github.joaovictorjpg.domen.localizacao.domen.repository.projections.CidadeProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Long>, JpaSpecificationExecutor<Cidade> {


    @Query(nativeQuery = true, value = "select c.id_cidade as id, c.nome from tb_cidade as c where c.nome = :nome")
    List<CidadeProjection> findByNomeSqlNativo(@Param("nome") String nome);

    //Busca pelo nome exato da cidade
    List<Cidade> findByNome(String nome);

    //Busca pelo nome like
    @Query(" select c from Cidade c where upper(c.nome) like upper(?1) ")
    List<Cidade> findByNomeLike(String nome);

    //Busca pelo nome like e Sort
    @Query(" select c from Cidade c where upper(c.nome) like upper(?1) ")
    List<Cidade> findByNomeLike(String nome, Sort sort);

    //Busca pelo nome like e Pagina
    @Query(" select c from Cidade c where upper(c.nome) like upper(?1) ")
    Page<Cidade> findByNomeLike(String nome, Pageable pageable);


    //Busca pelo nome começando por aquele pedaço
    List<Cidade> findByNomeStartingWith(String nome);

    //Busca pelo nome terminando por aquele peço
    List<Cidade> findByNomeEndingWith(String nome);

    //Busca pelo nome contendo aquele pedaço
    List<Cidade> findByNomeContaining(String nome);

    List<Cidade> findByHabitantes(Long habitantes);

    //Menor que
    List<Cidade> findByHabitantesLessThan(Long habitantes);
    //Maior que
    List<Cidade> findByHabitantesGreaterThan(Long habitantes);
    //Menor ou igual a
    List<Cidade> findByHabitantesLessThanEqual(Long habitantes);

    List<Cidade> findByHabitantesLessThanAndNomeLike(Long habitantes, String nome);
}
