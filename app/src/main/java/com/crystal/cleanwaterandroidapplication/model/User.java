package com.crystal.cleanwaterandroidapplication.model;

import com.crystal.cleanwaterandroidapplication.model.Account;

/**
 * User Account.
 * @author Team 62
 * @see Account
 */
public class User extends Account {

    /**
     * Creates a User Account, given a username and password.
     * @param username Username of User
     * @param password Password of User
     */
    public User(String username, String password) {
        super(username, password);
    }

}
