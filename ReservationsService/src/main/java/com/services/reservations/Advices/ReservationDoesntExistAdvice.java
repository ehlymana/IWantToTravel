package com.services.reservations.Advices;

import com.services.reservations.Exceptions.ReservationDoesntExistException;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ReservationDoesntExistAdvice {
    @ResponseBody
    @ExceptionHandler(ReservationDoesntExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    JSONObject reservationDoesntExistHandler(ReservationDoesntExistException ex) {
        JSONObject json = new JSONObject();
        json.put("status", HttpStatus.NOT_FOUND);
        json.put("message", ex.getMessage());
        return json;
    }
}
