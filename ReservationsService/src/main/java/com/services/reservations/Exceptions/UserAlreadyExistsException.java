package com.services.reservations.Exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(Long id) {
        super("User with ID: " + id + " already exists!");
    }
}
