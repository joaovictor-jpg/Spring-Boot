package io.com.github.joaovictorjpg.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostUserLoginDTO {

    @NotBlank(message = "Campo email obrigatório")
    @Email(message = "Passe um email valido")
    private String email;
    @NotBlank(message = "senha orbigatorio")
    private String password;

}
