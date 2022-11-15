package io.com.github.joaovictorjpg.exception;

public class AlreadyExistingEmail extends RuntimeException{
    public AlreadyExistingEmail(String message) {
        super(message);
    }
}
