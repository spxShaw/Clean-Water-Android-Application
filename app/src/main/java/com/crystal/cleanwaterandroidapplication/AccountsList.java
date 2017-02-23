package com.crystal.cleanwaterandroidapplication;

import java.util.ArrayList;

/**
 * AccountsList stores a list of all accounts in the system.
 * @author
 * @see Account
 * @version 1.0
 * @since 1.0
 */
public class AccountsList {
    private static ArrayList<Account> list;

    /**
     * Creates an AccountList
     * @since 1.0
     */
    public AccountsList() {
        list = new ArrayList<Account>();
    }

    /**
     * Gets a list of the accounts in the system.
     * @since 1.0
     * @return ArrayList of accounts
     */
    public ArrayList<Account> getAccountsList() {
        return list;
    }

    /**
     * Adds an account to the system. Returns true if account does not
     * exist and is added, false if account already exists and is not added.
     * @since 1.0
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
     * @since 1.0
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
     * @since 1.0
     * @param account Account to remove.
     * @return True if account is removed, false if account is not removed.
     */
    public boolean remove(Account account) {
        return list.remove(account);
    }

    /**
     * Validates a set of credentials, seeing if they match an account in the system. Will
     * return true if they have a match, false if they don't have a match.
     * @since 1.0
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
