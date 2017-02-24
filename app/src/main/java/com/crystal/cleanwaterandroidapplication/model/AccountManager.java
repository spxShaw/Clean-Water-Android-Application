package com.crystal.cleanwaterandroidapplication.model;

import com.crystal.cleanwaterandroidapplication.model.Account;

import java.util.Collection;
import java.util.HashMap;

/**
 * AccountsManager stores and manages a map of Accounts. The Account's username is used
 * as the key for the map.
 * @author Team 62
 * @see Account
 */
public class AccountManager {
    private static HashMap<String, Account> map = new HashMap<>();

    /**
     * Creates an AccountList
     */
    public AccountManager(){
        //Do nothing
    }

    /**
     * Returns a Collection of all the accounts in the map
     * @return Collection containing all accounts.
     */
    public Collection<Account> getAccountsList() {
        return map.values();
    }

    /**
     * Adds an account to the map. Returns true if account does not
     * exist and is added, false if account already exists and is not added.
     * @param newAccount the account to add
     * @return True if account is added, false if account is not added.
     */
    public boolean add(Account newAccount) {
        if (!map.containsKey(newAccount.getUsername())) {
            map.put(newAccount.getUsername(),newAccount);
            return true;
        }
        return false;
    }

    /**
     * Removes an account from the map by the accounts username. Returns true
     * if account is removed, false if account is not removed (not found).
     * @param username username of account to remove
     * @return True if account is removed, false if account is not removed.
     */
    public boolean remove(String username) {
        if(map.containsKey(username)) {
            map.remove(username);
        }
        return false;
    }

    /**
     * Removes an account from the map. Returns true if account is removed,
     * false if account is not removed (not found).
     * @param account Account to remove.
     * @return True if account is removed, false if account is not removed.
     */
    public boolean remove(Account account) {
        return remove(account.getUsername());
    }

    /**
     * Checks if the username already exists for an account in the map. Returns
     * true if username exists, false if username does not exist.
     * @param username username to check
     * @return True if username exists, false if username does not exist.
     */
    public boolean checkForUsername(String username) {
        return map.containsKey(username);
    }

    /**
     * Validates a set of credentials, seeing if they match an account in the system. Will
     * return true if they have a match, false if they don't have a match.
     * @param username username of account to validate
     * @param password password of account to validate
     * @return True if account is validated, false if account is not validated.
     */
    public boolean validCredentials(String username, String password) {
        if(map.containsKey(username)) {
            return map.get(username).getPassword().equals(password);
        }
        return false;
    }

}
