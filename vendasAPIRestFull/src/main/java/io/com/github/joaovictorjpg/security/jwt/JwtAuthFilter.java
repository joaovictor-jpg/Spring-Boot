package io.com.github.joaovictorjpg.security.jwt;

import io.com.github.joaovictorjpg.service.imp.UserServiceImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthFilter  extends OncePerRequestFilter {

    private JwtService jwtService;
    private UserServiceImpl userService;

    public JwtAuthFilter(JwtService jwtService, UserServiceImpl userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

            String authrization = request.getHeader("Authorization");

            if( authrization != null && authrization.startsWith("Bearer")) {
                String token = authrization.split(" ")[1];
                boolean isValid = jwtService.tokenValido(token);

                if(isValid) {
                    String loginUsuario = jwtService.obterLoginUsuario(token);
                    UserDetails userDetails = userService.loadUserByUsername(loginUsuario);
                    UsernamePasswordAuthenticationToken user =
                            new UsernamePasswordAuthenticationToken(userDetails, null,
                                    userDetails.getAuthorities());
                    user.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    SecurityContextHolder.getContext().setAuthentication(user);
                }

            }

            filterChain.doFilter(request, response);

    }
}
