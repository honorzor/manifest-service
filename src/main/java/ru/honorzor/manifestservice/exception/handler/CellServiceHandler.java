package ru.honorzor.manifestservice.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.honorzor.manifestservice.dto.ResponseDTO;
import ru.honorzor.manifestservice.exception.cell.NotEnoughCountException;
import ru.honorzor.manifestservice.exception.cell.NotEqualCodeException;

@ControllerAdvice
@RestController
public class CellServiceHandler {
    @ExceptionHandler(value = NotEqualCodeException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseDTO notEqualCodeException(final NotEqualCodeException exception) {
        return new ResponseDTO(exception.getMessage(), 101);
    }

    @ExceptionHandler(value = NotEnoughCountException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDTO notEnoughCountException(final NotEnoughCountException exception) {
        return new ResponseDTO(exception.getMessage(), 102);
    }
}
