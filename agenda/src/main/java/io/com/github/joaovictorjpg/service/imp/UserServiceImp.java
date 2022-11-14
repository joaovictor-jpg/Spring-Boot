package io.com.github.joaovictorjpg.service.imp;

import io.com.github.joaovictorjpg.domen.entity.User;
import io.com.github.joaovictorjpg.domen.repository.UserRepository;
import io.com.github.joaovictorjpg.exception.UserNotFound;
import io.com.github.joaovictorjpg.rest.dto.PostUserLoginDTO;
import io.com.github.joaovictorjpg.rest.dto.PostUserSaveDTO;
import io.com.github.joaovictorjpg.rest.dto.UserDTO;
import io.com.github.joaovictorjpg.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository repository;

    @Override
    public UserDTO save(PostUserSaveDTO obj) {
        User user = User
                .builder()
                .firstName(obj.getFirstName())
                .lestName(obj.getLestName())
                .email(obj.getEmail())
                .password(obj.getPassword())
                .birthDate(LocalDate.parse(obj.getBirthDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .build();
        repository.save(user);
        return userToUserDTO(user);
    }

    @Override
    public UserDTO findByEmail(PostUserLoginDTO obj) {
        User user = repository.findByEmail(obj.getEmail()).orElseThrow(() -> new UserNotFound("Usuario não encotrado"));
        return userToUserDTO(user);
    }


    public User findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new UserNotFound("Usuário do ID: " + id + ", não foi encontrado."));
    }
    private UserDTO userToUserDTO(User user) {
        return UserDTO.builder()
                .nickName(user.getFirstName())
                .Login(user.getEmail())
                .build();
    }
}
