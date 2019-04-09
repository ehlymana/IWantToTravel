package com.services.reservations.Advices;

import com.services.reservations.Exceptions.RoomAlreadyExistsException;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RoomAlreadyExistsAdvice {
    @ResponseBody
    @ExceptionHandler(RoomAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    JSONObject roomAlreadyExistsHandler(RoomAlreadyExistsException ex) {
        JSONObject json = new JSONObject();
        json.put("status", HttpStatus.BAD_REQUEST);
        json.put("message", ex.getMessage());
        return json;
    }
}
