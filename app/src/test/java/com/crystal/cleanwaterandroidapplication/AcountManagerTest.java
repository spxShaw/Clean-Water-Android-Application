package com.crystal.cleanwaterandroidapplication;


import com.crystal.cleanwaterandroidapplication.model.AccountManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.util.Random;

/**
 * Account manager test -- Chris Pham
 *
 */
public class AcountManagerTest {

    private AccountManager acctMng;

    /**
     * Set up for Account Manager tests
     */
    @Before
    public void setup() {
        acctMng = new AccountManager();
    }

    /**
     * Testing add.
     */
    @Test
    public void testAdd() {
        //add username
        Random rand = new Random();
        int u = rand.nextInt(100000000);
        String username = Integer.toString(u);
        boolean b = acctMng.add(username, "pass32", "u32@gmail.com", "USER");
        //check that add returns true
        Assert.assertEquals(true, b);
        //check if username is in database
        Assert.assertTrue(acctMng.checkUsername(username));


    }


    /**
     * Testing checkusername.
     */
    @Test
    public void testCheckUsername() {
        // add random username
        Random rand = new Random();
        int u = rand.nextInt(100000000);
        String username = Integer.toString(u);
        acctMng.add(username, "pass", "user@gmail.com", "USER");
        //check if username is in database
        Assert.assertTrue(acctMng.checkUsername(username));

        //check if invalid username returns false
        Assert.assertFalse(acctMng.checkUsername("notValidUser"));
    }

    /*
    @Test
    public void testAdd() {

        Account newAccount = new Account("user", "pass", Permission.USER);

        //Test successful add
        Assert.assertTrue(acctMng.add(newAccount));
        Assert.assertEquals("Account username is wrong", "user", newAccount.getUsername());
        Assert.assertEquals("Account password is wrong", "pass", newAccount.getPassword());
        //Assert.assertEquals("Account permission is wrong", Permission.USER, newAccount.getAccountPermission());

        //Test failed add
        Assert.assertFalse(acctMng.add(newAccount));


    }*/


}
