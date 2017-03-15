package com.crystal.cleanwaterandroidapplication.model;

import android.location.Location;


/**
 * Water Source Report. A Water Source Report stores information on the report's ID number,
 * location, account owner/creator, the type of water the report is on, and the condition of
 * the water the report is on.
 * @author Team 62
 */
public class WaterSourceReport {

    private final int reportNumber;
    private final Location location;
    private final Account reportOwner;
    private final WaterType waterType;
    private final WaterCondition waterCondition;

    /**
     * Creates a WaterSourceReport.
     * @param reportNumber The report ID/Number that is unique to this report
     * @param reportOwner The creator/owner of this water report.
     * @param location A Location object that is the report's location.
     * @param waterType The WaterType that the water is in the report
     * @param waterCondition The WaterCondition that the water is in the report.
     */
    public WaterSourceReport(Integer reportNumber, Account reportOwner, Location location,
                             WaterType waterType, WaterCondition waterCondition) {
        this.reportNumber = reportNumber;
        this.reportOwner = reportOwner;
        this.location = location;
        this.waterType = waterType;
        this.waterCondition = waterCondition;
    }

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
        return location.getLatitude();
    }

    /**
     * Gets the longitude location of this report
     * @return the longitude of the location.
     */
    public double getLongitude() {
        return location.getLongitude();
    }

    /**
     * Returns the Location object, containing the location of the report.
     * @return Location object containing the report's location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Gets the WaterType of the report
     * @return the WaterType of the report
     */
    public WaterType getWaterType() {
        return waterType;
    }

    /**
     * Gets the WaterCondition of the report
     * @return the WaterCondition of the report.
     */
    public WaterCondition getWaterCondition() {
        return waterCondition;
    }

    /**
     * Checks to see if an account is the owner of the report.
     * @param account Account to check for report ownership
     * @return True if account is the owner, false if not the owner.
     */
    public boolean isOwner(Account account) {
        return account.equals(this.reportOwner);
    }

    /**
     * Checks to see if an username is the owner of the report
     * @param username username to check for report ownership
     * @return True if username is the owner, false if not the owner.
     */
    public boolean isOwner(String username) {
        return username.equals(reportOwner.getUsername());
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
        s += "Owner: " + reportOwner.getUsername() + "\n";
        s += "Water Type: " + waterType + "\n";
        s += "Water Condition: " + waterCondition;
        return s;
    }
}
