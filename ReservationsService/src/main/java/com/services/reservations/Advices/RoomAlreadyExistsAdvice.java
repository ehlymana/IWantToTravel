package com.services.reservations.Advices;

import com.services.reservations.Exceptions.RoomAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RoomAlreadyExistsAdvice {
    @ResponseBody
    @ExceptionHandler(RoomAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String roomAlreadyExistsHandler(RoomAlreadyExistsException ex) {
        return ex.getMessage();
    }
}
