package io.github.joaovictorjpg.domen.localizacao.service.specs;

import io.github.joaovictorjpg.domen.localizacao.domen.entity.Cidade;
import org.springframework.data.jpa.domain.Specification;

public abstract class CidadeSpecs {

    public static Specification<Cidade> propertyEqual(String prop, Object value) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("prop"), value);
    }

    public static Specification<Cidade> idEqual(Long id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id);
    }

    public static Specification<Cidade> nomeEqual(String nome) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("nome"), nome);
    }

    public static Specification<Cidade> habitantesGreater(Long value) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("habitantes"), value);
    }

    public static Specification<Cidade> habitantesBetwenn(Long min, Long max) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("habitantes"), min, max);
    }

    public static Specification<Cidade> nomeLike(String nome) {
        return (root, query, cb) -> cb.like(cb.upper(root.get("nome")), "%" + nome + "%".toUpperCase());
    }

}
