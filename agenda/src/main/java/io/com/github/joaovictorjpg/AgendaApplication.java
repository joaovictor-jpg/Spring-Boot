package io.com.github.joaovictorjpg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AgendaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgendaApplication.class, args);
    }
}
