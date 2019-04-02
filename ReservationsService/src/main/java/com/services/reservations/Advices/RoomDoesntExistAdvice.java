package com.services.reservations.Advices;

import com.services.reservations.Exceptions.RoomDoesntExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RoomDoesntExistAdvice {
    @ResponseBody
    @ExceptionHandler(RoomDoesntExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String roomDoesntExistHandler(RoomDoesntExistException ex) {
        return ex.getMessage();
    }
}
