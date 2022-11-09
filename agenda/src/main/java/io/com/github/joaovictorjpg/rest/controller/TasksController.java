package io.com.github.joaovictorjpg.rest.controller;

import io.com.github.joaovictorjpg.domen.entity.Tasks;
import io.com.github.joaovictorjpg.rest.dto.PostTasksCreate;
import io.com.github.joaovictorjpg.service.TasksService;
import io.com.github.joaovictorjpg.service.imp.TasksServiceImp;
import lombok.RequiredArgsConstructor;
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

    @GetMapping
    public List<Tasks> findAll() {
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Tasks save(@RequestBody @Valid PostTasksCreate tasks) {
        return service.save(tasks);
    }

}
