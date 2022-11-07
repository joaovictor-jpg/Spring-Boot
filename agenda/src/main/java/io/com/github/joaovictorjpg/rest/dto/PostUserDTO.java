package io.com.github.joaovictorjpg.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostUserDTO {
    @NotBlank(message = "Primeiro nome obrigatório")
    private String firstName;
    @NotBlank(message = "Sobre nome obrigatório")
    private String lestName;
    @NotBlank(message = "Campo email obrigatório")
    @Email(message = "Passe um email valido")
    private String email;
    @NotBlank(message = "senha orbigatorio")
    private String password;
    @NotBlank(message = "Campo data de nascimento obrigatório.")
    private String birthDate;
}
