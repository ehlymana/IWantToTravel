package com.services.reservations.Advices;

import com.services.reservations.Exceptions.ReservationAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ReservationAlreadyExistsAdvice {
    @ResponseBody
    @ExceptionHandler(ReservationAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String reservationAlreadyExistsHandler(ReservationAlreadyExistsException ex) {
        return ex.getMessage();
    }
}

