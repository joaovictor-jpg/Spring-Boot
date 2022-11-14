package io.com.github.joaovictorjpg.rest.controller;

import io.com.github.joaovictorjpg.domen.entity.Tasks;
import io.com.github.joaovictorjpg.rest.dto.TasksDTO;
import io.com.github.joaovictorjpg.rest.dto.TasksResponseDTO;
import io.com.github.joaovictorjpg.service.TasksService;
import io.com.github.joaovictorjpg.service.imp.TasksServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TasksController {

    private TasksService service;

    public TasksController (TasksServiceImp tasksServiceImp) {
        this.service = tasksServiceImp;
    }

    @GetMapping("/{idUser}")
    public List<TasksResponseDTO> findByIdUser(@PathVariable Long idUser) {
        return service.finByIdUser(idUser);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public TasksResponseDTO save(@RequestBody @Valid TasksDTO tasks) {
        return service.save(tasks);
    }

    @DeleteMapping("/{idDelete}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long idDelete) {
        service.delete(idDelete);
    }

    @PutMapping("/{idTask}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long idTask, @RequestBody TasksDTO tasksDTO) {
        service.update(idTask, tasksDTO);
    }

}
