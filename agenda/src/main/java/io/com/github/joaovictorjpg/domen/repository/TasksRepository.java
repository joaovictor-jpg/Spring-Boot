package io.com.github.joaovictorjpg.domen.repository;

import io.com.github.joaovictorjpg.domen.entity.Tasks;
import io.com.github.joaovictorjpg.domen.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TasksRepository  extends JpaRepository<Tasks, Long> {
    List<Tasks> findByUser(@Param("id") User obj);
}
