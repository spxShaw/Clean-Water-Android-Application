package com.crystal.cleanwaterandroidapplication.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.crystal.cleanwaterandroidapplication.R;

public class ViewReportSelection extends AppCompatActivity {

        //UI References
        private ImageButton LookUpReportButton;
        private ImageButton TrendsButton;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_view_report_selection);

            /***** BUTTON SETUP *****/
            LookUpReportButton = (ImageButton) findViewById(R.id.LookUpReportImageButton);
            TrendsButton = (ImageButton) findViewById(R.id.TrendsImageButton);


            LookUpReportButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ViewReportSelection.this, ViewReportsActivity.class);
                    startActivity(intent);
                }
            });

            TrendsButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ViewReportSelection.this, TrendsActivity.class);
                    startActivity(intent);
                }
            });
        }

}
