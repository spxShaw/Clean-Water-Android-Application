package com.crystal.cleanwaterandroidapplication.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;

import com.crystal.cleanwaterandroidapplication.R;
import com.crystal.cleanwaterandroidapplication.model.WaterReportManager;
import com.crystal.cleanwaterandroidapplication.model.WaterSourceReport;
import com.crystal.cleanwaterandroidapplication.model.WaterType;
import com.crystal.cleanwaterandroidapplication.model.WaterCondition;

public class SubmitReportActivity extends AppCompatActivity {

    //UI Reference
    private TextView ReportID;
    private Button SubmitReport;
    private Spinner WaterTypeSpinner;
    private Spinner WaterConditionSpinner;
    private Spinner LatitudeSpinner;
    private Spinner LongitudeSpinner;

    //Reference to WaterReportManager
    private WaterReportManager waterReportManager = new WaterReportManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_report);

        //Point UI reference objects to their proper items.
        ReportID = (TextView) findViewById(R.id.ReportID);
        SubmitReport = (Button) findViewById(R.id.MakeReportButton);
        WaterTypeSpinner = (Spinner) findViewById(R.id.WaterTypeSpinner);
        WaterConditionSpinner = (Spinner) findViewById(R.id.WaterConditionSpinner);
        LatitudeSpinner = (Spinner) findViewById(R.id.LatitudeSpinner);
        LongitudeSpinner = (Spinner) findViewById(R.id.LongitudeSpinner);

        //Display ReportID in its TextView
        ReportID.setText("" + waterReportManager.getNextReportNumber());

        //Setup WaterTypeSpinner
        ArrayAdapter<WaterType> WaterTypeSpinnerAdapter = new ArrayAdapter<WaterType>(this, android.R.layout.simple_spinner_item);
        WaterTypeSpinnerAdapter.addAll(WaterType.getWaterTypeCollection());
        WaterTypeSpinner.setAdapter(WaterTypeSpinnerAdapter);

        //Setup WaterConditionSpinner
        ArrayAdapter<WaterCondition> WaterConditionSpinnerAdapter = new ArrayAdapter<WaterCondition>(this, android.R.layout.simple_spinner_item);
        WaterConditionSpinnerAdapter.addAll(WaterCondition.getWaterConditionCollection());
        WaterConditionSpinner.setAdapter(WaterConditionSpinnerAdapter);




        //Setup submit report button
        SubmitReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //waterReportManager.addReport(new WaterSourceReport(waterReportManager.getNextReportNumber()));
                waterReportManager.incrementReportNumber();




                Intent intent = new Intent(SubmitReportActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

}
