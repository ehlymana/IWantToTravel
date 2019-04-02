package com.services.reservations.Advices;

import com.services.reservations.Exceptions.UserDoesntExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserDoesntExistAdvice {
    @ResponseBody
    @ExceptionHandler(UserDoesntExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String userDoesntExistHandler(UserDoesntExistException ex) {
        return ex.getMessage();
    }
}
