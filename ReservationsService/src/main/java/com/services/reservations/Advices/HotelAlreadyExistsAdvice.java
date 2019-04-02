package com.services.reservations.Advices;

import com.services.reservations.Exceptions.HotelAlreadyExistsException;
import com.services.reservations.Exceptions.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class HotelAlreadyExistsAdvice {
    @ResponseBody
    @ExceptionHandler(HotelAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String hotelAlreadyExistsHandler(HotelAlreadyExistsException ex) {
        return ex.getMessage();
    }
}
