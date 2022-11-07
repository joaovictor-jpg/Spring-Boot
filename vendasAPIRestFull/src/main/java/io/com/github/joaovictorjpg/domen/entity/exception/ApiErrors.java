package io.com.github.joaovictorjpg.domen.entity.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class ApiErrors {

    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private List<String> messagens = new ArrayList<>();
    private String path;

    public ApiErrors(LocalDateTime timestamp, Integer status, String error, String messagens, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.messagens = Arrays.asList(messagens);
        this.path = path;
    }
    public ApiErrors(LocalDateTime timestamp, Integer status, String error, List<String> messagens, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.messagens = messagens;
        this.path = path;
    }


}
