package io.github.joaovictorjpg.domen.localizacao.service;

import io.github.joaovictorjpg.domen.localizacao.domen.entity.Cidade;
import io.github.joaovictorjpg.domen.localizacao.domen.repository.CidadeRepository;
import static io.github.joaovictorjpg.domen.localizacao.service.specs.CidadeSpecs.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.swing.text.html.HTMLDocument;

@Service
public class CidadeService {

    private CidadeRepository repository;

    public CidadeService(CidadeRepository repository) {

        this.repository = repository;
    }

    public void salvar() {
        repository.save(new Cidade(1L, "São Paulo", 12396372L));
    }

    public void listaCidade() {
        repository.findAll().forEach(System.out::println);
    }
    public void listaCidadePorNome() {
        System.out.println("Lista por nome");
        repository.findByNome("Palmas").forEach(System.out::println);
        System.out.println("Busca pelo nome começando por aquele pedaço");
        repository.findByNomeStartingWith("Por").forEach(System.out::println);
        System.out.println("Busca pelo nome terminando por aquele peço");
        repository.findByNomeEndingWith("al").forEach(System.out::println);
        System.out.println("Busca pelo nome contendo aquele pedaço");
        repository.findByNomeContaining("a").forEach(System.out::println);
        System.out.println("Busca pelo nome like");
        repository.findByNomeLike("porto%").forEach(System.out::println);
    }
    public void ListaPorNumerosDeHabitantes() {
        System.out.println("Lista por quantidade exato:");
        repository.findByHabitantes(12396372L).forEach(System.out::println);
        System.out.println("Menor que");
        repository.findByHabitantesLessThan(1000001L).forEach(System.out::println);
        System.out.println("Maior que");
        repository.findByHabitantesGreaterThan(1000001L).forEach(System.out::println);
        System.out.println("Menor ou igual a");
        repository.findByHabitantesLessThanEqual(1000000L).forEach(System.out::println);

        System.out.println("Dois ou mais parametros");
        repository.findByHabitantesLessThanAndNomeLike(1000001L, "Br%").forEach(System.out::println);
    }

    public void listaCidadePorHabitantes() {
        repository.findByHabitantes(78978979L).forEach(System.out::println);
    }

    public void ListarPorNomeEOrdenarPorHabitantes() {
        repository
                .findByNomeLike("Porto%", Sort.by("habitantes"))
                .forEach(System.out::println);
    }

    public void ListarPorNomeEPagina() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by("habitantes"));
        repository
                .findByNomeLike("%%%", pageable)
                .forEach(System.out::println);
    }

    public void ListaPorExample(Cidade cidade) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(cidade, matcher);

        Pageable pageable = PageRequest.of(0, 5, Sort.by("Habitantes"));

        repository.findAll(example, pageable)
                .forEach(System.out::println);
    }

    public void listarCidadesByNameSpec() {

        Pageable pageable = PageRequest.of(0, 5, Sort.by("Habitantes"));

        repository
                .findAll(nomeEqual("São Paulo")
                        .and(habitantesGreater(12396371L)), pageable)
                .forEach(System.out::println);

    }

    public void listarCidadesSpecsFiltroDinamico(Cidade filter) {
        Specification<Cidade> specs = Specification.where((root, query, cb) -> cb.conjunction());

        if(filter.getId() != null) {
            specs =  specs.and(idEqual(filter.getId()));
        }

        if(StringUtils.hasText(filter.getNome())) {
            specs = specs.and(nomeLike(filter.getNome()));
        }

        if(filter.getHabitantes() != null) {
            specs = specs.and(habitantesGreater(filter.getHabitantes()));
        }

        Pageable pageable = PageRequest.of(0 ,5, Sort.by("Nome"));

        repository.findAll(specs).forEach(System.out::println);

    }

    public void listaCidadeComSqlNativo() {
        repository
                .findByNomeSqlNativo("Rio de Janeiro")
                .stream().map(cidadeProjection -> new Cidade(cidadeProjection.getId(), cidadeProjection.getNome(), null))
                .forEach(System.out::println);
    }

}
