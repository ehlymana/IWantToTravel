package com.services.reservations.Exceptions;

public class HotelAlreadyExistsException extends RuntimeException {
    public HotelAlreadyExistsException(Long id) {
        super("Hotel with ID: " + id + " already exists!");
    }
}
