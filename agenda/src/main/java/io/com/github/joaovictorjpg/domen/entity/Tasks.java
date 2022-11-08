package io.com.github.joaovictorjpg.domen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    @Column(length = 55)
    private String title;
    @NotBlank
    private String description;
    @FutureOrPresent
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
