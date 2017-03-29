package com.crystal.cleanwaterandroidapplication.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

/**
 * WaterReportManager stores and managers accessing all water reports. The report ID is the key to
 * the report.
 * @author Team 62
 * @see WaterSourceReport
 */
public class WaterReportManager {
    private static HashMap<Integer, WaterSourceReport> map = new HashMap<>();
    private static Integer nextReportNumber = 1000000; //TODO this should be done better.

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
    public WaterSourceReport getReport(Integer reportNumber) throws ReportDoesNotExistException {
        WaterSourceReport w = map.get(reportNumber);
        if (w == null) {
            throw new ReportDoesNotExistException("Cannot find report number: " + reportNumber);
        } else {
            return w;
        }
    }

    public static HashMap<Integer, WaterSourceReport> getWaterReportHashMap() {
        return map;
    }

    public static void updateReports() {
        try {
            HashMap<Integer, WaterSourceReport> newHashMap = new HashMap<>();
            URL url = new URL("http://mattbusch.net/wp-content/uploads/WaterWorld/loadreport.php");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setDoOutput(true);
            InputStream stream = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            String jsonString = br.readLine();
            JSONArray myjsonarray = new JSONArray(jsonString);
            for (int i = 0; i < myjsonarray.length(); i++) {
                JSONObject jsonObject = myjsonarray.getJSONObject(i);
                Integer reportNumber = (jsonObject.getInt("ID"));
                Account reportOwner = new AccountManager().getAccountByUsername(jsonObject.getString("owner"));
                LatLng location = new LatLng(jsonObject.getDouble("lat"), jsonObject.getDouble("lon"));
                WaterType waterType = WaterType.valueOf(jsonObject.getString("watertype"));
                WaterCondition waterCondition = WaterCondition.valueOf(jsonObject.getString("watercondition"));
                WaterSourceReport report = new WaterSourceReport(reportNumber, reportOwner, location, waterType, waterCondition);
                newHashMap.put(new Integer(jsonObject.getString("ID")), report);
                map = newHashMap;
            }
        } catch (Exception E) {
            Log.e("Error", E.toString());
        }
    }
}
