package com.crystal.cleanwaterandroidapplication.model;

import com.google.android.gms.maps.model.LatLng;

/**
 *
 */
public class WaterQualityReport extends WaterSourceReport {

    private double virusPPM;
    private double contaminantPPM;
    private int month;
    private int year;

    public WaterQualityReport() {
        virusPPM = -1;
        contaminantPPM = -1;
    }

    public WaterQualityReport(Integer reportNumber, String reportOwnerUsername, LatLng location,
                              WaterType waterType, WaterCondition waterCondition, double virusPPM,
                              double contaminantPPM) {
        super(reportNumber, reportOwnerUsername, location, waterType, waterCondition);
        this.virusPPM = virusPPM;
        this.contaminantPPM = contaminantPPM;
    }

    public WaterQualityReport(Integer reportNumber, String reportOwnerUsername, LatLng location,
                              WaterType waterType, WaterCondition waterCondition, double virusPPM,
                              double contaminantPPM, int month, int year) {
        super(reportNumber, reportOwnerUsername, location, waterType, waterCondition);
        this.virusPPM = virusPPM;
        this.contaminantPPM = contaminantPPM;
        this.month = month;
        this.year = year;
    }

    public double getVirusPPM() {
        return virusPPM;
    }

    public double getContaminantPPM() {
        return contaminantPPM;
    }
}
