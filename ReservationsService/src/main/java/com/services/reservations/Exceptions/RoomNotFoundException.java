package com.services.reservations.Exceptions;

public class RoomNotFoundException extends RuntimeException {
    public RoomNotFoundException(long id) {
        super("Room with ID: " + id + " not found!");
    }
}
