package com.services.reservations.Exceptions;

public class HotelNotFoundException extends RuntimeException {
    public HotelNotFoundException(long id) {
        super("Hotel with ID: " + id + " not found!");
    }
}
