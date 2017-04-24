package com.crystal.cleanwaterandroidapplication;


import com.crystal.cleanwaterandroidapplication.model.AccountManager;

import org.junit.Before;

//import org.junit.Test;


/**
 * Account manager test -- Chris Pham
 *
 */
public class AcountManagerTest {

    private AccountManager acctMng;

    @Before
    public void setup() {
        acctMng = new AccountManager();
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
