package io.com.github.joaovictorjpg.rest.controller;

import io.com.github.joaovictorjpg.domen.entity.User;
import io.com.github.joaovictorjpg.rest.dto.PostUserLoginDTO;
import io.com.github.joaovictorjpg.rest.dto.PostUserSaveDTO;
import io.com.github.joaovictorjpg.service.UserService;
import io.com.github.joaovictorjpg.service.imp.UserServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    UserService service;

    public UserController (UserServiceImp service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody PostUserSaveDTO obj) {
        return service.save(obj);

    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public User login(@RequestBody PostUserLoginDTO obj) {
        return service.findByEmail(obj);
    }

}
