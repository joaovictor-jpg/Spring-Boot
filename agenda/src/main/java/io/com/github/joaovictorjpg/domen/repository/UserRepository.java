package io.com.github.joaovictorjpg.domen.repository;

import io.com.github.joaovictorjpg.domen.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
