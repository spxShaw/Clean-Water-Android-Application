package com.crystal.cleanwaterandroidapplication.model;

/**
 * Water Source Report.
 *
 * @author Team 62
 */

public class WaterSourceReport {

    private int month, day, year; //date of submission
    private int hours, seconds, milliseconds; //time of submission
    private int reportNumber; //auto-generated number assigned to report
    private String reporterName; //name of reporter from account info
    private String location; //location is a String for now
    private WaterType waterType;
    private WaterCondition watercondition;

    public enum WaterType {
        BOTTLED, WELL, STREAM, LAKE, SPRING, OTHER
    }

    public enum WaterCondition {
        WASTE, TREATABLE_CLEAR, TREATABLE_MUDDY, POTABLE
    }

    public WaterSourceReport(Integer reportNumber) {
        this.reportNumber = reportNumber;
    }

    public Integer getReportNumber() {
        return new Integer(reportNumber);
    }
}
