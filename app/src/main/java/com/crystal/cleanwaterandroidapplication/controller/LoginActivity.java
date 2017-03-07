package com.crystal.cleanwaterandroidapplication.controller;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.crystal.cleanwaterandroidapplication.R;
import com.crystal.cleanwaterandroidapplication.model.AccountManager;
import com.crystal.cleanwaterandroidapplication.model.InvalidCredentialsException;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via username and password. Currently, simply uses
 * AccountManager for logging in.
 */
public class LoginActivity extends AppCompatActivity {

    // UI references.
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;

    // Login thread reference
    private UserLoginTask loginTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set up the login form.
        usernameEditText = (EditText) findViewById(R.id.usernameLogin);
        passwordEditText = (EditText) findViewById(R.id.passwordLogin);
        loginButton = (Button) findViewById(R.id.SignInButton);

        loginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
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

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String username;
        private final String password;
        private final AccountManager accountManager = new AccountManager();

        UserLoginTask(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        protected Boolean doInBackground(Void ... params) {
            try {
                //Attempt to login the account
                accountManager.login(username, password);
            } catch (InvalidCredentialsException e) {
                //Login failed
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
                usernameEditText.setError("Incorrect username and/or password");
                passwordEditText.setError("Incorrect username and/or password");
            }
        }

        @Override
        protected void onCancelled() {
            loginTask = null;
        }
    }
}

