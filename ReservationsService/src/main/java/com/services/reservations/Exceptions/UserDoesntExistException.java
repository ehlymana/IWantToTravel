package com.services.reservations.Exceptions;

public class UserDoesntExistException extends RuntimeException {
    public UserDoesntExistException(long id) {
        super("User with ID: " + id + " doesn't exist!");
    }
}
