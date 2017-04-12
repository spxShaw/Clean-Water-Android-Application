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
 * AccountsManager links the app and the database. It is the 'gateway' that the app uses to
 * communicate with the database.
 * @author Team 62
 * @see Account
 */
public final class AccountManager {
    private static HashMap<String, Account> map = new HashMap<>();
    private static Account currentAccount;

    private AccountManager(){
        //Do nothing, for now
    }

    /**
     * Attempts to login an account with given username and password. If it is successful, makes the
     * currentAccount that account. If it fails, throws an InvalidCredentialsException. MUST BE
     * CALLED WITHIN A SEPARATE THREAD
     * @param username Username of account to login
     * @param password Password of account to login
     * @throws InvalidCredentialsException Thrown whenever an attempt to login has invalid username
     * or password
     */
    public static void login(String username, String password) throws InvalidCredentialsException {
        updateAccounts();
        Account account = map.get(username);
        if (account != null) {
            if (account.getPassword().equals(password)) {
                currentAccount = account;
                return;
            }
        }
        throw new InvalidCredentialsException("Either an invalid username/password provided to" +
                "login");
    }

    /**
     * Adds an account to the account database. If the account already exists on the database
     * (username is already taken for example), account is not added and returns false. Otherwise,
     * adds account to database and returns true. MUST BE CALLED WITHIN A SEPARATE THREAD.
     * @param account account to add to database
     * @return true if account successfully added, false if account failed to be added.
     */
    public static boolean addAccount(Account account) {
        if (account == null) {
            return false;
        }

        updateAccounts();
        if (usernameExists(account.getUsername())) {
            return false;
        } else {
            add(account.getUsername(), account.getPassword(), account.getEmail(), account.getAccountPermission());
            return true;
        }
    }

    /**
     * Gets the current logged in account.
     * @return pointer to the account currently logged in.
     */
    public static Account getCurrentAccount() {
        return currentAccount;
    }

    //Checks to see if username already exists on the internal account map
    public static boolean usernameExists(String username) {
        return map.containsKey(username);
    }

    //Adds the account to the database
    private static boolean add(String username, String password, String email, Permission accountType) {
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
            switch (accountType) {
                case USER:
                    data += "&" + URLEncoder.encode("type", "UTF-8")
                            + "=" + URLEncoder.encode("USER", "UTF-8");
                    break;
                case WORKER:
                    data += "&" + URLEncoder.encode("type", "UTF-8")
                            + "=" + URLEncoder.encode("WORK", "UTF-8");
                    break;
                case MANAGER:
                    data += "&" + URLEncoder.encode("type", "UTF-8")
                            + "=" + URLEncoder.encode("MANG", "UTF-8");
                    break;
                case ADMINISTRATOR:
                    data += "&" + URLEncoder.encode("type", "UTF-8")
                            + "=" + URLEncoder.encode("ADMN", "UTF-8");
                    break;
                case DEVELOPER:
                    data += "&" + URLEncoder.encode("type", "UTF-8")
                            + "=" + URLEncoder.encode("DEV", "UTF-8");
                    break;
            }
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

    //Updates the accounts internally within this class.
    private static void updateAccounts() {
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
}
