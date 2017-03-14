package com.crystal.cleanwaterandroidapplication.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;

import com.crystal.cleanwaterandroidapplication.R;
import com.crystal.cleanwaterandroidapplication.model.WaterReportManager;
import com.crystal.cleanwaterandroidapplication.model.WaterSourceReport;

public class SubmitReportActivity extends AppCompatActivity {

    //UI Reference
    private TextView ReportID;
    private Button SubmitReport;

    //Reference to WaterReportManager
    private WaterReportManager waterReportManager = new WaterReportManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_report);

        ReportID = (TextView) findViewById(R.id.ReportID);
        SubmitReport = (Button) findViewById(R.id.MakeReportButton);

        ReportID.setText("" + waterReportManager.getNextReportNumber());

        Spinner spinner1 = (Spinner) findViewById(R.id.WaterTypeSpinner);
        Spinner spinner2 = (Spinner) findViewById(R.id.WaterConditionSpinner);
        Spinner spinner3 = (Spinner) findViewById(R.id.LatitudeSpinner);
        Spinner spinner4 = (Spinner) findViewById(R.id.LongitudeSpinner);

        /*
        Insert code for displaying values in the spinners.
         */


        SubmitReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                waterReportManager.addReport(new WaterSourceReport(waterReportManager.getNextReportNumber()));
                waterReportManager.incrementReportNumber();




                Intent intent = new Intent(SubmitReportActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

}
