package io.com.github.joaovictorjpg.service.imp;

import io.com.github.joaovictorjpg.domen.entity.Tasks;
import io.com.github.joaovictorjpg.domen.entity.User;
import io.com.github.joaovictorjpg.domen.repository.TasksRepository;
import io.com.github.joaovictorjpg.rest.dto.PostTasksCreate;
import io.com.github.joaovictorjpg.service.TasksService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TasksServiceImp implements TasksService {

    private final TasksRepository repository;
    private final UserServiceImp userServiceImp;

    @Override
    public List<Tasks> findAll() {
        return repository.findAll();
    }

    @Override
    public Tasks save(PostTasksCreate obj) {
        User user = userServiceImp.findById(obj.getUser());
        Tasks tasks = Tasks
                .builder()
                .title(obj.getTitle())
                .description(obj.getDescription())
                .date(LocalDateTime.parse(obj.getDate(),
                        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")))
                .user(user)
                .build();
        return repository.save(tasks);
    }
}
