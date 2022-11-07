package io.com.github.joaovictorjpg.exception;

public class SenhaInvalidaException extends RuntimeException {
    public SenhaInvalidaException() {
        super("Senha Inválida");
    }
}
