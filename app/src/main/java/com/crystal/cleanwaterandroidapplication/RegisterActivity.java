package com.crystal.cleanwaterandroidapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class RegisterActivity extends AppCompatActivity{



    boolean checkUsername(String username) {
        return true;
    }

    boolean checkPassword(String password) {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);




        Button RegisterButton = (Button) findViewById(R.id.RegisterButton);
        RegisterButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText usernameEditText = (EditText) findViewById(R.id.username); //Get the string from the username box
                String username = usernameEditText.getText().toString();

                EditText passwordEditText = (EditText) findViewById(R.id.password); //Get the string from the password box
                String password = passwordEditText.getText().toString();

                if (checkUsername(username) && checkPassword(password)) {

                    
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                }

                //Set to error
            }
        });


        Spinner spinner  = (Spinner) findViewById(R.id.accountSpinner);
        ArrayAdapter <CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Account_Options_Array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }
}
