package io.com.github.joaovictorjpg.service;

import io.com.github.joaovictorjpg.rest.dto.PostUserLoginDTO;
import io.com.github.joaovictorjpg.rest.dto.PostUserSaveDTO;
import io.com.github.joaovictorjpg.rest.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDTO save(PostUserSaveDTO obj);

    UserDTO findByEmail(PostUserLoginDTO obj);
}
