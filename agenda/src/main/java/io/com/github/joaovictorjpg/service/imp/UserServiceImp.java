package io.com.github.joaovictorjpg.service.imp;

import io.com.github.joaovictorjpg.domen.entity.User;
import io.com.github.joaovictorjpg.domen.repository.UserRepository;
import io.com.github.joaovictorjpg.exception.UserNotFound;
import io.com.github.joaovictorjpg.rest.dto.PostUserLoginDTO;
import io.com.github.joaovictorjpg.rest.dto.PostUserSaveDTO;
import io.com.github.joaovictorjpg.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository repository;

    @Override
    public User save(PostUserSaveDTO obj) {
        User user = User
                .builder()
                .firstName(obj.getFirstName())
                .lestName(obj.getLestName())
                .email(obj.getEmail())
                .password(obj.getPassword())
                .birthDate(LocalDate.parse(obj.getBirthDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .build();
        return repository.save(user);
    }

    @Override
    public User findByEmail(PostUserLoginDTO obj) {
        return repository.findByEmail(obj.getEmail()).orElseThrow(() -> new UserNotFound("Usuario não encotrado"));

    }
}