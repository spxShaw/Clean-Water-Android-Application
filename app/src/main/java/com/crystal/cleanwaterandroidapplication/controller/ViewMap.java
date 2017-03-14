package com.crystal.cleanwaterandroidapplication.controller;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.crystal.cleanwaterandroidapplication.R;
import com.crystal.cleanwaterandroidapplication.model.WaterReportManager;
import com.crystal.cleanwaterandroidapplication.model.WaterSourceReport;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ViewMap extends FragmentActivity implements GoogleMap.OnMarkerClickListener, OnMapReadyCallback {

    private GoogleMap mMap;

    private Marker myMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Iterates through the WaterReportManager Hashmap and adds a marker for each report.
        HashMap<Integer, WaterSourceReport> map = WaterReportManager.getWaterReportHashMap();
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            WaterSourceReport report = (WaterSourceReport) pair.getValue();
            LatLng location = new LatLng(report.getLatitude(), report.getLongitude());
            mMap.addMarker(new MarkerOptions().position(location).title("Water Report"));
        }
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }




    //This code needs to be tested as soon as WaterSourceReport is functional.
    @Override
    public boolean onMarkerClick(final Marker marker) {
        //Get the lat/longitude from the marker
        LatLng location = marker.getPosition();
        //Iterate through Hashmap to find the report that matches the selected marker.
        HashMap<Integer, WaterSourceReport> map = WaterReportManager.getWaterReportHashMap();
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            WaterSourceReport report = (WaterSourceReport) pair.getValue();
            if (report.getLatitude() == location.latitude && report.getLongitude() == location.longitude) {
                Intent intent = new Intent(ViewMap.this, ViewReportsActivity.class);
                startActivity(intent);
            }
        }
        return true;
    }

}
