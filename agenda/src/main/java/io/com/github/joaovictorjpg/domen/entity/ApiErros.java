package io.com.github.joaovictorjpg.domen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiErros {

    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private List<String> messagens = new ArrayList<>();
    private String path;

}
