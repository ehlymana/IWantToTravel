package com.services.reservations.Advices;

import com.services.reservations.Exceptions.UserDoesntExistException;
import net.minidev.json.JSONObject;
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
    JSONObject userDoesntExistHandler(UserDoesntExistException ex) {
        JSONObject json = new JSONObject();
        json.put("status", HttpStatus.NOT_FOUND);
        json.put("message", ex.getMessage());
        return json;
    }
}
