package io.github.joaovictorjpg.domen.localizacao.domen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_cidade")
public class Cidade {

    @Id
    @Column(name = "id_cidade")
    private Long id;
    @Column(name = "nome", length = 50)
    private String nome;
    @Column(name = "qtd_habitantes")
    private Long habitantes;

}
