package com.crystal.cleanwaterandroidapplication.model;

/**
 * Created by RobertCooper on 2/28/17.
 */

public class AccountNotLoggedInException extends Exception {
    public AccountNotLoggedInException() {
        //Do nothing
    }

    public AccountNotLoggedInException(String message) {
        super(message);
    }
}
