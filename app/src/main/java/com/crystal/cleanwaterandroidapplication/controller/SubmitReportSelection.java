package com.crystal.cleanwaterandroidapplication.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.crystal.cleanwaterandroidapplication.R;

/**
 * Activity for controlling the submission of WaterReports
 * @author Team 62
 */
public class SubmitReportSelection extends AppCompatActivity {

    //UI References
    private ImageButton AvailabilityReportButton;
    private ImageButton QualityReportButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_report_selection);

        /* BUTTON SETUP */
        AvailabilityReportButton = (ImageButton) findViewById(R.id.AvailabilityReportImageButton);
        QualityReportButton = (ImageButton) findViewById(R.id.QualityReportImageButton);


        AvailabilityReportButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubmitReportSelection.this, SubmitReportActivity.class);
                startActivity(intent);
            }
        });

        QualityReportButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubmitReportSelection.this, SubmitQualityReportActivity.class);
                startActivity(intent);
            }
        });
    }

}
