package io.com.github.joaovictorjpg.service;

import io.com.github.joaovictorjpg.domen.entity.User;
import io.com.github.joaovictorjpg.rest.dto.PostUserLoginDTO;
import io.com.github.joaovictorjpg.rest.dto.PostUserSaveDTO;

public interface UserService {
    User save(PostUserSaveDTO obj);

    User findByEmail(PostUserLoginDTO obj);
}
