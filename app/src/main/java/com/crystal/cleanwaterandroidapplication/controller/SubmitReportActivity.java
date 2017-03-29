package com.crystal.cleanwaterandroidapplication.controller;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.crystal.cleanwaterandroidapplication.R;
import com.crystal.cleanwaterandroidapplication.model.WaterCondition;
import com.crystal.cleanwaterandroidapplication.model.WaterReportManager;
import com.crystal.cleanwaterandroidapplication.model.WaterType;

public class SubmitReportActivity extends AppCompatActivity {

    //UI Reference
    private TextView ReportID;
    private Button SubmitReport;
    private Spinner WaterTypeSpinner;
    private Spinner WaterConditionSpinner;
    private TextView LatitudeTextView;
    private TextView LongitudeTextView;

    //Reference to WaterReportManager
    private WaterReportManager waterReportManager = new WaterReportManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_report);

        //Point UI reference objects to their proper items.
        //ReportID = (TextView) findViewById(R.id.ReportID);
        SubmitReport = (Button) findViewById(R.id.MakeReportButton);
        WaterTypeSpinner = (Spinner) findViewById(R.id.WaterTypeSpinner);
        WaterConditionSpinner = (Spinner) findViewById(R.id.WaterConditionSpinner);
        LatitudeTextView = (TextView) findViewById(R.id.LatitudeTextView);
        LongitudeTextView = (TextView) findViewById(R.id.LongitudeTextView);

        //Display ReportID in its TextView
        //ReportID.setText("" + waterReportManager.getNextReportNumber());

        //Setup WaterTypeSpinner
        ArrayAdapter<WaterType> WaterTypeSpinnerAdapter = new ArrayAdapter<WaterType>(this, android.R.layout.simple_spinner_item);
        WaterTypeSpinnerAdapter.addAll(WaterType.getWaterTypeCollection());
        WaterTypeSpinner.setAdapter(WaterTypeSpinnerAdapter);

        //Setup WaterConditionSpinner
        final ArrayAdapter<WaterCondition> WaterConditionSpinnerAdapter = new ArrayAdapter<WaterCondition>(this, android.R.layout.simple_spinner_item);
        WaterConditionSpinnerAdapter.addAll(WaterCondition.getWaterConditionCollection());
        WaterConditionSpinner.setAdapter(WaterConditionSpinnerAdapter);




        //Setup submit report button
        SubmitReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String waterType = ((WaterType) WaterTypeSpinner.getSelectedItem()).toString();
                String waterCondition = ((WaterCondition) WaterConditionSpinner.getSelectedItem()).toString();
                String latitude = LatitudeTextView.getText().toString();
                String longitude = LongitudeTextView.getText().toString();
                new AddReportTask().execute(waterType, waterCondition, latitude, longitude);

                Intent intent = new Intent(SubmitReportActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    class AddReportTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            boolean success = WaterReportManager.addReport(params[0], params[1], params[2], params[3]);
            return "Task Completed.";
        }

    }

}
