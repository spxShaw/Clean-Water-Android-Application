package com.crystal.cleanwaterandroidapplication.model;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * WaterReportManager stores and managers accessing all water reports. The report ID is the key to
 * the report.
 * @author Team 62
 * @see WaterSourceReport
 */
public class WaterReportManager {
    private static HashMap<Integer, WaterSourceReport> map = new HashMap<>();

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
        return addReport(report.getWaterType().toString(), report.getWaterCondition().toString(),
                new Double(report.getLongitude()).toString(),
                new Double(report.getLatitude()).toString());
    }

    /**
     * Adds a WaterSourceReport to the map. Returns true if successfully added, false if
     * that report ID already exists and report cannot be added.
     *
     * @param waterType the type of the water being added
     * @param waterCondition is the condition of the water being added
     * @param latitude the latitude of the water report
     * @param longitude the longitude of the water report
     * @return True if successful, false if not.
     */
    public static boolean addReport(String waterType, String waterCondition, String latitude, String longitude) {
        updateReports();
        try {
            URL url = new URL("http://mattbusch.net/wp-content/uploads/WaterWorld/adduser.php");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            String data = URLEncoder.encode("wt", "UTF-8")
                    + "=" + URLEncoder.encode(waterType, "UTF-8");
            data += "&" + URLEncoder.encode("wc", "UTF-8") + "="
                    + URLEncoder.encode(waterCondition, "UTF-8");
            data += "&" + URLEncoder.encode("lat", "UTF-8")
                    + "=" + URLEncoder.encode(latitude, "UTF-8");
            data += "&" + URLEncoder.encode("lon", "UTF-8")
                    + "=" + URLEncoder.encode(longitude, "UTF-8");
            writer.write(data);
            writer.close();
            InputStream stream = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            updateReports();
            return true;
        } catch (Exception e) {
            return false;
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
                String reportOwner = jsonObject.getString("owner");
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
