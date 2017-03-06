package com.crystal.cleanwaterandroidapplication.model;

/**
 * Thrown whenever an account cannot be located/found.
 * @author Team 62
 * @see AccountManager
 */
public class AccountDoesNotExistException extends Exception {
    public AccountDoesNotExistException() {
        //Do nothing
    }

    public AccountDoesNotExistException(String message) {
        super(message);
    }
}
