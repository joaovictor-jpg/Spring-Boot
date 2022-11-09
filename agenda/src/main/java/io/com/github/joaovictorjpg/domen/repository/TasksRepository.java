package io.com.github.joaovictorjpg.domen.repository;

import io.com.github.joaovictorjpg.domen.entity.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasksRepository  extends JpaRepository<Tasks, Long> {
}
