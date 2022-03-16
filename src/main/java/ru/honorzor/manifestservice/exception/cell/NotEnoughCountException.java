package ru.honorzor.manifestservice.exception.cell;

public class NotEnoughCountException extends RuntimeException {
    public NotEnoughCountException(String message) {
        super(message);
    }
}
