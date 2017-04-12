package com.crystal.cleanwaterandroidapplication.model;

/**
 * Thrown whenever an invalid login attempt occurs.
 * @author Team 62
 */
public class InvalidCredentialsException extends Exception {

    /**
     * Creates an InvalidCredentialsException with default message 'Invalid Credentials Detected!'.
     */
    public InvalidCredentialsException() {
        super("Invalid Credentials Detected!");
    }

    /**
     * Creates an InvalidCredentialsExceptions with an unique message that is passed in
     * @param message message to be displayed when the exception occurs
     */
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
