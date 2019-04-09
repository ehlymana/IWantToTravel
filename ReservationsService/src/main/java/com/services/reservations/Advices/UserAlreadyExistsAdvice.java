package com.services.reservations.Advices;

import com.services.reservations.Exceptions.UserAlreadyExistsException;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserAlreadyExistsAdvice {
    @ResponseBody
    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    JSONObject userAlreadyExistsHandler(UserAlreadyExistsException ex) {
        JSONObject json = new JSONObject();
        json.put("status", HttpStatus.BAD_REQUEST);
        json.put("message", ex.getMessage());
        return json;
    }
}