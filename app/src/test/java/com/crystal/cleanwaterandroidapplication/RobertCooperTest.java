package com.crystal.cleanwaterandroidapplication;

import com.crystal.cleanwaterandroidapplication.model.Account;
import com.crystal.cleanwaterandroidapplication.model.Permission;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Robert Cooper's junit test. Used to test the functionality of the equals method of the Account
 * class. An Account should only be equal if the two accounts have the same username.
 */
public class RobertCooperTest {

    private Account nullAccount;
    private Account blankAccount;
    private Account a;
    private Account aCopy;
    private Account b;
    private Account c;
    private Object objectAccount;

    @Before
    public void setup() {
        nullAccount = null;
        blankAccount = new Account("","", Permission.USER);

        a = new Account("rcooper39", "password123", Permission.USER);
        a.setFirstName("Robert");
        a.setLastName("Cooper");
        aCopy = a;

        b = new Account("rlcooper46", "crazyDude123", Permission.USER);
        b.setFirstName("Robert");
        b.setLastName("Cooper");

        c = new Account("rcooper39", "123", Permission.DEVELOPER);

        objectAccount = new Account("rcooper39", "crazyGuy", Permission.ADMINISTRATOR);
    }

    @Test
    public void test_AccountEquality() {
        //Test object equals itself
        Assert.assertTrue(a.equals(a));

        //Test for copies returning true.
        Assert.assertTrue(a.equals(aCopy));
        Assert.assertTrue(aCopy.equals(a));

        //Test for accounts with same name but different username does not equal each other.
        Assert.assertFalse(a.equals(b));

        //Test for accounts with same username equal each other
        Assert.assertTrue(a.equals(c));
        Assert.assertTrue(c.equals(a));

        //Test for accounts casted as other objects but with same username are equal
        Assert.assertTrue(a.equals(objectAccount));
        Assert.assertTrue(objectAccount.equals(a));
    }
}
