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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
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
     * Updates reports in the database
     */
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
                int month = jsonObject.getInt("month");
                int year = jsonObject.getInt("year");
                WaterSourceReport report = new WaterSourceReport(reportNumber, reportOwner, location, waterType, waterCondition, month, year);
                newHashMap.put(new Integer(jsonObject.getString("ID")), report);
            }
            URL url2 = new URL("http://mattbusch.net/wp-content/uploads/WaterWorld/loadqualityreport.php");
            HttpURLConnection connection2 = (HttpURLConnection) url2.openConnection();
            connection2.setConnectTimeout(5000);
            connection2.setDoOutput(true);
            InputStream stream2 = connection2.getInputStream();
            BufferedReader br2 = new BufferedReader(new InputStreamReader(stream2));
            String jsonString2 = br2.readLine();
            JSONArray myjsonarray2 = new JSONArray(jsonString2);
            for (int i = 0; i < myjsonarray2.length(); i++) {
                JSONObject jsonObject2 = myjsonarray2.getJSONObject(i);
                Integer reportNumber = (jsonObject2.getInt("ID"));
                String reportOwner = jsonObject2.getString("owner");
                LatLng location = new LatLng(jsonObject2.getDouble("lat"), jsonObject2.getDouble("lon"));
                Log.i("watertype", jsonObject2.getString("watertype"));
                WaterType waterType = WaterType.valueOf(jsonObject2.getString("watertype"));
                Log.i("watercondition", jsonObject2.getString("watercondition"));
                WaterCondition waterCondition = WaterCondition.valueOf(jsonObject2.getString("watercondition"));
                Double virusPPM = jsonObject2.getDouble("virusppm");
                Double contamPPM = jsonObject2.getDouble("contamppm");
                int month = jsonObject2.getInt("month");
                int year = jsonObject2.getInt("year");
                WaterSourceReport report = new WaterQualityReport(reportNumber, reportOwner, location, waterType, waterCondition, virusPPM, contamPPM, month, year);
                newHashMap.put(new Integer(jsonObject2.getString("ID")), report);
            }
            map = newHashMap;
        } catch (Exception E) {
            Log.e("Report Error", E.toString());
        }
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
        DateFormat df = new SimpleDateFormat("M");
        String month = df.format(Calendar.getInstance().getTime());
        df = new SimpleDateFormat("YYYY");
        String year = df.format(Calendar.getInstance().getTime());
        try {
            //TODO: add owner
            URL url = new URL("http://mattbusch.net/wp-content/uploads/WaterWorld/addreport.php");
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
            data += "&" + URLEncoder.encode("owner", "UTF-8")
                    + "=" + URLEncoder.encode(AccountManager.getCurrentAccount().getUsername(), "UTF-8");
            data += "&" + URLEncoder.encode("month", "UTF-8")
                    + "=" + URLEncoder.encode(month, "UTF-8");
            data += "&" + URLEncoder.encode("year", "UTF-8")
                    + "=" + URLEncoder.encode(year, "UTF-8");
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
     * Adds a quality report with the specific data values passed in to the database
     * @param waterType the type of water the report is on
     * @param waterCondition the water condition of water the report is on
     * @param latitude the latitude of the location of the water
     * @param longitude the longitude of the location of the water
     * @param virusPPM the virusPPM of the water
     * @param contamPPM the contamPPM of the water
     * @return whether the quality report was successfully added
     */
    public static boolean addQualityReport(String waterType, String waterCondition, String latitude,
                                           String longitude, String virusPPM, String contamPPM) {
        updateReports();
        DateFormat df = new SimpleDateFormat("M");
        String month = df.format(Calendar.getInstance().getTime());
        df = new SimpleDateFormat("YYYY");
        String year = df.format(Calendar.getInstance().getTime());
        try {
            //TODO: add owner
            URL url = new URL("http://mattbusch.net/wp-content/uploads/WaterWorld/addqualityreport.php");
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
            data += "&" + URLEncoder.encode("contamppm", "UTF-8")
                    + "=" + URLEncoder.encode(contamPPM, "UTF-8");
            data += "&" + URLEncoder.encode("virusppm", "UTF-8")
                    + "=" + URLEncoder.encode(virusPPM, "UTF-8");
            data += "&" + URLEncoder.encode("owner", "UTF-8")
                    + "=" + URLEncoder.encode(AccountManager.getCurrentAccount().getUsername(), "UTF-8");
            data += "&" + URLEncoder.encode("month", "UTF-8")
                    + "=" + URLEncoder.encode(month, "UTF-8");
            data += "&" + URLEncoder.encode("year", "UTF-8")
                    + "=" + URLEncoder.encode(year, "UTF-8");
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

    /**
     * Gets a Collection of all reports from a given year.
     * @param year year to get from
     * @return Collection of all the reports from that year.
     */
    public static Collection<WaterSourceReport> getReportsFromYear(int year) {
        Collection<WaterSourceReport> toReturn = new ArrayList<>();
        for (WaterSourceReport report : map.values()) {
            if (report.getYear() == year) {
                toReturn.add(report);
            }
        }
        return toReturn;
    }

    /**
     * Getter for the waterReportHashMap
     * @return the waterReportHashMap
     */
    public static HashMap<Integer, WaterSourceReport> getWaterReportHashMap() {
        return map;
    }
}
