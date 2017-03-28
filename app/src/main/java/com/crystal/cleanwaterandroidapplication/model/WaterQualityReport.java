package com.crystal.cleanwaterandroidapplication.model;

import com.google.android.gms.maps.model.LatLng;

/**
 *
 */
public class WaterQualityReport extends WaterSourceReport {

    private double virusPPM;
    private double contaminantPPM;

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

    public double getVirusPPM() {
        return virusPPM;
    }

    public double getContaminantPPM() {
        return contaminantPPM;
    }
}
