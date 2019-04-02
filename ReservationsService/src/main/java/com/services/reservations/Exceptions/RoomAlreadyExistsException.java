package com.services.reservations.Exceptions;

public class RoomAlreadyExistsException extends RuntimeException {
    public RoomAlreadyExistsException(long id) {
        super("Room with ID: " + id + " already exists!");
    }
}
