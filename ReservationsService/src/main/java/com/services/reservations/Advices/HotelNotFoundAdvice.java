package com.services.reservations.Advices;

import com.services.reservations.Exceptions.HotelNotFoundException;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class HotelNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(HotelNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    JSONObject hotelNotFoundHandler(HotelNotFoundException ex) {
        JSONObject json = new JSONObject();
        json.put("status", HttpStatus.NOT_FOUND);
        json.put("message", ex.getMessage());
        return json;
    }
}

