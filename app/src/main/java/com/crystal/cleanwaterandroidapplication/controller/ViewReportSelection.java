package com.crystal.cleanwaterandroidapplication.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.crystal.cleanwaterandroidapplication.R;

/**
 * Controls the viewing of reports
 * @author Team 62
 */
public class ViewReportSelection extends AppCompatActivity {

    //UI References
    private ImageButton LookUpReportButton;
    private ImageButton TrendsButton;

    //Reference to this context (useful for the alerts)
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_report_selection);

        /* BUTTON SETUP */
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
                //Go to view the graph
                Intent intent = new Intent(ViewReportSelection.this, TrendSetupActivity.class);
                startActivity(intent);
            }
        });
    }
}
