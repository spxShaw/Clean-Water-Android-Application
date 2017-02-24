package com.crystal.cleanwaterandroidapplication.model;

import com.crystal.cleanwaterandroidapplication.model.Account;

/**
 * Manager Account.
 * @author Team 62
 * @see Account
 */
public class Manager extends Account {

    /**
     * Creates a Manager Account, given a username and password.
     * @param username Username of User
     * @param password Password of User
     */
    public Manager(String username, String password) {
        super(username, password);
    }
}
