package ru.honorzor.manifestservice.exception.order;

public class CannotFinishOrderException extends RuntimeException{
    public CannotFinishOrderException(String message) {
        super(message);
    }
}
