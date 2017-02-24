package com.crystal.cleanwaterandroidapplication.model;

/**
 * Checked Exception that is thrown whenever an account cannot be located/found.
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
