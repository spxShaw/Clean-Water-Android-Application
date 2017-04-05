package com.crystal.cleanwaterandroidapplication.model;

import com.google.android.gms.maps.model.LatLng;

/**
 * WaterQualityReport saves all the information of each water quality report including virusPPM,
 * contaminantPPM, month, and year.
 * @author Team 62
 * @see WaterSourceReport
 */
public class WaterQualityReport extends WaterSourceReport {

    private double virusPPM;
    private double contaminantPPM;
    private int month;
    private int year;

    /**
     * Creates a default WaterQualityReport with virusPPM and contaminantPPM both set to -1
     */
    public WaterQualityReport() {
        virusPPM = -1;
        contaminantPPM = -1;
    }

    /**
     * Creates a WaterQualityReport with specific values/pieces of data that are passed in
     * as parameters with no month and year.
     * @param reportNumber the report number
     * @param reportOwnerUsername the username of the owner of the report
     * @param location the location the report is for
     * @param waterType the type of water the report is for
     * @param waterCondition the water condition of the water
     * @param virusPPM the virusPPM of the water
     * @param contaminantPPM the contaminantPPM of the water
     */
    public WaterQualityReport(Integer reportNumber, String reportOwnerUsername, LatLng location,
                              WaterType waterType, WaterCondition waterCondition, double virusPPM,
                              double contaminantPPM) {
        super(reportNumber, reportOwnerUsername, location, waterType, waterCondition);
        this.virusPPM = virusPPM;
        this.contaminantPPM = contaminantPPM;
    }

    /**
     * Creates a WaterQualityReport with specific values/pieces of data that are passed in
     * as parameters
     * @param @param reportNumber the report number
     * @param reportOwnerUsername the username of the owner of the report
     * @param location the location the report is for
     * @param waterType the type of water the report is for
     * @param waterCondition the water condition of the water
     * @param virusPPM the virusPPM of the water
     * @param contaminantPPM the contaminantPPM of the water
     * @param month the month when the water was tested
     * @param year the year when the water was tested
     */
    public WaterQualityReport(Integer reportNumber, String reportOwnerUsername, LatLng location,
                              WaterType waterType, WaterCondition waterCondition, double virusPPM,
                              double contaminantPPM, int month, int year) {
        super(reportNumber, reportOwnerUsername, location, waterType, waterCondition);
        this.virusPPM = virusPPM;
        this.contaminantPPM = contaminantPPM;
        this.month = month;
        this.year = year;
    }

    /**
     * Getter for VirusPPM
     * @return the virusPPM on the report
     */
    public double getVirusPPM() {
        return virusPPM;
    }

    /**
     * Getter for the contaminantPPM
     * @return the contaminantPPM on the report
     */
    public double getContaminantPPM() {
        return contaminantPPM;
    }
}
