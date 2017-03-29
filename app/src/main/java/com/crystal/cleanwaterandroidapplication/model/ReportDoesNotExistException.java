package com.crystal.cleanwaterandroidapplication.model;

/**
 * Thrown whenever a report cannot be located/found.
 * @author Team 62
 * @see WaterReportManager
 */
public class ReportDoesNotExistException extends Exception {
    public ReportDoesNotExistException() {
        //Do nothing
    }

    public ReportDoesNotExistException(String message) {
        super(message);
    }
}
