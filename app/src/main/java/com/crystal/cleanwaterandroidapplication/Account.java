package com.crystal.cleanwaterandroidapplication;

/**
 * Created by iwbtbp on 2/18/17.
 */

public class Account {
    private String firstName, middleName, lastName;
    private String email;
    private int day, month, year;
    public int accountID;

    public Account(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Account (String firstName, String middleName, String lastName) {
        this(firstName, lastName);
        this.middleName = middleName;
    }

    String getName() {
        if (this.middleName != null) {
            return firstName + middleName + lastName;
        }
        return firstName + lastName;
    }

    int getAge() {
        return 0;
    }


    void setName(String firstName, String middleName, String lastName) {
        if (firstName == null || lastName == null) {
            throw new java.lang.Error("Null name was passed to setName.");
        }
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    void setBirthday(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

}
