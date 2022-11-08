package io.com.github.joaovictorjpg.domen.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Primeiro nome obrigatório")
    @Column(name = "first_name", length = 50)
    private String firstName;
    @NotBlank(message = "Sobre nome obrigatório")
    @Column(name = "last_name", length = 50)
    private String lestName;
    @NotBlank(message = "Campo email obrigatório")
    @Email(message = "Passe um email valido")
    @Column(name = "email", length = 55)
    private String email;
    @NotBlank(message = "senha orbigatorio")
    @Column(name = "password")
    private String password;
    @Past(message = "Campo data inválido")
    @Column(name = "birth_data")
    private LocalDate birthDate;
    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Tasks> tasksList = new ArrayList<>();

}
