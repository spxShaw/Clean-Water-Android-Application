package com.crystal.cleanwaterandroidapplication.controller;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.crystal.cleanwaterandroidapplication.model.AccountManager;

import com.crystal.cleanwaterandroidapplication.R;
import com.crystal.cleanwaterandroidapplication.model.Permission;

public class MainActivity extends AppCompatActivity {

    //UI References
    private ImageButton logoutButton;
    private ImageButton editProfileButton;
    private ImageButton submitReportButton;
    private ImageButton viewMapButton;
    private ImageButton submitWaterQualityReportButton;
    private ImageButton viewWaterQualityReportButton;
    private View userMainLayout;
    private View workerMainLayout;
    private View managerMainLayout;
    private View administratorMainLayout;
    private View developerMainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("CleanWaterAndroidApplication");
        setSupportActionBar(toolbar);
        setContentView(R.layout.content_main);
        setSupportActionBar(toolbar);

        /***** BUTTON SETUP *****/
        logoutButton = (ImageButton) findViewById(R.id.LogOutImageButton);
        editProfileButton = (ImageButton) findViewById(R.id.EditProfileImageButton);
        submitReportButton = (ImageButton) findViewById(R.id.SubmitReportImageButton);
        viewMapButton = (ImageButton) findViewById(R.id.ViewMapImageButton);
        //submitWaterQualityReportButton = (ImageButton) findViewById(R.id.SubmitWaterQualityReportImageButton);
        //viewWaterQualityReportButton = (ImageButton) findViewById(R.id.ViewWaterQualityReportImageButton);

        logoutButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                startActivity(intent);
            }
        });

        editProfileButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditProfileActivity.class);
                startActivity(intent);
            }
        });

        submitReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubmitReportSelection.class);
                startActivity(intent);
            }
        });

        viewMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewMap.class);
                startActivity(intent);
            }
        });
/*
        submitWaterQualityReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubmitQualityReportActivity.class);
                startActivity(intent);
            }
        });

*/

        /***** VIEW SETUP *****/
        /*
        userMainLayout = (View) findViewById(R.id.UserMainLayout);
        workerMainLayout = (View) findViewById(R.id.WorkerMainLayout);
        managerMainLayout = (View) findViewById(R.id.ManagerMainLayout);
        administratorMainLayout = (View) findViewById(R.id.AdministratorMainLayout);
        developerMainLayout = (View) findViewById(R.id.DeveloperMainLayout);

        if (AccountManager.getCurrentAccount().hasPermission(Permission.USER)) {
            userMainLayout.setVisibility(View.VISIBLE);
        } else {
            userMainLayout.setVisibility(View.INVISIBLE);
        }

        if (AccountManager.getCurrentAccount().hasPermission(Permission.WORKER)) {
            workerMainLayout.setVisibility(View.VISIBLE);
        } else {
            workerMainLayout.setVisibility(View.INVISIBLE);
        }

        if (AccountManager.getCurrentAccount().hasPermission(Permission.MANAGER)) {
            managerMainLayout.setVisibility(View.VISIBLE);
        } else {
            managerMainLayout.setVisibility(View.INVISIBLE);
        }

        if (AccountManager.getCurrentAccount().hasPermission(Permission.ADMINISTRATOR)) {
            administratorMainLayout.setVisibility(View.VISIBLE);
        } else {
            administratorMainLayout.setVisibility(View.INVISIBLE);
        }

        if (AccountManager.getCurrentAccount().hasPermission(Permission.DEVELOPER)) {
            developerMainLayout.setVisibility(View.VISIBLE);
        } else {
            developerMainLayout.setVisibility(View.INVISIBLE);
        }
        */
    }

}
