package io.com.github.joaovictorjpg.service;

import io.com.github.joaovictorjpg.domen.entity.Tasks;
import io.com.github.joaovictorjpg.rest.dto.TasksDTO;

import java.util.List;

public interface TasksService {

    Tasks save(TasksDTO tasks);

    List<Tasks> finByIdUser(Long idCliente);

    void delete(Long idDelete);

    void update(Long idTask, TasksDTO tasksDTO);
}
