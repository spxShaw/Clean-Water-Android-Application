package com.crystal.cleanwaterandroidapplication.model;

/**
 * Thrown whenever a report cannot be located/found.
 * @author Team 62
 * @see WaterReportManager
 */
public class ReportDoesNotExistException extends Exception {

    /**
     * Creates a new ReportDoesNotExistException
     */
    public ReportDoesNotExistException() {
        //Do nothing
    }

    /**
     * Creates a new ReportDoesNotExistException with the unique message that is passed in
     * @param message the message to be displayed when the exception occurs
     */
    public ReportDoesNotExistException(String message) {
        super(message);
    }
}
