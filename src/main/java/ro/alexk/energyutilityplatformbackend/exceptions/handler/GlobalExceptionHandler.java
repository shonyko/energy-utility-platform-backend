package ro.alexk.energyutilityplatformbackend.exceptions.handler;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String usernameAlreadyExists(DataIntegrityViolationException e) {
        return e.getMessage();
    }

    @ExceptionHandler({IllegalArgumentException.class, ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String usernameAlreadyExists(Exception e) {
        return makePretty(e.getMessage());
    }

    private String makePretty(String msg) {
        var arr = msg.split(":", 2);
        return arr[arr.length - 1];
    }
}
