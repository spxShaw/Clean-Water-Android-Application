package com.crystal.cleanwaterandroidapplication.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.crystal.cleanwaterandroidapplication.R;
import com.crystal.cleanwaterandroidapplication.model.AccountManager;

/**
 * Activity controlling the profile edit view. Makes changes to the current logged in account.
 * @author Team 62
 */
public class EditProfileActivity extends AppCompatActivity {

    //UI References
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private ImageButton submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        //Define UI Elements
        firstNameEditText = (EditText) findViewById(R.id.FirstNameEditProfile);
        lastNameEditText = (EditText) findViewById(R.id.LastNameEditProfile);
        submitButton = (ImageButton) findViewById(R.id.submitEditProfile);

        //Fill EditTexts with current accounts info.
        firstNameEditText.setText(AccountManager.getCurrentAccount().getFirstName());
        lastNameEditText.setText(AccountManager.getCurrentAccount().getLastName());

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Set current Account to info in EditTexts
                AccountManager.getCurrentAccount().setFirstName(firstNameEditText.getText().toString());
                AccountManager.getCurrentAccount().setLastName(lastNameEditText.getText().toString());

                //Change from RegisterActivity to LoginActivity.
                Intent intent = new Intent(EditProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
