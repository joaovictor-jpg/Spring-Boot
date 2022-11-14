package io.com.github.joaovictorjpg.service;

import io.com.github.joaovictorjpg.domen.entity.User;
import io.com.github.joaovictorjpg.rest.dto.PostUserLoginDTO;
import io.com.github.joaovictorjpg.rest.dto.PostUserSaveDTO;
import io.com.github.joaovictorjpg.rest.dto.UserDTO;

public interface UserService {
    User save(PostUserSaveDTO obj);

    UserDTO findByEmail(PostUserLoginDTO obj);
}
