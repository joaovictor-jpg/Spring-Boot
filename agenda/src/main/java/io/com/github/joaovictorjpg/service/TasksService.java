package io.com.github.joaovictorjpg.service;

import io.com.github.joaovictorjpg.domen.entity.Tasks;
import io.com.github.joaovictorjpg.rest.dto.TasksDTO;
import io.com.github.joaovictorjpg.rest.dto.TasksResponseDTO;

import java.util.List;

public interface TasksService {

    TasksResponseDTO save(TasksDTO tasks);

    List<TasksResponseDTO> finByIdUser(Long idCliente);

    void delete(Long idDelete);

    void update(Long idTask, TasksDTO tasksDTO);
}
