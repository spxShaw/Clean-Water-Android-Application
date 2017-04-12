package com.crystal.cleanwaterandroidapplication;

import com.crystal.cleanwaterandroidapplication.model.Account;
import com.crystal.cleanwaterandroidapplication.model.Permission;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 * Matt Busch Test Class
 * Tests the hasPermission method
 *
 * @author Matt Busch
 * @version 1.0
 */
public class MattBuschTest {
    private Account account1;
    private Account account2;
    private Account account3;
    private Account account4;


    /**
     * Sets up four accounts with different permission levels
     */
    @Before
    public void setup() {
        account1 = new Account("admin", "Test 1", Permission.ADMINISTRATOR);

        account2 = new Account("manager", "Test 2", Permission.MANAGER);

        account3 = new Account("worker", "Test 3", Permission.WORKER);

        account4 = new Account("user", "Test 4", Permission.USER);

    }

    /**
     * Tests if admin has access to all permission levels
     */
    @Test
    public void testAdmin() {
        Assert.assertTrue(account1.hasPermission(Permission.ADMINISTRATOR));
        Assert.assertTrue(account1.hasPermission(Permission.MANAGER));
        Assert.assertTrue(account1.hasPermission(Permission.WORKER));
        Assert.assertTrue(account1.hasPermission(Permission.USER));

    }

    /**
     * Tests if Manager account has all permission but administrator permissions
     */
    @Test
    public void testManager() {
        Assert.assertFalse(account2.hasPermission(Permission.ADMINISTRATOR));
        Assert.assertTrue(account2.hasPermission(Permission.MANAGER));
        Assert.assertTrue(account2.hasPermission(Permission.WORKER));
        Assert.assertTrue(account2.hasPermission(Permission.USER));
    }

    /**
     * Tests if worker has worker and user permissions
     */
    @Test
    public void testWorker() {
        Assert.assertFalse(account3.hasPermission(Permission.ADMINISTRATOR));
        Assert.assertFalse(account3.hasPermission(Permission.MANAGER));
        Assert.assertTrue(account3.hasPermission(Permission.WORKER));
        Assert.assertTrue(account3.hasPermission(Permission.USER));

    }

    /**
     * Tests if a user only has user permissions
     */
    @Test
    public void testUser() {
        Assert.assertFalse(account4.hasPermission(Permission.ADMINISTRATOR));
        Assert.assertFalse(account4.hasPermission(Permission.MANAGER));
        Assert.assertFalse(account4.hasPermission(Permission.WORKER));
        Assert.assertTrue(account4.hasPermission(Permission.USER));

    }

}
