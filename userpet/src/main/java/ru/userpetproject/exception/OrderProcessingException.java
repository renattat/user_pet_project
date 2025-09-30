package ru.userpetproject.exception;

public class OrderProcessingException extends RuntimeException {

    public OrderProcessingException(String message) {
        super(message);
    }

    public OrderProcessingException(String message, String e) {
        super("Error processing user event creation: " + e);
    }
}
