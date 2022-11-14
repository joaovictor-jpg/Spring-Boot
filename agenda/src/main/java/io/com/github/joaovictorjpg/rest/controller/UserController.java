package io.com.github.joaovictorjpg.rest.controller;

import io.com.github.joaovictorjpg.domen.entity.User;
import io.com.github.joaovictorjpg.rest.dto.PostUserLoginDTO;
import io.com.github.joaovictorjpg.rest.dto.PostUserSaveDTO;
import io.com.github.joaovictorjpg.rest.dto.UserDTO;
import io.com.github.joaovictorjpg.service.UserService;
import io.com.github.joaovictorjpg.service.imp.UserServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    UserService service;

    public UserController (UserServiceImp service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO save(@RequestBody @Valid PostUserSaveDTO obj) {
        return service.save(obj);

    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO login(@RequestBody @Valid PostUserLoginDTO obj) {
        return service.findByEmail(obj);
    }

}
