package io.com.github.joaovictorjpg.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TasksDTO {

    @NotBlank(message = "Campo Obrigatório título")
    @Column(length = 55)
    private String title;
    @NotBlank(message = "Campo Obrigatório descrição")
    private String description;
    @NotBlank(message = "Campo Obrigatório data")
    private String date;
    private Long user;

}
