package ru.userpetproject.exception;


import org.springframework.validation.FieldError;

public class UserValidationException extends RuntimeException {

    public UserValidationException(String message) {
        super(message);
    }

    public UserValidationException(FieldError errorField) {
        super("Поле " + errorField.getField() + " нарушает ограничение: " + errorField.getDefaultMessage());
    }
}
