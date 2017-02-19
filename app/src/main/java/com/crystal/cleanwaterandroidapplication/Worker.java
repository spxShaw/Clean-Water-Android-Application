package com.crystal.cleanwaterandroidapplication;

/**
 * Created by iwbtbp on 2/18/17.
 */

public class Worker extends Account {

    public Worker(String username, String password, String firstName, String lastName) {
        super(username, password, firstName, lastName);
    }

    public Worker(String username, String password, String firstName, String middleName, String lastName) {
        super(username, password, firstName, middleName, lastName);
    }
}
