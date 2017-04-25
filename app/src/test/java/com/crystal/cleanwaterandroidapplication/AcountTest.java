package com.crystal.cleanwaterandroidapplication;

import com.crystal.cleanwaterandroidapplication.model.Account;
import com.crystal.cleanwaterandroidapplication.model.Permission;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

/**
 * Created by christopherpham on 4/24/17.
 */


public class AcountTest {

    private Account acct;

    @Before
    public void setup() {
        acct = new Account("user", "pass", Permission.MANAGER);
    }

    @Test
    public void testGetUsername() {
        Assert.assertEquals("user", acct.getUsername());
    }

    @Test
    public void testGetPassword() {
        Assert.assertEquals("pass", acct.getPassword());
    }

    /**
     * Testing full name features
     */
    @Test
    public void testGetFullName() {
        //test default name
        Assert.assertEquals("N/A N/A", acct.getFullName());

        //set names
        acct.setFirstName("First");
        Assert.assertEquals("First", acct.getFirstName());
        acct.setMiddleName("Middle");
        Assert.assertEquals("Middle", acct.getMiddleName());
        acct.setLastName("Last");
        Assert.assertEquals("Last", acct.getLastName());

        //test full name getter
        Assert.assertEquals("First Middle Last", acct.getFullName());

        //test without middle name
        acct.setMiddleName("");
        Assert.assertEquals("First Last", acct.getFullName());

    }

    /**
     * testing e-mail
     */
    @Test
    public void testEmail() {
        //Test null String
        acct.setEmail(null);
        Assert.assertEquals("", acct.getEmail());

        //Test valid String
        acct.setEmail("valid Email");
        Assert.assertEquals("valid Email", acct.getEmail());
    }

    /**
     * testing account id
     */
    @Test
    public void testAccountID() {
        acct.setAccountID(1234);
        Assert.assertEquals(1234, acct.getAccountID());
    }

    /**
     * Testing toString
     */
    @Test
    public void testToString() {
        acct.setUsername("mike32");
        acct.setFirstName("Mikey");
        acct.setMiddleName("Timothy");
        acct.setLastName("Wiggins");
        acct.setAccountID(12345);
        acct.updatePermission(Permission.USER);

        Assert.assertEquals("Account -- Username: mike32 Name: Mikey Timothy Wiggins Account ID: 12345 Permission: User ", acct.toString());
    }
}
