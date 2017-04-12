package com.crystal.cleanwaterandroidapplication;

import com.crystal.cleanwaterandroidapplication.model.Account;
import com.crystal.cleanwaterandroidapplication.model.Permission;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 * Lucille Wang's JUnit test
 * Tests the function ______
 *
 * @author Lucille Wang
 * @version 1.0
 */
public class LucilleWangTest {

    private Account account1;
    private Account account2;
    private Account account3;
    private Account account4;
    private Account account5;
    private Account account6;

    /**
     * Sets up 6 different accounts covering the different combinations of missing/having
     * first, middle, and last names. These are used to be tested.
     */
    @Before
    public void setup() {
        account1 = new Account("asdf", "aaaa", Permission.ADMINISTRATOR);
        account1.setFirstName("Lucille");
        account1.setMiddleName("Jiayu");
        account1.setLastName("Wang");

        account2 = new Account("wert", "aaaa", Permission.ADMINISTRATOR);
        account2.setMiddleName("Jiayu");
        account2.setLastName("Wang");

        account3 = new Account("kuhi", "aaaa", Permission.ADMINISTRATOR);
        account3.setFirstName("Lucille");
        account3.setLastName("Wang");

        account4 = new Account("ouyu", "aaaa", Permission.ADMINISTRATOR);
        account4.setFirstName("Lucille");
        account4.setMiddleName("Jiayu");

        account5 = new Account("pueh", "aaaa", Permission.ADMINISTRATOR);

        account6 = new Account("pueh", "aaaa", Permission.ADMINISTRATOR);
        account6.setFirstName(null);
        account6.setMiddleName(null);
        account6.setLastName(null);

    }

    /**
     * Tests if a name in an account with first middle and last name returns the correct string
     */
    @Test
    public void testFirstMiddleLast() {
        String expected = "Lucille Jiayu Wang";
        Assert.assertEquals("Returning incorrect answer for account with First, Middle," +
                "and Last name.", expected, account1.getFullName());
    }

    /**
     * Tests if a name in an account with only middle and last name returns the correct string
     */
    @Test
    public void testMiddleLast() {
        String expected = "N/A Jiayu Wang";
        Assert.assertEquals("Returning incorrect answer for account with only Middle" +
                "and Last name.", expected, account2.getFullName());
    }

    /**
     * Tests if a name in an account with only first and last name returns the correct string
     */
    @Test
    public void testFirstLast() {
        String expected = "Lucille Wang";
        Assert.assertEquals("Returning incorrect answer for account with First and" +
                "and Last name.", expected, account3.getFullName());
    }

    /**
     * Tests if a name in an account with only first and middle name returns the correct string
     */
    @Test
    public void testFirstMiddle() {
        String expected = "Lucille Jiayu N/A";
        Assert.assertEquals("Returning incorrect answer for account with First and Middle" +
                "name.", expected, account4.getFullName());
    }

    /**
     * Tests if a name in an account that hasn't set a name returns the correct string
     */
    @Test
    public void testNone() {
        String expected = "N/A N/A";
        Assert.assertEquals("Returning incorrect answer for account with no First, Middle," +
                "and Last name.", expected, account5.getFullName());
    }
    /**
     * Tests if a name in an account with a null first, middle, and last name returns the correct string
     */
    public void testNull() {
        String expected = "NULL NAME";
        Assert.assertEquals("Returning incorrect answer for account with nulls for First, Middle," +
                "and Last name.", expected, account5.getFullName());
    }
}
