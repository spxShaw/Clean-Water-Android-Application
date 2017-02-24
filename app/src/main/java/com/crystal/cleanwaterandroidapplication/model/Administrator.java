package com.crystal.cleanwaterandroidapplication.model;

import com.crystal.cleanwaterandroidapplication.model.Account;

/**
 * Administrator Account.
 * @author Team 62
 * @see Account
 */
public class Administrator extends Account {

    /**
     * Creates a Administrator Account, given a username and password.
     * @param username Username of User
     * @param password Password of User
     */
    public Administrator(String username, String password) {
        super(username, password);
    }

    /**
     * Returns a string representation of the account, in format: <br/>
     * Account -- Username: 'username' Name: 'full name' Account ID: 'ID'
     * Permissions: 'ListOfPermissions'
     * @return String representation of the account.
     */
    @Override
    public String toString() {
        return super.toString() + "Permissions: Administrator, Manager, Worker, User";
    }
}
