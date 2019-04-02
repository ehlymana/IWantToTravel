package com.services.reservations.Exceptions;

public class ReservationAlreadyExistsException extends RuntimeException {
    public ReservationAlreadyExistsException(Long id) {
        super("Reservation with ID: " + id + " already exists!");
    }
}
