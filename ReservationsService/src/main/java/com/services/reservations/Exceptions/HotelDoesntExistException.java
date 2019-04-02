package com.services.reservations.Exceptions;

public class HotelDoesntExistException extends RuntimeException {
    public HotelDoesntExistException(long id) {
        super("Hotel with ID: " + id + " doesn't exist!");
    }
}
