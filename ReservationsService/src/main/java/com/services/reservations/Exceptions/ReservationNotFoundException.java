package com.services.reservations.Exceptions;

public class ReservationNotFoundException extends RuntimeException {
    public ReservationNotFoundException(long id) {
        super("Reservation with ID: " + id + " not found!");
    }
}
