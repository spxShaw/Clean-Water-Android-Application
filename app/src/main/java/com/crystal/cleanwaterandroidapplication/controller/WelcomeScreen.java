package com.crystal.cleanwaterandroidapplication.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.crystal.cleanwaterandroidapplication.R;

public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        Button SignInButton = (Button) findViewById(R.id.SignInButton);
        SignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeScreen.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        ImageButton RegisterButton = (ImageButton) findViewById(R.id.RegisterImageButton);
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeScreen.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}
