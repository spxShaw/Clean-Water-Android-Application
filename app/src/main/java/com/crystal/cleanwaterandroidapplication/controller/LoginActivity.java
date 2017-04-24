package com.crystal.cleanwaterandroidapplication.controller;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.crystal.cleanwaterandroidapplication.R;
import com.crystal.cleanwaterandroidapplication.model.AccountManager;
import com.crystal.cleanwaterandroidapplication.model.BannedAccountException;
import com.crystal.cleanwaterandroidapplication.model.InvalidCredentialsException;

/**
 * A login screen that offers login via username and password. Currently, simply uses
 * AccountManager for logging in.
 */
public class LoginActivity extends AppCompatActivity {

    // UI references.
    private EditText usernameEditText;
    private EditText passwordEditText;
    private ImageButton loginButton;
    private ImageButton cancelButton;
    private EditText forgotPasswordText;
    private Button forgotPasswordButton;

    // Login thread reference
    private UserLoginTask loginTask;
    private PasswordForgetTask forgetTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set up the login form.
        usernameEditText = (EditText) findViewById(R.id.usernameLogin);
        passwordEditText = (EditText) findViewById(R.id.passwordLogin);
        loginButton = (ImageButton) findViewById(R.id.SignInImageButton);
        cancelButton = (ImageButton) findViewById(R.id.CancelSignInImageButton);
        forgotPasswordText = (EditText) findViewById(R.id.ForgotPassword);
        forgotPasswordButton = (Button) findViewById(R.id.ForgotPasswordButton);

        //Setup login button
        loginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        //Setup cancel login button
        cancelButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
                startActivity(intent);
            }
        });

        forgotPasswordButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                forgotPass();
            }
        });
    }


    /*
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        // Reset errors.
        usernameEditText.setError(null);
        passwordEditText.setError(null);
        forgotPasswordText.setError(null);

        // Store values at the time of the login attempt.
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        //Check for empty username field or password field.
        boolean cancel = false;
        if (TextUtils.isEmpty(username)) {
            usernameEditText.setError("Enter a Username");
            cancel = true;
        }
        if (TextUtils.isEmpty(password)) {
            passwordEditText.setError("Enter a Password");
            cancel = true;
        }

        //Login
        if (!cancel) {
            loginTask = new UserLoginTask(username, password);
            loginTask.execute((Void) null);
        }
    }

    /*
     * Login thread. Implemented as a thread for future usability.
     */
    private class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String username;
        private final String password;

        UserLoginTask(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        protected Boolean doInBackground(Void ... params) {
            try {
                //Attempt to login the account
                AccountManager.login(username, password);
            } catch (InvalidCredentialsException e) {
                //Login failed
                return false;
            } catch (BannedAccountException b) {
                return false;
            }
            //Login successful!
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            //Delete Thread
            loginTask = null;

            if (success) {
                //If successful login, send to MainActivity page.
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            } else {
                //Unsuccessful login, show appropriate errors
                usernameEditText.setError("Incorrect username and/or password or Banned Account");
                passwordEditText.setError("Incorrect username and/or password or Banned Account");
            }
        }

        @Override
        protected void onCancelled() {
            loginTask = null;
        }
    }

    private void forgotPass() {
        // Reset errors.
        usernameEditText.setError(null);
        passwordEditText.setError(null);
        forgotPasswordText.setError(null);

        // Store values at the time of the login attempt.
        String email = forgotPasswordText.getText().toString();

        //Check for empty username field or password field.
        boolean cancel = false;
        if (TextUtils.isEmpty(email)) {
            forgotPasswordText.setError("Enter an Email");
            cancel = true;
        }

        //Login
        if (!cancel) {
            forgetTask = new PasswordForgetTask(email);
            forgetTask.execute((Void) null);
        }
    }

    /*
     * Login thread. Implemented as a thread for future usability.
     */
    private class PasswordForgetTask extends AsyncTask<Void, Void, Boolean> {

        private final String email;

        PasswordForgetTask(String email) {
            this.email = email;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            AccountManager.forgetPass(email);
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            forgotPasswordText.setError("Check email for additional instructions");
        }

        @Override
        protected void onCancelled() {
            forgetTask = null;
        }
    }
}

