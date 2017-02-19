package com.crystal.cleanwaterandroidapplication;

import java.util.ArrayList;

/**
 * Created by iwbtbp on 2/19/17.
 */

public class AccountsList {

    private static ArrayList<Account> list = new ArrayList<Account>();

    public AccountsList() {

    }

    public ArrayList<Account> getAccountsList() {
        return list;
    }

    public void add(Account newAccount) {
        list.add(newAccount);
    }

    public void remove(String username) {
        for (int i = 0; i <list.size(); i++) {
            if ((list.get(i)).getUsername() == username) {
                list.remove(i);
                break;
            }
        }
    }

    public boolean validCredentials(String username, String password) {
        for (int i = 0; i <list.size(); i++) {
            if ((list.get(i)).getUsername().equals(username)) {
                if (list.get(i).getPassword().equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }

}
