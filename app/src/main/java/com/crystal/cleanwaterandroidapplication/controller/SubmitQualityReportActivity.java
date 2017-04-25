package com.crystal.cleanwaterandroidapplication.controller;

import android.Manifest;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.crystal.cleanwaterandroidapplication.R;
import com.crystal.cleanwaterandroidapplication.model.WaterCondition;
import com.crystal.cleanwaterandroidapplication.model.WaterReportManager;
import com.crystal.cleanwaterandroidapplication.model.WaterType;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;

/**
 * Activity for controlling the submission of WaterQualityReports.
 */
public class SubmitQualityReportActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    //UI Reference
    private TextView ReportID;
    private ImageButton SubmitReport;
    private Spinner WaterTypeSpinner;
    private Spinner WaterConditionSpinner;
    private TextView LatitudeTextView;
    private TextView LongitudeTextView;
    private TextView VirusPPMView;
    private TextView ContaminantPPMView;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_quality_report);

        //Setup UI references
        SubmitReport = (ImageButton) findViewById(R.id.MakeReportButton);
        WaterTypeSpinner = (Spinner) findViewById(R.id.WaterTypeSpinner);
        WaterConditionSpinner = (Spinner) findViewById(R.id.WaterConditionSpinner);
        LatitudeTextView = (TextView) findViewById(R.id.LatitudeTextView);
        LongitudeTextView = (TextView) findViewById(R.id.LongitudeTextView);
        VirusPPMView = (TextView) findViewById(R.id.VirusPPMView);
        ContaminantPPMView = (TextView) findViewById(R.id.ContaminantPPMView);

        //Setup WaterTypeSpinner
        ArrayAdapter<WaterType> WaterTypeSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        WaterTypeSpinnerAdapter.addAll(WaterType.getWaterTypeCollection());
        WaterTypeSpinner.setAdapter(WaterTypeSpinnerAdapter);

        //Setup WaterConditionSpinner
        final ArrayAdapter<WaterCondition> WaterConditionSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        WaterConditionSpinnerAdapter.addAll(WaterCondition.getWaterConditionCollection());
        WaterConditionSpinner.setAdapter(WaterConditionSpinnerAdapter);

        //Setup submit report button
        SubmitReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String waterType = ((WaterType) WaterTypeSpinner.getSelectedItem()).name();
                String waterCondition = ((WaterCondition) WaterConditionSpinner.getSelectedItem()).name();
                String latitude = LatitudeTextView.getText().toString();
                String longitude = LongitudeTextView.getText().toString();
                String virusPPM = VirusPPMView.getText().toString();
                String contamPPM = ContaminantPPMView.getText().toString();
                new AddQualityReportTask().execute(waterType, waterCondition, latitude, longitude,
                        virusPPM, contamPPM);

                Intent intent = new Intent(SubmitQualityReportActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //Setup google API for getting location
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }

    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            //return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            LatitudeTextView.setText(String.valueOf(mLastLocation.getLatitude()));
            LongitudeTextView.setText(String.valueOf(mLastLocation.getLongitude()));
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    private class AddQualityReportTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            boolean success = WaterReportManager.addQualityReport(params[0], params[1], params[2],
                    params[3], params[4], params[5]);
            return "Task Completed.";
        }

    }
}
