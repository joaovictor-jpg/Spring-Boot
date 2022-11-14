package io.com.github.joaovictorjpg.service.imp;

import io.com.github.joaovictorjpg.domen.entity.Tasks;
import io.com.github.joaovictorjpg.domen.entity.User;
import io.com.github.joaovictorjpg.domen.repository.TasksRepository;
import io.com.github.joaovictorjpg.exception.UserNotFound;
import io.com.github.joaovictorjpg.rest.dto.TasksDTO;
import io.com.github.joaovictorjpg.rest.dto.TasksResponseDTO;
import io.com.github.joaovictorjpg.service.TasksService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TasksServiceImp implements TasksService {

    private final TasksRepository repository;
    private final UserServiceImp userServiceImp;

    @Override
    @Transactional
    public TasksResponseDTO save(TasksDTO obj) {
        Tasks tasks = TasksDTOToTasks(obj);
        repository.save(tasks);
        return taskToTaskResponseDTO(tasks);
    }


    @Override
    public List<TasksResponseDTO> finByIdUser(Long id) {

        User user = userServiceImp.findById(id);
        List<Tasks> tasks = repository.findByUser(user);
        return listTasksToTaskResponseDTO(tasks);
    }


    @Override
    public void update(Long idTask, TasksDTO tasksDTO) {

        Tasks tasksWhithId = repository.findById(idTask).orElseThrow(() -> new UserNotFound("task not found"));

        Tasks tasks = TasksDTOToTasks(tasksDTO);
        tasks.setId(tasksWhithId.getId());

        repository.save(tasks);

    }

    @Override
    @Transactional
    public void delete(Long idDelete) {
        Tasks tasks = repository.findById(idDelete).orElseThrow(() -> new UserNotFound("task not found"));
        repository.delete(tasks);
    }


    private Tasks TasksDTOToTasks(TasksDTO obj) {
        return Tasks
                .builder()
                .title(obj.getTitle())
                .description(obj.getDescription())
                .date( LocalDateTime.parse(
                            obj.getDate(),
                            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                )
                .user( userServiceImp.findById(obj.getUser()) )
                .build();
    }
    private TasksResponseDTO taskToTaskResponseDTO(Tasks tasks) {
        return TasksResponseDTO
                .builder()
                .title(tasks.getTitle())
                .description(tasks.getDescription())
                .date(tasks.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")))
                .build();
    }
    private List<TasksResponseDTO> listTasksToTaskResponseDTO(List<Tasks> tasks) {
        return tasks.stream()
                .map(task -> taskToTaskResponseDTO(task))
                .collect(Collectors.toList());
    }
}
