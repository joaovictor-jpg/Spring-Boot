package io.com.github.joaovictorjpg.domen.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "{campo.login.obrigatorio}")
    private String login;
    @NotEmpty(message = "{campo.senha.obrigatorio}")
    @Column(name = "senha")
    private String password;
    private boolean admin;

}
