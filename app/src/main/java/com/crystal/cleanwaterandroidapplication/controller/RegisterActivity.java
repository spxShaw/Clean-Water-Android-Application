package com.crystal.cleanwaterandroidapplication.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.crystal.cleanwaterandroidapplication.R;
import com.crystal.cleanwaterandroidapplication.model.Account;
import com.crystal.cleanwaterandroidapplication.model.User;
import com.crystal.cleanwaterandroidapplication.model.Worker;
import com.crystal.cleanwaterandroidapplication.model.Manager;
import com.crystal.cleanwaterandroidapplication.model.Administrator;
import com.crystal.cleanwaterandroidapplication.model.AccountManager;

public class RegisterActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Create and setup Spinner.
        final Spinner spinner  = (Spinner) findViewById(R.id.accountSpinner);
        ArrayAdapter <CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Account_Options_Array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button RegisterButton = (Button) findViewById(R.id.RegisterButton);
        RegisterButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Register button clicked, prompt to register username and password
                //TODO Prompt user to choose a new username if username already exists.

                //Get the string from the username box
                EditText usernameEditText = (EditText) findViewById(R.id.username);
                String username = usernameEditText.getText().toString();

                //Get the string from the password box
                EditText passwordEditText = (EditText) findViewById(R.id.password);
                String password = passwordEditText.getText().toString();

                //Get the string of the account type
                //0 -> User, 1 -> Worker, 2 -> Manager, 3 -> Admin
                int accountChoice = spinner.getSelectedItemPosition();

                //Get the AccountManager
                AccountManager accountManager = new AccountManager();

                if (!accountManager.checkForUsername(username)) {
                    //new username! add account
                    Account newAccount;
                    if (accountChoice == 0) {
                        //User
                        newAccount = new User(username, password);
                    } else if (accountChoice == 1) {
                        //Worker
                        newAccount = new Worker(username, password);
                    } else if (accountChoice == 2) {
                        //Manager
                        newAccount = new Manager(username, password);
                    } else if (accountChoice == 3) {
                        //Admin
                        newAccount = new Administrator(username, password);
                    } else {
                        throw new Error("Account Type not found while Registering");
                    }
                    accountManager.add(newAccount);
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    //prompt username already taken
                }
            }
        });

    }
}
