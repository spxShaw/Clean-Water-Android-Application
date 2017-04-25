package com.crystal.cleanwaterandroidapplication.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.crystal.cleanwaterandroidapplication.R;
import com.crystal.cleanwaterandroidapplication.model.WaterReportManager;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Activity to view reports
 * @author Team 62
 */
public class ViewReportsActivity extends AppCompatActivity {
    //UI Reference
    private Spinner reports;
    private Button backButton;

    //Reference to WaterReportManager
    private WaterReportManager waterReportManager = new WaterReportManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_report);

        reports = (Spinner) findViewById(R.id.ReportsSpinner);
        backButton = (Button) findViewById(R.id.backButtonReports);

        Collection<Integer> reportNumbers = new ArrayList<>();
        //for(int i = 1000000; i < waterReportManager.getNextReportNumber(); i++) {
        //    reportNumbers.add(new Integer(i));
        //}

        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        adapter.addAll(reportNumbers);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reports.setAdapter(adapter);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewReportsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

}
