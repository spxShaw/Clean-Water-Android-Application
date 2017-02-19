package com.crystal.cleanwaterandroidapplication;

/**
 * Created by iwbtbp on 2/18/17.
 */

public class Manager extends Account {

    public Manager(String username, String password, String firstName, String lastName) {
        super(username, password, firstName, lastName);
    }

    public Manager(String username, String password, String firstName, String middleName, String lastName) {
        super(username, password, firstName, middleName, lastName);
    }
}
