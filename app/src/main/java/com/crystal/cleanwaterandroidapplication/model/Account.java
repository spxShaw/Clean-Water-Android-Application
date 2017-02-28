package com.crystal.cleanwaterandroidapplication.model;

import java.util.Date;

//TODO Implement Calendar object instead of Date object (deprecated).

/**
 * The Account object stores everything a basic account needs to know about itself.
 * This includes its username, password, accountID, and basic bio information. For now,
 * username and password are stored locally, in a basic String object.
 * @author Team 62
 * @see User
 * @see Manager
 * @see Worker
 * @see Administrator
 */
public abstract class Account {
    private String username;
    private String password;
    private String firstName, middleName, lastName;
    private String email;
    private Date birthday;
    private int accountID;

    private Account() {
        firstName = "";
        middleName = "";
        lastName = "";
        email = "";
        birthday = new Date(1900,1,1);
        accountID = 0;
    }

    /**
     * Creates an account, given a username and password.
     * @param username username of account to create
     * @param password password of account to create
     */
    public Account(String username, String password) {
        this();
        this.username = username;
        this.password = password;
    }

    /**
     * Gets the account's username.
     * @return The account's username.
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Gets the account's password.
     * @return The account's password.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Gets the account holders full name. Returns in for the format:
     * 'FirstName' 'MiddleName' 'LastName'. If the name field is empty, replaces
     * with "N/A"
     * @return The full name of the account holder
     */
    public String getFullName() {
        String firstNameReturn = firstName;
        String middleNameReturn = middleName;
        String lastNameReturn = lastName;
        if (firstName.equals("")) {
            firstNameReturn = "N/A";
        }
        if(middleName.equals("")) {
            middleNameReturn = "N/A";
        }
        if (lastName.equals("")) {
            lastNameReturn = "N/A";
        }
        return firstNameReturn + " " + middleNameReturn + " " + lastNameReturn;
    }

    /**
     * Gets the account holders first name.
     * @return The first name of the account holder.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the account holders middle name.
     * @return The middle name of the account holder.
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Gets the account holders last name.
     * @return The last name of the account holder.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the account's email.
     * @return The account's email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the account's birthday date.
     * @return A Date object storing the account holder's birthday.
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * Gets the account's age.
     * @return The account's age.
     */
    public int getAge() {
        Date today = new Date(); //Defaults to 'current' date
        int age = today.getYear() - birthday.getYear();
        if(today.getMonth() == birthday.getMonth()) {
            //Check day
            if(today.getDay() < birthday.getDay()) {
                //Birthday hasnt come yet, subtract one from age
                age--;
            }
        } else if (today.getMonth() < birthday.getMonth()) {
            //Birthday month hasnt come yet, subtract one from age
            age--;
        }
        return age;
    }

    /**
     * Gets the account's ID.
     * @return The account's ID.
     */
    public int getAccountID() {
        return accountID;
    }

    /**
     * Sets the accounts username.
     * @param username The username to change the account's username too.
     */
    public void setUsername(String username) {
        if(username == null) {
            username = "";
        }
        this.username = username;
    }

    /**
     * Sets the account's password.
     * @param password The password to change the account's password too.
     */
    public void setPassword(String password) {
        if(password == null) {
            password = "";
        }
        this.password = password;
    }

    /**
     * Sets the account's full name.
     * @param firstName The first name to change the account's name too.
     * @param middleName The middle name to change the account's name too.
     * @param lastName The last name to change the account's name too.
     */
    void setName(String firstName, String middleName, String lastName) {
        if(firstName == null) {
            this.firstName = "";
        } else {
            this.firstName = firstName;
        }
        if(middleName == null) {
            this.middleName = "";
        } else {
            this.middleName = middleName;
        }
        if(lastName == null) {
            this.lastName = "";
        } else {
            this.lastName = lastName;
        }
    }

    /**
     * Sets the account's first name.
     * @param name Name to set first name too.
     */
    public void setFirstName(String name) {
        if (name == null) {
            this.firstName = "";
        } else {
            this.firstName = name;
        }
    }

    /**
     * Sets the account's middle name.
     * @param name Name to set middle name too.
     */
    public void setMiddleName(String name) {
        if (name == null) {
            this.middleName = "";
        } else {
            this.middleName = name;
        }
    }

    /**
     * Sets the account's last name.
     * @param name Name to set last name too.
     */
    public void setLastName(String name) {
        if (name == null) {
            this.lastName = "";
        } else {
            this.lastName = name;
        }
    }

    /**
     * Sets the account's email.
     * @param email Email to set the account's email too.
     */
    public void setEmail(String email) {
        if (email == null) {
            this.email = "";
        } else {
            this.email = email;
        }
    }

    /**
     * Sets the account's birthday, based on the integer date.
     * @param year Year of the birthday.
     * @param month Month of the birthday. (1-12)
     * @param day Day of the birthday.
     */
    public void setBirthday(int year, int month, int day) {
        this.birthday = new Date(year, month, day);
    }

    /**
     * Sets the account's birthday, based on a Date object.
     * @param birthday Date object to set account's birthday too.
     */
    public void setBirthday(Date birthday) {
        if(birthday == null) {
            this.birthday = new Date(1900,1,1);
        } else {
            this.birthday = birthday;
        }
    }

    /**
     * Sets the account's ID.
     * @param accountID ID to set the account too.
     */
    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    /**
     * Returns the hashcode of the account. The hashcode is just the hashcode of
     * the username.
     * @return Hashcode of the account.
     */
    @Override
    public int hashCode() {
        return username.hashCode();
    }

    /**
     * Returns a string representation of the account, in format: <br/>
     * Account -- Username: 'username' Name: 'full name' Account ID: 'ID'
     * @return String representation of the account.
     */
    @Override
    public String toString() {
        String s = "Account -- ";
        s += "Username: " + getUsername() + " ";
        s += "Name: " + getFullName() + " ";
        s += "Account ID: " + getAccountID() + " ";
        return s;
    }
}