package com.services.reservations.Advices;

import com.services.reservations.Exceptions.ReservationAlreadyExistsException;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ReservationAlreadyExistsAdvice {
    @ResponseBody
    @ExceptionHandler(ReservationAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    JSONObject reservationAlreadyExistsHandler(ReservationAlreadyExistsException ex) {
        JSONObject json = new JSONObject();
        json.put("status", HttpStatus.BAD_REQUEST);
        json.put("message", ex.getMessage());
        return json;
    }
}

