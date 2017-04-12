package com.crystal.cleanwaterandroidapplication.model;

/**
 * Thrown whenever an account cannot be located/found.
 * @author Team 62
 * @see AccountManager
 */
public class AccountDoesNotExistException extends Exception {

    /**
     * Creates a new AccountDoesNotExistException with default message "Account does not exist"
     */
    public AccountDoesNotExistException() {
        super("Account does not exist");
    }

    /**
     * Creates a new AccountDoesNotExistException with a unique message which is passed
     * in as a parameter
     * @param message the message to be displayed when the exception occurs
     */
    public AccountDoesNotExistException(String message) {
        super(message);
    }
}
