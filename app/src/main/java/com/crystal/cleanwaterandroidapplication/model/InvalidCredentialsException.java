package com.crystal.cleanwaterandroidapplication.model;

/**
 * Thrown whenever an invalid login attempt occurs.
 * @author Team 62
 */
public class InvalidCredentialsException extends Exception {
    public InvalidCredentialsException() {
        //Do nothing
    }

    public InvalidCredentialsException(String message) {
        super(message);
    }
}
