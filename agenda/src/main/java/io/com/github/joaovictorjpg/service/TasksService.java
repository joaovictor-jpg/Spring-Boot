package io.com.github.joaovictorjpg.service;

import io.com.github.joaovictorjpg.domen.entity.Tasks;
import io.com.github.joaovictorjpg.rest.dto.PostTasksCreate;

import java.util.List;

public interface TasksService {
    List<Tasks> findAll();

    Tasks save(PostTasksCreate tasks);
}
