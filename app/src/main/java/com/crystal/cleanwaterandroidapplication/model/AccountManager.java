package com.crystal.cleanwaterandroidapplication.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.HashMap;

//TODO Implement this class with a database backing rather than a HashMap backing.

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
     * Creates an AccountList. Nothing special about it.
     */
    public AccountManager(){
        //Do nothing, for now
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
     * @return Account matching that username.
     * @throws AccountDoesNotExistException Thrown if Account is not found in the map.
     */
    public Account getAccountByUsername(String username) throws AccountDoesNotExistException {
        Account a = map.get(username);
        if (a == null) {
            throw new AccountDoesNotExistException("Attempted to get a username that does not " +
                    "belong to an account in AccountManager");
        } else {
            return a;
        }
    }

    /**
     * Gets an account object, based on the Account's email. Recommended to not look up by email,
     * but rather by username were possible, for efficiency.
     * @param email Email of Account to return.
     * @return Account matching that username.
     * @throws AccountDoesNotExistException Thrown if Account is not found in the map.
     */
    public Account getAccountByEmail(String email) throws AccountDoesNotExistException {
        for(Account a: map.values()) {
            if(a.getEmail().equals(email)) {
                return a;
            }
        }
        throw new AccountDoesNotExistException("Attempted to get a email that does not " +
                "belong to an account in AccountManager");
    }

    /**
     * Adds an account to the map. Returns true if Account does not
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
     * Adds an account to the map. Returns true if Account does not
     * exist and is added, false if account already exists or cannot reach database.
     * @param username username of account
     * @param password password of account
     * @param email email of account
     * @param accountType type of account
     * @return True if account is added, false if account is not added.
     */
    public boolean add(String username, String password, String email, String accountType) {
        updateAccounts();
        if (checkUsername(username)) {
            return false;
        }
        try {
            URL url = new URL("http://mattbusch.net/wp-content/uploads/WaterWorld/adduser.php");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            String data = URLEncoder.encode("user", "UTF-8")
                    + "=" + URLEncoder.encode(username, "UTF-8");
            data += "&" + URLEncoder.encode("email", "UTF-8") + "="
                    + URLEncoder.encode(email, "UTF-8");
            data += "&" + URLEncoder.encode("pass", "UTF-8")
                    + "=" + URLEncoder.encode(password, "UTF-8");
            data += "&" + URLEncoder.encode("type", "UTF-8")
                    + "=" + URLEncoder.encode(accountType, "UTF-8");
            writer.write(data);
            writer.close();
            InputStream stream = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            updateAccounts();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Removes an account from the map. Returns the Account removed or throws
     * AccountDoesNotExistException if the account cannot be found.
     * @param account Account to remove.
     * @return Account that was removed.
     * @throws AccountDoesNotExistException Thrown if Account is not found in the map.
     */
    public Account remove(Account account) throws AccountDoesNotExistException {
        return removeByUsername(account.getUsername());
    }

    /**
     * Removes an account from the map by the Account's username. Returns the Account
     * that is removed or throws AccountDoesNotExistException if the account cannot be found.
     * @param username username of account to remove
     * @return Account that was removed from the Map.
     * @throws AccountDoesNotExistException Thrown if Account is not found in the map.
     */
    public Account removeByUsername(String username) throws AccountDoesNotExistException {
        Account a = map.remove(username);
        if(a == null) {
            throw new AccountDoesNotExistException("Attempted to remove an account by username that " +
                    "does not belong to an account in AccountManager");
        } else {
            return a;
        }
    }

    /**
     * Removes an account from the map by the Account's email. Returns the Account that is removed
     * or throws AccountDoesNotExistException if account is not found. For efficiency, use
     * removeByUsername over this method.
     * @param email The email of the account to remove
     * @return Account that was removed from the map
     * @throws AccountDoesNotExistException Thrown if Account is not found in the map
     */
    public Account removeByEmail(String email) throws AccountDoesNotExistException {
        for(Account a: map.values()) {
            if(a.getEmail().equals(email)) {
                removeByUsername(a.getUsername());
            }
        }
        throw new AccountDoesNotExistException("Attempted to remove an account by email that does" +
                " not belong to an account in AccountManager");
    }

    /**
     * Checks if the username already exists for an account in the map. Returns
     * true if username exists, false if username does not exist.
     * @param username The username to check
     * @return True if username exists, false if username does not exist.
     */
    public boolean checkUsername(String username) {
        return map.containsKey(username);
    }

    /**
     * Checks to see if a email exists within the map. Returns true if the email exists,
     * false if the email does not exist
     * @param email The email to check.
     * @return True if email exists, false if email does not exist.
     */
    public boolean checkEmail(String email) {
        for(Account a: map.values()) {
            if(a.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Validates a set of credentials, seeing if they match an account in the system. Will
     * return true if they have a match, false if they don't have a match.
     * @param username username of account to validate
     * @param password password of account to validate
     * @return True if account is validated, false if account is not validated.
     */
    public boolean validCredentials(String username, String password) {
        if (map.containsKey(username)) {
            return map.get(username).getPassword().equals(password);
        }
        return false;
    }

    /**
     * Sets the current Account to whatever account matches the given credentials. If no account
     * is found, throws an InvalidCredentialsException. All logins should be done through here.
     * @param username Username of account
     * @param password Password of account
     * @return The Account that is logged in and set as the current account.
     * @throws InvalidCredentialsException Thrown if the username/password combo is invalid
     */
    public Account login(String username, String password) throws InvalidCredentialsException {
        if (validCredentials(username, password)) {
            setCurrentAccount(map.get(username));
            return map.get(username);
        }
        throw new InvalidCredentialsException("Account not found, incorrect username or password.");
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

    /**
     * Replaces the map with an updated one from the database
     */
    public static void updateAccounts() {
            try {
                HashMap<String, Account> newHashMap = new HashMap<>();
                URL url = new URL("http://mattbusch.net/wp-content/uploads/WaterWorld/loadusers.php");
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setConnectTimeout(5000);
                connection.setDoOutput(true);
                InputStream stream = connection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(stream));
                String jsonString=br.readLine();
                JSONArray myjsonarray = new JSONArray(jsonString);
                for (int i = 0; i < myjsonarray.length(); i++){
                    JSONObject jsonObject = myjsonarray.getJSONObject(i);
                    Log.i("JSON Object", jsonObject.toString());
                    Account newAccount;
                    switch (jsonObject.getString("type")) {
                        case "ADMN":
                            newAccount = new Administrator(jsonObject.getString("username"),jsonObject.getString("password"));
                            break;
                        case "MANG":
                            newAccount = new Manager(jsonObject.getString("username"),jsonObject.getString("password"));
                            break;
                        case "WORK":
                            newAccount = new Worker(jsonObject.getString("username"),jsonObject.getString("password"));
                            break;
                        default:
                            newAccount = new User(jsonObject.getString("username"),jsonObject.getString("password"));
                            break;
                    }
                    newAccount.setAccountID(new Integer(jsonObject.getString("ID")));
                    Log.i("New Account", newAccount.toString());
                    newHashMap.put(jsonObject.getString("username"),newAccount);
                    map = newHashMap;
                }
            } catch (Exception E) {
                Log.e("Error", E.toString());
        }
    }
}
