package com.nwt.ums.Exceptions;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserExceptionAdvice {
    @ResponseBody
    @ExceptionHandler(UserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    JSONObject userAlreadyExists(UserException exception) {
        JSONObject json = new JSONObject();
        try {
            json.put("status", HttpStatus.BAD_REQUEST);
            json.put("message", exception.getMessage());
        } catch (JSONException jException) {
            System.out.println(jException.getMessage());
        }
        return json;
    }
}
