package com.crystal.cleanwaterandroidapplication.model;

import com.google.android.gms.maps.model.LatLng;

public class HistoryGraph {
    private double ppmPoints[]; //Sorted by month
    private LatLng location;
    private int year;
    private HistoryGraphType graphType;

    /**
     * Creates a history graph using location, year, and graphType.
     * @param location the location of the history graph
     * @param year the year of the history graph
     * @param graphType the type of graph
     */
    public HistoryGraph(LatLng location, int year, HistoryGraphType graphType) {
        this.location = location;
        this.year = year;
        this.graphType = graphType;
    }
}
