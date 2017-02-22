package com.crystal.cleanwaterandroidapplication;

/**
 * Created by iwbtbp on 2/18/17.
 */

public class Account {
    private String username;
    private String password;
    private String email;
    private String firstName, middleName, lastName;
    private int day, month, year;
    public int accountID;
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
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the email address of the account.
     * @return the email address of the account
     */
    public String getEmail() {

        return email;
    }

    /**
     * Sets the email address of the account.
     * @param email the specified email address to be assigned to the account
     */
    public void setEmail(String email) {
        this.email = email;
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

    public void setName(String firstName, String middleName, String lastName) {
        if (firstName == null || middleName == null || lastName == null) {
            throw new java.lang.Error("Null name was passed to setName.");
        }
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }


    /**
     * Gets the age of the account's user.
     * @return the age of the user
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the account's user.
     * @param age the given age of the user
     */
    public void setAge(int age) {
        this.age = age;
    }


    /**
     * This method is currently being worked on.
     * @return the birthday of the user
     */
    public int getBirthday() {
        //todo
        return 0;
    }

    /**
     * Sets the birthday of the account's user given the day, month, and year.
     * @param day the day of the month
     * @param month the month from 1 being Jan to 12 being Dec
     * @param year the year the user was born
     */

    public void setBirthday(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }


    /**
     * Gets the account id
     * @return the account id
     */
    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int id) {
        this.accountID = id;
    }

}
