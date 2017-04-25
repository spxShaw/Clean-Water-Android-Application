package com.crystal.cleanwaterandroidapplication.controller;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.crystal.cleanwaterandroidapplication.R;
import com.crystal.cleanwaterandroidapplication.model.AccountManager;
import com.crystal.cleanwaterandroidapplication.model.BannedAccountException;
import com.crystal.cleanwaterandroidapplication.model.InvalidCredentialsException;

/**
 * Controls the 'Admin' screen of the app.
 * @author Team 62
 */
public class AdminActivity extends AppCompatActivity {

    private EditText username;
    private Button banButton;
    private Button unbanButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        username = (EditText) findViewById(R.id.usernameEnter);
        banButton = (Button) findViewById(R.id.banButton);
        unbanButton = (Button) findViewById(R.id.unbanButton);

        //Setup ban button
        banButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ban();

                Intent intent = new Intent(AdminActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //Setup unban button
        unbanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unban();

                Intent intent = new Intent(AdminActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }

    public void ban() {
        String usernameText = username.getText().toString();
        BanTask banTask = new BanTask(usernameText, "1");
        banTask.execute((Void) null);
    }

    public void unban() {
        String usernameText = username.getText().toString();
        BanTask banTask = new BanTask(usernameText, "0");
        banTask.execute((Void) null);
    }

    /*
     * Login thread. Implemented as a thread for future usability.
     */
    private class BanTask extends AsyncTask<Void, Void, Boolean> {

        private final String username;
        private final String ban;

        BanTask(String username, String ban) {
            this.username = username;
            this.ban = ban;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            //Ban account or unban account
            AccountManager.setBan(username, ban);
            return true;
        }
    }

}
