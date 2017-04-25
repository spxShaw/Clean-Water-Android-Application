package com.crystal.cleanwaterandroidapplication.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

//TODO Implement this class with a database backing rather than a HashMap backing.

/**
 * AccountsManager stores and manages a map of Accounts. The Account's username is used
 * as the key for the map. The map is static, creating new instances of this class will
 * not overwrite the map of Accounts. Therefor, simply create an instance of this class
 * whenever access is needed to Account info.
 *
 * Follows the Singleton design pattern
 *
 * @author Team 62
 * @see Account
 */
public class AccountManager {

    private static Account currentAccount;

    public AccountManager() {
        //Do nothing, for now
    }

    /*@
      @   public normal_behavior
      @     requires username != null && password != null;
      @*/
    /**
     *
     * @param username
     * @param password
     * @throws InvalidCredentialsException
     */
    public static void login(String username, String password) throws InvalidCredentialsException, BannedAccountException {
        try {
            URL url = new URL("http://mattbusch.net/wp-content/uploads/WaterWorld/login.php");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            String data = URLEncoder.encode("user", "UTF-8")
                    + "=" + URLEncoder.encode(username, "UTF-8");
            data += "&" + URLEncoder.encode("pass", "UTF-8")
                    + "=" + URLEncoder.encode(password, "UTF-8");
            writer.write(data);
            writer.close();
            InputStream stream = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            String jsonString = br.readLine();
            if (jsonString.equals("null")) {
                throw new InvalidCredentialsException("User/Pass doesn't exist");
            }
            JSONArray myjsonarray = new JSONArray(jsonString);
            JSONObject jsonObject = myjsonarray.getJSONObject(0);
            Account newAccount;
            newAccount = new Account(jsonObject.getString("username"),
                    jsonObject.getString("password"),
                    Permission.valueOf(jsonObject.getString("type")));
            newAccount.setAccountID(Integer.valueOf(jsonObject.getString("ID")));
            newAccount.setFirstName(jsonObject.getString("first"));
            newAccount.setMiddleName(jsonObject.getString("middle"));
            newAccount.setLastName(jsonObject.getString("last"));
            if (1 == jsonObject.getInt("banned")) {
                throw new BannedAccountException("Account is banned");
            }
            currentAccount = newAccount;
        } catch (java.io.IOException e) {
            //TODO: Database exception
            throw new InvalidCredentialsException("Cannot determine if username exists");
        } catch (JSONException j) {
            throw new InvalidCredentialsException("User/Pass doesn't exist");
        }
    }

    public static void updateAccount(Account account) {

    }

    /*@
      @   public normal_behavior
      @     ensures \result !=null;
      @*/
    /**
     * Gets the current logged in account.
     * @return the account currently logged in.
     */
    public static Account getCurrentAccount() {
        return currentAccount;
    }


    /*@
      @   public normal_behavior
      @     required username !=null;
      @     ensures \result != null;
      @*/
    /**
     * Gets an account object, based on the Account's username.
     *
     * @param username Username of Account to return.
     * @return Account matching that username.
     * @throws AccountDoesNotExistException Thrown if Account is not found in the map.
     */
    public Account getAccountByUsername(String username) throws AccountDoesNotExistException {
        try {
            URL url = new URL("http://mattbusch.net/wp-content/uploads/WaterWorld/finduser.php");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            String data = URLEncoder.encode("user", "UTF-8")
                    + "=" + URLEncoder.encode(username, "UTF-8");
            writer.write(data);
            writer.close();
            InputStream stream = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            String jsonString = br.readLine();
            if (jsonString.equals("null")) {
                throw new AccountDoesNotExistException("User doesn't exist");
            }
            JSONArray myjsonarray = new JSONArray(jsonString);
            JSONObject jsonObject = myjsonarray.getJSONObject(0);
            Account newAccount;
            newAccount = new Account(jsonObject.getString("username"),
                    jsonObject.getString("password"),
                    Permission.valueOf(jsonObject.getString("type")));
            newAccount.setAccountID(Integer.valueOf(jsonObject.getString("ID")));
            newAccount.setFirstName(jsonObject.getString("first"));
            newAccount.setMiddleName(jsonObject.getString("middle"));
            newAccount.setLastName(jsonObject.getString("last"));
            return newAccount;
        } catch (java.io.IOException e) {
            //TODO: Database exception
            throw new AccountDoesNotExistException("Cannot determine if username exists");
        } catch (JSONException j) {
            throw new AccountDoesNotExistException("User doesn't exist");
        }
    }

    /*@
      @   public normal_behavior
      @     requires username !=null && password != null;
      @*/
    /**
     * Adds an account to the map. Returns true if Account does not
     * exist and is added, false if account already exists or cannot reach database.
     *
     * @param username    username of account
     * @param password    password of account
     * @param email       email of account
     * @param accountType type of account
     * @return True if account is added, false if account is not added.
     */
    public boolean add(String username, String password, String email, String accountType) {
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
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /*@
      @   public normal_behavior
      @     requires account != null;
      @     ensures \result !=null;
      @*/
    /**
     * Removes an account from the map. Returns the Account removed or throws
     * AccountDoesNotExistException if the account cannot be found.
     *
     * @param account Account to remove.
     * @return Account that was removed.
     * @throws AccountDoesNotExistException Thrown if Account is not found in the map.
     */
    public Account remove(Account account) throws AccountDoesNotExistException {
        return removeByUsername(account.getUsername());
    }


    /*@
      @   public normal_behavior
      @     requires username != null;
      @     ensures \result !=null;
      @*/
    /**
     * Removes an account from the map by the Account's username. Returns the Account
     * that is removed or throws AccountDoesNotExistException if the account cannot be found.
     *
     * @param username username of account to remove
     * @return Account that was removed from the Map.
     * @throws AccountDoesNotExistException Thrown if Account is not found in the map.
     */
    public Account removeByUsername(String username) throws AccountDoesNotExistException {
        Account returnAccount = getAccountByUsername(username);
        ;
        try {
            URL url = new URL("http://mattbusch.net/wp-content/uploads/WaterWorld/deleteUser.php");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            String data = URLEncoder.encode("user", "UTF-8")
                    + "=" + URLEncoder.encode(username, "UTF-8");
            writer.write(data);
            writer.close();
            InputStream stream = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            String jsonString = br.readLine();
        } catch (java.io.IOException e) {
            //TODO: Database exception
            throw new AccountDoesNotExistException("Doesn't Exist");
        }
        return returnAccount;
    }

    /*@
      @   public normal_behavior
      @     requires username != null;
      @*/
    /**
     * Checks if the username already exists for an account in the map. Returns
     * true if username exists, false if username does not exist.
     *
     * @param username The username to check
     * @return True if username exists, false if username does not exist.
     */
    public boolean checkUsername(String username) {
        try {
            URL url = new URL("http://mattbusch.net/wp-content/uploads/WaterWorld/finduser.php");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            String data = URLEncoder.encode("user", "UTF-8")
                    + "=" + URLEncoder.encode(username, "UTF-8");
            writer.write(data);
            writer.close();
            InputStream stream = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            String jsonString = br.readLine();
            if (jsonString.equals("null")) {
                return false;
            }
            return true;
        } catch (java.io.IOException e) {
            //TODO: Database exception
            return false;
        }
    }


    /*@
      @   public normal_behavior
      @     requires username != null;
      @*/

    /**
     * Sends email to website script to begin resetting password
     *
     * @param email The email of the forgotten user
     */
    public static void forgetPass(String email) {
        try {
            URL url = new URL("http://mattbusch.net/wp-content/uploads/WaterWorld/passwordrecover.php");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            String data = URLEncoder.encode("email", "UTF-8")
                    + "=" + URLEncoder.encode(email, "UTF-8");
            writer.write(data);
            writer.close();
            InputStream stream = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
    }

    /**
     * Sets the Account that is currently logged in to account
     *
     * @param account Account to set as current logged in account
     */
    public void setCurrentAccount(Account account) {
        currentAccount = account;
    }

    /**
     * Replaces the map with an updated one from the database
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
     Account newAccount;
     switch (jsonObject.getString("type")) {
     case "ADMN":
     newAccount = new Account(jsonObject.getString("username"),jsonObject.getString("password"),Permission.ADMINISTRATOR);
     break;
     case "MANG":
     newAccount = new Account(jsonObject.getString("username"),jsonObject.getString("password"),Permission.MANAGER);
     break;
     case "WORK":
     newAccount = new Account(jsonObject.getString("username"),jsonObject.getString("password"),Permission.WORKER);
     break;
     default:
     newAccount = new Account(jsonObject.getString("username"),jsonObject.getString("password"),Permission.USER);
     break;
     }
     newAccount.setAccountID(new Integer(jsonObject.getString("ID")));
     newHashMap.put(jsonObject.getString("username"),newAccount);
     map = newHashMap;
     }
     } catch (Exception E) {
     Log.e("Error", E.toString());
     }
     }
     */
}