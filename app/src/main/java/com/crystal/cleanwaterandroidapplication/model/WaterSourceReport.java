package com.crystal.cleanwaterandroidapplication.model;

import com.google.android.gms.maps.model.LatLng;


/**
 * Water Source Report. A Water Source Report stores information on the report's ID number,
 * location, account owner/creator, the type of water the report is on, and the condition of
 * the water the report is on.
 * @author Team 62
 */
public class WaterSourceReport {

    private final int reportNumber;
    private final LatLng location;
    private final String reportOwnerUsername;
    private final WaterType waterType;
    private final WaterCondition waterCondition;
    private int month;
    private int year;

    /**
     * Default constructor. Sets everything to null/-1.
     */
    public WaterSourceReport() {
        reportNumber = -1;
        location = null;
        reportOwnerUsername = null;
        waterType = null;
        waterCondition = null;
        month = -1;
        year = -1;
    }


    /*@
      @   public normal_behavior
      @     requires reportNumber >= 0 && reportOwnerUsername != null && location != null
      @                                && waterType != null && waterCondition != null;
      @*/
    /**
     * Creates a WaterSourceReport.
     * @param reportNumber The report ID/Number that is unique to this report
     * @param reportOwnerUsername The creator/owner of this water report.
     * @param location A Location object that is the report's location.
     * @param waterType The WaterType that the water is in the report
     * @param waterCondition The WaterCondition that the water is in the report.
     */
    public WaterSourceReport(Integer reportNumber, String reportOwnerUsername, LatLng location,
                             WaterType waterType, WaterCondition waterCondition) {
        this.reportNumber = reportNumber;
        this.reportOwnerUsername = reportOwnerUsername;
        this.location = location;
        this.waterType = waterType;
        this.waterCondition = waterCondition;
        month = -1;
        year = -1;
    }

    /*@
      @   public normal_behavior
      @     requires reportNumber >= 0 && reportOwnerUsername != null && location != null
      @                                && waterType != null && waterCondition != null
      @                                && month > 0 && year >= 2015 && year <= 2020;
      @*/
    /**
     * Creates a WaterSourceReport with added month and year
     * @param reportNumber The report ID/Number that is unique to this report
     * @param reportOwnerUsername The creator/owner of this water report.
     * @param location A Location object that is the report's location.
     * @param waterType The WaterType that the water is in the report
     * @param waterCondition The WaterCondition that the water is in the report.
     * @param month the month the water was tested
     * @param year the year the water was tested
     */
    public WaterSourceReport(Integer reportNumber, String reportOwnerUsername, LatLng location,
                             WaterType waterType, WaterCondition waterCondition, int month, int year) {
        this.reportNumber = reportNumber;
        this.reportOwnerUsername = reportOwnerUsername;
        this.location = location;
        this.waterType = waterType;
        this.waterCondition = waterCondition;
        this.month = month;
        this.year = year;
    }

    /*@
      @   public normal_behavior
      @     ensures \result >= 0;
      @*/
    /**
     * Gets the report number of the report
     * @return Integer object of the report number
     */
    public Integer getReportNumber() {
        return reportNumber;
    }

    /**
     * Gets the latitude location of this report
     * @return the latitude of the location.
     */
    public double getLatitude() {
        return location.latitude;
    }

    /**
     * Gets the longitude location of this report
     * @return the longitude of the location.
     */
    public double getLongitude() {
        return location.longitude;
    }

    /**
     * Returns the Location object, containing the location of the report.
     * @return Location object containing the report's location
     */
    public LatLng getLocation() {
        return location;
    }

    /*@
      @   public normal_behavior
      @     ensures \result != null;
      @*/
    /**
     * Gets the WaterType of the report
     * @return the WaterType of the report
     */
    public WaterType getWaterType() {
        return waterType;
    }

    /*@
      @   public normal_behavior
      @     ensures \result != null;
      @*/
    /**
     * Gets the WaterCondition of the report
     * @return the WaterCondition of the report.
     */
    public WaterCondition getWaterCondition() {
        return waterCondition;
    }

    /*@
      @   public normal_behavior
      @     ensures \result >= 0;
      @*/
    /**
     * Gets the month of the report
     * @return the month of the report
     */
    public int getMonth() {
        return month;
    }

    /*@
      @   public normal_behavior
      @     ensures \result >= 2015 && \result <= 2020;
      @*/
    /**
     * Gets the year of the report.
     * @return the year of the report.
     */
    public int getYear() {
        return year;
    }



    /**
     * Checks to see if an username is the owner of the report
     * @param username username to check for report ownership
     * @return True if username is the owner, false if not the owner.
     */
    public boolean isOwner(String username) {
        return username.equals(reportOwnerUsername);
    }

    /**
     * Checks to see if the Report's WaterType is the same.
     * @param waterType the WaterType to compare to the Report's WaterType
     * @return True if both WaterTypes are the same, false if different.
     */
    public boolean isWaterType(WaterType waterType) {
        return this.waterType == waterType;
    }

    /**
     * Checks to see if the Report's WaterCondition is the same.
     * @param waterCondition the WaterCondition to compare to the Report's WaterCondition
     * @return True if both WaterConditions are the same, false if different.
     */
    public boolean isWaterCondition(WaterCondition waterCondition) {
        return this.waterCondition == waterCondition;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (!(o instanceof WaterSourceReport)) {
            return false;
        }
        return reportNumber == ((WaterSourceReport) o).getReportNumber();
    }

    @Override
    public String toString() {
        String s = "";
        s += "Report Number: " + reportNumber + "\n";
        s += "Longitude: " + getLongitude() + " Latitude: " + getLatitude() + "\n";
        s += "Owner: " + reportOwnerUsername + "\n";
        s += "Water Type: " + waterType + "\n";
        s += "Water Condition: " + waterCondition;
        s += "Month: " + month;
        s += "Year: " + year;
        return s;
    }
}
