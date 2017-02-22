package com.crystal.cleanwaterandroidapplication;

/**
 * Created by iwbtbp on 2/18/17.
 */

public class Account {
    private String firstName, middleName, lastName;
    private String email;
    private int day, month, year;
    public int accountID;
    private String username;
    private String password;
    private int age;


    /**
     * Creates new account with given username and password.
     * @param username
     * @param password
     */
    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Creates new account with given username, password, first name, and last name.
     * @param username
     * @param password
     * @param firstName
     * @param lastName
     */
    public Account(String username, String password, String firstName, String lastName) {
        this(username, password);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Creates new account with given username, password, first name, middle name, and last name.
     * @param username
     * @param password
     * @param firstName
     * @param middleName
     * @param lastName
     */
    public Account (String username, String password, String firstName, String middleName, String lastName) {
        this(username, password, firstName, lastName);
        this.middleName = middleName;
    }

    /**
     * Gets username.
     * @return the account's username
     */
    String getUsername() {
        return this.username;
    }

    /**
     * Sets the username of the account.
     * @param username the username to be associated with the account
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets password.
     * @return the account's password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Sets the password of the account.
     * @param password the password to be associated with the account
     */
    public setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets name of account. If account has a middle name, the format of the returned name is "[first] [middle] [last]".
     * Otherwise, "[first] [last]" is returned.
     * @return a string that represents the name associated with the account.
     */
    public String getName() {
        if (firstName == null) {
            throw new java.lang.Error("This account does not have a name");
        }
        if (this.middleName != null) {
            return firstName + " " + middleName + " " + lastName;
        }
        return firstName + " " + lastName;
    }

    /**
     * Gets the age of the account's user.
     * @return the age of the user
     */
    public int getAge() {
        return age;
    }


    public void setName(String firstName, String middleName, String lastName) {
        if (firstName == null || middleName == null || lastName == null) {
            throw new java.lang.Error("Null name was passed to setName.");
        }
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public void setBirthday(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

}
