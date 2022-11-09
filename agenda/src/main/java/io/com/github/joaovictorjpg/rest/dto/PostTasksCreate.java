package io.com.github.joaovictorjpg.rest.dto;

import io.com.github.joaovictorjpg.domen.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostTasksCreate {

    @NotBlank(message = "Campo Obrigatório título")
    @Column(length = 55)
    private String title;
    @NotBlank(message = "Campo Obrigatório descrição")
    private String description;
    @NotBlank(message = "Campo Obrigatório data")
    private String date;
    private Long user;

}
