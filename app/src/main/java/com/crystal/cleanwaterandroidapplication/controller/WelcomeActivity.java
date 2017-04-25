package com.crystal.cleanwaterandroidapplication.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.crystal.cleanwaterandroidapplication.R;

/**
 * Controls the welcome screen of the app, sending the user to login or register
 * @author Team 62
 */
public class WelcomeActivity extends AppCompatActivity {

    //UI References
    private ImageButton signInButton;
    private ImageButton registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        signInButton = (ImageButton) findViewById(R.id.SignInImageButton);
        registerButton = (ImageButton) findViewById(R.id.RegisterImageButton);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}
