package com.crystal.cleanwaterandroidapplication.controller;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.crystal.cleanwaterandroidapplication.R;
import com.crystal.cleanwaterandroidapplication.model.AccountManager;
import com.crystal.cleanwaterandroidapplication.model.Account;
import com.crystal.cleanwaterandroidapplication.model.Permission;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Activity controlling the registration view. Creates an account, and passes that account
 * over to the AccountManager for storage.
 * @author Team 62
 */
public class RegisterActivity extends AppCompatActivity {
    //UI Reference
    private EditText emailEditText;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText passwordVerifyEditText;
    private Spinner accountSpinner;
    private ImageButton RegisterButton;

    /*
     * Checks to see if the email is a valid email.
     * Returns true if email is a valid, false if invalid.
     */
    private boolean verifyEmail(String email) {
        //Method from stack overflow solution to verifying email.
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(email);
        return mat.matches();
    }

    /*
     * Checks to see if the username is only letters or numbers.
     * Returns true if username is valid, false if invalid.
     */
    private boolean verifyUsername(String username) {
        boolean flag = true;
        for(int i = 0; i < username.length(); i++) {
            if (!(((int) username.charAt(i)) >= 48 && ((int) username.charAt(i)) <= 57)
                    && !(((int) username.charAt(i)) >= 65 && ((int) username.charAt(i)) <= 90)
                    && !(((int) username.charAt(i)) >= 97 && ((int) username.charAt(i)) <= 122)) {
                return false;
            }
        }
        return true;
    }

    /*
     * Checks to see if the password is a valid password. Currently, checks if
     * the password is at least length 7, contains at least a capital letter, lowercase letter, and
     * a number. Returns true if password is valid, false if invalid.
     */
    private boolean verifyPassword(String pass) {
        boolean hadUpperCase = false;
        boolean hadLowerCase = false;
        boolean hadNumber = false;
        if (pass.length() < 7) {
            return false;
        } else {
            for(int i = 0; i < pass.length(); i++) {
                if (((int) pass.charAt(i)) >= 48 && ((int) pass.charAt(i)) <= 57) {
                    //Number
                    hadNumber = true;
                } else if (((int) pass.charAt(i)) >= 65 && ((int) pass.charAt(i)) <= 90) {
                    //UpperCase
                    hadUpperCase = true;
                } else if (((int) pass.charAt(i)) >= 97 && ((int) pass.charAt(i)) <= 122) {
                    //LowerCase
                    hadLowerCase = true;
                } else {
                    return false;
                }
            }
        }

        return hadUpperCase && hadLowerCase && hadNumber;
    }

    /*
     * Checks to see if the email is already being used in the system.
     * Returns true if email exists, false if email does not exist
     */
    private boolean checkEmailExists(String email) {
        return false;
    }

    /*
     * Checks to see if the username already exists.
     * Returns true if the username exists in the AccountManager,
     * false if username does not exist in the AccountManager.
     */
    private boolean checkUsernameExists(String username) {
        return AccountManager.usernameExists(username);
    }

    /*
     * Checks to see if a EditText field is empty. Returns true if a field is empty, false if
     * none of the fields are empty.
     */
    private boolean isFieldBlank() {
        System.out.println(emailEditText.getText().toString());
        return emailEditText.getText().toString().equals("") ||
                usernameEditText.getText().toString().equals("") ||
                passwordEditText.getText().toString().equals("") ||
                passwordVerifyEditText.getText().toString().equals("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Setup UI items.
        emailEditText = (EditText) findViewById(R.id.email);
        usernameEditText = (EditText) findViewById(R.id.username);
        passwordEditText = (EditText) findViewById(R.id.passwordLogin);
        passwordVerifyEditText = (EditText) findViewById(R.id.verifyPassword);
        RegisterButton = (ImageButton) findViewById(R.id.RegisterImageButton);

        //Add listener to check for valid email.
        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if(!verifyEmail(s.toString())) {
                    emailEditText.setError("Invalid Email");
                } else if (checkEmailExists(s.toString())) {
                    emailEditText.setError("Email Already Exists");
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

        //Add listener to check for valid password.
        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (!verifyPassword(s.toString())) {
                    passwordEditText.setError("Password must be at least 7 characters and contain"
                            + " a uppercase, lowercase, and number");
                } else if (!s.toString().equals(passwordVerifyEditText.getText().toString())) {
                    passwordVerifyEditText.setError("Passwords do not match");
                } else {
                    passwordEditText.setError(null);
                    passwordVerifyEditText.setError(null);
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

        passwordVerifyEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals(passwordEditText.getText().toString())) {
                    passwordVerifyEditText.setError("Passwords do not match");
                } else if (!verifyPassword(s.toString())) {
                    passwordEditText.setError("Password must be at least 7 characters and contain"
                            + " a uppercase, lowercase, and number");
                } else {
                    passwordEditText.setError(null);
                    passwordVerifyEditText.setError(null);
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

        //Add listener to check for valid username
        usernameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                checkUsername cu = new checkUsername();
                cu.execute(s.toString());
                try {
                    if (cu.get()) {
                        usernameEditText.setError("Username Already Exists");
                    } else if (!verifyUsername(s.toString())) {
                        usernameEditText.setError("Username can only be made up of letters and numbers");
                    } else {
                        usernameEditText.setError(null);
                    }
                } catch (Exception e) {
                    usernameEditText.setError("Problem connecting to database");
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
                //Only register if all fields are not empty, and no errors are present.
                if (!isFieldBlank() && emailEditText.getError() == null && usernameEditText.getError() == null
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
                    String accountType;
                    if (accountChoice == 0) {
                        //User
                        accountType = "USER";
                    } else if (accountChoice == 1) {
                        //Worker
                        accountType = "WORKER";
                    } else if (accountChoice == 2) {
                        //Manager
                        accountType = "MANAGER";
                    } else if (accountChoice == 3) {
                        //Admin
                        accountType = "ADMINISTRATOR";
                    } else {
                        throw new Error("Account Type not found while Registering");
                    }
                    AddAccountTask aat = new AddAccountTask();
                    aat.execute(username, password, email, accountType);
                    try {
                        if (aat.get()) {
                            //Change from RegisterActivity to LoginActivity.
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                        } else {
                            usernameEditText.setError("Problem connecting to database");
                        }
                    } catch (Exception e) {
                        usernameEditText.setError("Problem connecting to database");
                    }
                }
            }
        });

    }


    /**
     * Adds an account to the database
     */
    class AddAccountTask extends AsyncTask<String, String, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            Account a;
            switch(params[3]) {
                case "USER":
                    a = new Account(params[0], params[1], Permission.USER);
                    break;
                case "WORK":
                    a = new Account(params[0], params[1], Permission.WORKER);
                    break;
                case "MANG":
                    a = new Account(params[0], params[1], Permission.MANAGER);
                    break;
                case "ADMN":
                    a = new Account(params[0], params[1], Permission.ADMINISTRATOR);
                    break;
                case "DEV":
                    a = new Account(params[0], params[1], Permission.DEVELOPER);
                    break;
                default:
                    a = new Account(params[0], params[1], Permission.USER);
            }
            a.setEmail(params[2]);
            return AccountManager.addAccount(a);
        }

    }

    class checkUsername extends AsyncTask<String, String, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            return checkUsernameExists(params[0]);
        }
    }
}
