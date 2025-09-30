package ru.userpetproject.exception;


public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(long id) {
        super("Пользователь с id " + id + " не найден");
    }
}
