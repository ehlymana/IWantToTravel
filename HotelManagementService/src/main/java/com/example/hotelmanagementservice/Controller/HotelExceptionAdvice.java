package com.example.hotelmanagementservice.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class HotelExceptionAdvice {
    @ResponseBody
    @ExceptionHandler(HotelException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String hotelHandler(HotelException ex) {
        return ex.getMessage();
    }
}