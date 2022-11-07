package io.com.github.joaovictorjpg.rest.controller;

import io.com.github.joaovictorjpg.domen.entity.Usuario;
import io.com.github.joaovictorjpg.exception.SenhaInvalidaException;
import io.com.github.joaovictorjpg.rest.dto.CredenciaisDTO;
import io.com.github.joaovictorjpg.rest.dto.TokenDTO;
import io.com.github.joaovictorjpg.security.jwt.JwtService;
import io.com.github.joaovictorjpg.service.imp.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UserServiceImpl userService;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario save(@RequestBody @Valid Usuario usuario) {
        String senhaCriptografada = encoder.encode(usuario.getPassword());
        usuario.setPassword(senhaCriptografada);
        return userService.save(usuario);
    }

    @PostMapping("/auth")
    public TokenDTO auntenticar(@RequestBody CredenciaisDTO credenciaisDTO) {
        try{
            Usuario usuario = Usuario
                    .builder()
                    .login(credenciaisDTO.getLogin())
                    .password(credenciaisDTO.getPassword())
                    .build();
            UserDetails userDetails = userService.autenticar(usuario);
            String token = jwtService.gerarToken(usuario);
            return new TokenDTO(usuario.getLogin(), token);
        }catch (UsernameNotFoundException | SenhaInvalidaException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

}
