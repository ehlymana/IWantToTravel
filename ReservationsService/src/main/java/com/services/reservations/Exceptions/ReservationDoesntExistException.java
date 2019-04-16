package com.services.reservations.Exceptions;

public class ReservationDoesntExistException extends RuntimeException {
    public ReservationDoesntExistException(long id) {
        super("Reservation with ID: " + id + " doesn't exist!");
    }
}
