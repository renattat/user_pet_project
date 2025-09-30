package ru.userpetproject.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.userpetproject.exception.UserNotFoundException;
import ru.userpetproject.exception.UserValidationException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public void handleException(RuntimeException ex) {
        log.error(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(UserValidationException.class)
    public void handleUserValidationException(RuntimeException ex) {
        log.error(ex.getMessage());
    }
}
