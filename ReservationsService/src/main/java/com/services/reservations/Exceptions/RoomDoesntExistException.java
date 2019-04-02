package com.services.reservations.Exceptions;

public class RoomDoesntExistException extends RuntimeException {
    public RoomDoesntExistException(long id) {
        super("Room with ID: " + id + " doesn't exist!");
    }
}
