package com.crystal.cleanwaterandroidapplication.model;

import java.util.Collection;
import java.util.HashMap;

/**
 * AccountsManager stores and manages a map of Accounts. The Account's username is used
 * as the key for the map. The map is static, creating new instances of this class will
 * not overwrite the map of Accounts. Therefor, simply create an instance of this class
 * whenever access is needed to Account info.
 * @author Team 62
 * @see Account
 */
public class AccountManager {
    private static HashMap<String, Account> map = new HashMap<>();
    private static Account currentAccount;

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
     * Gets an account object, based on the Account's username.
     * @param username Username of Account to return.
     * @return Account matching that username. Null if no account matches.
     * @throws AccountDoesNotExistException Thrown if Account is not found in the map.
     */
    public Account getAccount(String username) throws AccountDoesNotExistException {
        Account a = map.get(username);
        if (a == null) {
            throw new AccountDoesNotExistException("Attempted to get a username that does not " +
                    "belong to an account in AccountManager");
        } else {
            return a;
        }
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
     * @return Account that was removed from the Map.
     * @throws AccountDoesNotExistException Thrown if Account is not found in the map.
     */
    public Account remove(String username) throws AccountDoesNotExistException {
        Account a = map.get(username);
        if(a == null) {
            throw new AccountDoesNotExistException("Attempted to get a username that does not " +
                    "belong to an account in AccountManager");
        } else {
            return a;
        }
    }

    /**
     * Removes an account from the map. Returns true if account is removed,
     * false if account is not removed (not found).
     * @param account Account to remove.
     * @return Account that was removed.
     * @throws AccountDoesNotExistException Thrown if Account is not found in the map.
     */
    public Account remove(Account account) throws AccountDoesNotExistException {
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

    /**
     * Sets the Account that is currently logged in to account
     * @param account Account to set as current logged in account
     */
    public void setCurrentAccount(Account account) {
        currentAccount = account;
    }

    /**
     * Gets the Account that is currently logged in.
     * @return current Account that is logged in
     */
    public Account getCurrentAccount() {
        return currentAccount;
    }

}
