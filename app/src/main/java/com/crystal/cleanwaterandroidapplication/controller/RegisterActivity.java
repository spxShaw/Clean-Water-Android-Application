package com.crystal.cleanwaterandroidapplication.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.text.TextWatcher;
import android.text.Editable;

import com.crystal.cleanwaterandroidapplication.R;
import com.crystal.cleanwaterandroidapplication.model.Account;
import com.crystal.cleanwaterandroidapplication.model.User;
import com.crystal.cleanwaterandroidapplication.model.Worker;
import com.crystal.cleanwaterandroidapplication.model.Manager;
import com.crystal.cleanwaterandroidapplication.model.Administrator;
import com.crystal.cleanwaterandroidapplication.model.AccountManager;

/**
 * Activity controlling the registration view. Creates an account, and passes that account
 * over to the AccountManager for storage.
 * @author Team 62
 */
public class RegisterActivity extends AppCompatActivity{
    //Model Reference
    private final AccountManager accountManager = new AccountManager();

    //UI Reference
    private EditText emailEditText;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText passwordVerifyEditText;
    private Spinner  accountSpinner;
    private Button RegisterButton;

    /*
     * Checks to see if the email is a valid email.
     * Returns true if email is a valid, false if invalid.
     */
    private boolean verifyEmail(String email) {
        //TODO Implement VerifyEmail better
        return email.contains("@");
    }

    /*
     * Checks to see if the email is already being used in the system.
     * Returns true if email exists, false if email does not exist
     */
    private boolean checkIfEmailExists(String email) {
        //TODO Implement checkIfEmailExists
        return false;
    }

    /*
     * Checks to see if the username already exists.
     * Returns true if the username exists in the AccountManager,
     * false if username does not exist in the AccountManager.
     */
    private boolean checkUsernameExists(String username) {
        return accountManager.checkForUsername(username);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Setup UI items.
        emailEditText = (EditText) findViewById(R.id.email);
        usernameEditText = (EditText) findViewById(R.id.username);
        passwordEditText = (EditText) findViewById(R.id.password);
        passwordVerifyEditText = (EditText) findViewById(R.id.verifyPassword);
        RegisterButton = (Button) findViewById(R.id.RegisterButton);

        //Add listener to check for valid email.
        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if(!verifyEmail(s.toString())) {
                    emailEditText.setError("Invalid Email");
                } else if (checkIfEmailExists(s.toString())) {
                    //TODO Show error in emailEditText
                } else {
                    emailEditText.setError(null);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Do nothing
            }
        });

        //TODO Add listener to check for valid username.
        usernameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if(checkUsernameExists(s.toString())) {
                    usernameEditText.setError("Username Already Exists");
                } else {
                    usernameEditText.setError(null);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Do nothing
            }
        });

        //TODO Add listener to check for valid password match.

        //Setup Account Spinner.
        final Spinner spinner  = (Spinner) findViewById(R.id.accountSpinner);
        ArrayAdapter <CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Account_Options_Array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //Setup register button click listener.
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check if any text inputs have an error
                if(emailEditText.getError() == null && usernameEditText.getError() == null
                        && passwordEditText.getError() == null) {
                    //Get the string from email box
                    String email = emailEditText.getText().toString();

                    //Get the string from the username box
                    String username = usernameEditText.getText().toString();

                    //Get the string from the password box
                    String password = passwordEditText.getText().toString();

                    //Get the string of the account type
                    //0 -> User, 1 -> Worker, 2 -> Manager, 3 -> Admin
                    int accountChoice = spinner.getSelectedItemPosition();

                    //Add account
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
                    newAccount.setEmail(email);
                    accountManager.add(newAccount);

                    //Change from RegisterActivity to LoginActivity.
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    //TODO prompt user cannot register, due to error
                }
            }
        });

    }
}
