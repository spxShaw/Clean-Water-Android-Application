package com.crystal.cleanwaterandroidapplication.model;

import java.util.HashMap;

/**
 * WaterReportManager stores and managers accessing all water reports. The report ID is the key to
 * the report.
 * @author Team 62
 * @see WaterSourceReport
 */
public class WaterReportManager {
    private static HashMap<Integer, WaterSourceReport> map = new HashMap<>();
    private static Integer currentReportNumber = 1000000; //TODO this should be done better.

    /**
     * Creates a WaterReportManager. Nothing special about it.
     */
    public WaterReportManager() {
        //Do nothing, for now
    }

    /**
     * Adds a WaterSourceReport to the map. Returns true if successfully added, false if
     * that report ID already exists and report cannot be added.
     * @param report WaterSourceReport to add.
     * @return True if successful, false if not.
     */
    public boolean addReport(WaterSourceReport report) {
        if(map.containsKey(report.getReportNumber())) {
            return false;
        } else {
            map.put(report.getReportNumber(),report);
            return true;
        }
    }

    /**
     * Retrieves the report specified by reportNumber.
     * @param reportNumber reportNumber of report to retrieve.
     * @return Report that was retrieved, null if no report found
     */
    public WaterSourceReport getReport(Integer reportNumber) {
        return map.get(reportNumber);
    }
}
