package com.crystal.cleanwaterandroidapplication;

import java.util.ArrayList;

/**
 * AccountsList stores a list of all accounts in the system.
 * @author
 * @see Account
 */
public class AccountsList {
    private static ArrayList<Account> list;

    /**
     * Creates an AccountList
     */
    public AccountsList() {
        list = new ArrayList<Account>();
    }

    /**
     * Gets a list of the accounts in the system.
     * @return ArrayList of accounts
     */
    public ArrayList<Account> getAccountsList() {
        return list;
    }

    /**
     * Checks if the username already exists for an account in the AccountList. Returns
     * true if username exists, false if username does not exist.
     * @param username username to check
     * @return True if username exists, false if username does not exist.
     */
    public boolean checkForUsername(String username) {
        for(Account a: list) {
            if(a.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds an account to the system. Returns true if account does not
     * exist and is added, false if account already exists and is not added.
     * @param newAccount the account to add
     * @return true if account is added, false if account is not added.
     */
    public boolean add(Account newAccount) {
        if (!list.contains(newAccount)) {
            list.add(newAccount);
            return true;
        }
        return false;
    }

    /**
     * Removes an account from the system by the accounts username. Returns true
     * if account is removed, false if account is not removed (not found).
     * @param username username of account to remove
     * @return Rrue if account is removed, false if account is not removed.
     */
    public boolean remove(String username) {
        for (Account a : list) {
            if (a.getUsername().equals(username)) {
                list.remove(a);
                return true;
            }
        }
        return false;
    }

    /**
     * Removes an account from the system. Returns true if account is removed,
     * false if account is not removed (not found).
     * @param account Account to remove.
     * @return True if account is removed, false if account is not removed.
     */
    public boolean remove(Account account) {
        return list.remove(account);
    }

    /**
     * Validates a set of credentials, seeing if they match an account in the system. Will
     * return true if they have a match, false if they don't have a match.
     * @param username username of account to validate
     * @param password password of account to validate
     * @return true if account is validated, false if account is not validated
     */
    public boolean validCredentials(String username, String password) {
        for (Account a: list) {
            if (a.getUsername().equals(username)) {
                if (a.getPassword().equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }

}
