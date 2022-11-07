package io.com.github.joaovictorjpg.service.imp;

import io.com.github.joaovictorjpg.domen.entity.User;
import io.com.github.joaovictorjpg.domen.repository.UserRepository;
import io.com.github.joaovictorjpg.rest.dto.PostUserDTO;
import io.com.github.joaovictorjpg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository repository;

    @Override
    public User save(PostUserDTO obj) {
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
}
