package io.com.github.joaovictorjpg.service;

import io.com.github.joaovictorjpg.domen.entity.User;
import io.com.github.joaovictorjpg.rest.dto.PostUserDTO;

public interface UserService {
    User save(PostUserDTO obj);
}
