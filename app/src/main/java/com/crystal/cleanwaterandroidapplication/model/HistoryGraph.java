package com.crystal.cleanwaterandroidapplication.model;

import com.google.android.gms.maps.model.LatLng;

public class HistoryGraph {
    private double ppmPoints[]; //Sorted by month
    private LatLng location;
    private int year;
    private HistoryGraphType graphType;

    public HistoryGraph(LatLng location, int year, HistoryGraphType graphType) {
        this.location = location;
        this.year = year;
        this.graphType = graphType;
    }
}
