package com.crystal.cleanwaterandroidapplication.model;

import com.crystal.cleanwaterandroidapplication.model.Account;

/**
 * Worker Account.
 * @author Team 62
 * @see Account
 */
public class Worker extends Account {

    /**
     * Creates a Worker Account, given a username and password.
     * @param username Username of User
     * @param password Password of User
     */
    public Worker(String username, String password) {
        super(username, password);
    }
}
