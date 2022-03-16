package ru.honorzor.manifestservice.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.honorzor.manifestservice.dto.ResponseDTO;
import ru.honorzor.manifestservice.exception.order.CannotFinishOrderException;

@ControllerAdvice
@RestController
public class OrderServiceHandler {
    @ExceptionHandler(value = CannotFinishOrderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDTO cannotFinishOrder(final CannotFinishOrderException cannotFinishOrderException) {
        return new ResponseDTO(cannotFinishOrderException.getMessage(), 201);
    }

}
