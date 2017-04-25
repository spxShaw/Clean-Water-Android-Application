package com.crystal.cleanwaterandroidapplication.model;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Object to contain all the information of a HistoryGraph. When initialized, will get all
 * necessary data from the ReportManager and allow you to retrieve individual month's averages.
 * @author Team 62
 */
public class HistoryGraph {
    //References to describe history graph
    private int year;
    private HistoryGraphType graphType;

    //References to Collections containing each months data
    private Collection<Double> january;
    private Collection<Double> february;
    private Collection<Double> march;
    private Collection<Double> april;
    private Collection<Double> may;
    private Collection<Double> june;
    private Collection<Double> july;
    private Collection<Double> august;
    private Collection<Double> september;
    private Collection<Double> october;
    private Collection<Double> november;
    private Collection<Double> december;

    /*@
      @   public normal_behavior
      @     requires year > 2015 && year < 2020 && graphType != null;
      @*/
    /**
     * Creates a history graph using location, year, and graphType. Gets all the data points from
     * the WaterReportManager.
     * @param year the year of the history graph
     * @param graphType the type of graph
     */
    public HistoryGraph(int year, HistoryGraphType graphType) {
        System.out.println("Year Received: " + year);

        //Initialize references
        this.year = year;
        this.graphType = graphType;
        january = new ArrayList<>();
        february = new ArrayList<>();
        march = new ArrayList<>();
        april = new ArrayList<>();
        may = new ArrayList<>();
        june = new ArrayList<>();
        july = new ArrayList<>();
        august = new ArrayList<>();
        september = new ArrayList<>();
        october = new ArrayList<>();
        november = new ArrayList<>();
        december = new ArrayList<>();

        //Add data points
        for (WaterSourceReport report : WaterReportManager.getReportsFromYear(year)) {
            System.out.println("Reports year: " + report.getYear());
            System.out.println("Reports month: " + report.getMonth());
            //if (report instanceof WaterQualityReport) {
                System.out.println("Got here");
                switch (report.getMonth()) {
                    case 0:
                        if (graphType == HistoryGraphType.CONTAMINANT_PPM) {
                            april.add(((WaterQualityReport) report).getContaminantPPM());
                        } else if (graphType == HistoryGraphType.VIRUS_PPM) {
                            april.add(((WaterQualityReport) report).getVirusPPM());
                        }
                    case 1:
                        if (graphType == HistoryGraphType.CONTAMINANT_PPM) {
                            january.add(((WaterQualityReport) report).getContaminantPPM());
                        } else if (graphType == HistoryGraphType.VIRUS_PPM) {
                            january.add(((WaterQualityReport) report).getVirusPPM());
                        }
                        break;
                    case 2:
                        if (graphType == HistoryGraphType.CONTAMINANT_PPM) {
                            february.add(((WaterQualityReport) report).getContaminantPPM());
                        } else if (graphType == HistoryGraphType.VIRUS_PPM) {
                            february.add(((WaterQualityReport) report).getVirusPPM());
                        }
                        break;
                    case 3:
                        if (graphType == HistoryGraphType.CONTAMINANT_PPM) {
                            march.add(((WaterQualityReport) report).getContaminantPPM());
                        } else if (graphType == HistoryGraphType.VIRUS_PPM) {
                            march.add(((WaterQualityReport) report).getVirusPPM());
                        }
                        break;
                    case 4:
                        if (graphType == HistoryGraphType.CONTAMINANT_PPM) {
                            april.add(((WaterQualityReport) report).getContaminantPPM());
                        } else if (graphType == HistoryGraphType.VIRUS_PPM) {
                            april.add(((WaterQualityReport) report).getVirusPPM());
                        }
                        break;
                    case 5:
                        if (graphType == HistoryGraphType.CONTAMINANT_PPM) {
                            may.add(((WaterQualityReport) report).getContaminantPPM());
                        } else if (graphType == HistoryGraphType.VIRUS_PPM) {
                            may.add(((WaterQualityReport) report).getVirusPPM());
                        }
                        break;
                    case 6:
                        if (graphType == HistoryGraphType.CONTAMINANT_PPM) {
                            june.add(((WaterQualityReport) report).getContaminantPPM());
                        } else if (graphType == HistoryGraphType.VIRUS_PPM) {
                            june.add(((WaterQualityReport) report).getVirusPPM());
                        }
                        break;
                    case 7:
                        if (graphType == HistoryGraphType.CONTAMINANT_PPM) {
                            july.add(((WaterQualityReport) report).getContaminantPPM());
                        } else if (graphType == HistoryGraphType.VIRUS_PPM) {
                            july.add(((WaterQualityReport) report).getVirusPPM());
                        }
                        break;
                    case 8:
                        if (graphType == HistoryGraphType.CONTAMINANT_PPM) {
                            august.add(((WaterQualityReport) report).getContaminantPPM());
                        } else if (graphType == HistoryGraphType.VIRUS_PPM) {
                            august.add(((WaterQualityReport) report).getVirusPPM());
                        }
                        break;
                    case 9:
                        if (graphType == HistoryGraphType.CONTAMINANT_PPM) {
                            september.add(((WaterQualityReport) report).getContaminantPPM());
                        } else if (graphType == HistoryGraphType.VIRUS_PPM) {
                            september.add(((WaterQualityReport) report).getVirusPPM());
                        }
                        break;
                    case 10:
                        if (graphType == HistoryGraphType.CONTAMINANT_PPM) {
                            october.add(((WaterQualityReport) report).getContaminantPPM());
                        } else if (graphType == HistoryGraphType.VIRUS_PPM) {
                            october.add(((WaterQualityReport) report).getVirusPPM());
                        }
                        break;
                    case 11:
                        if (graphType == HistoryGraphType.CONTAMINANT_PPM) {
                            november.add(((WaterQualityReport) report).getContaminantPPM());
                        } else if (graphType == HistoryGraphType.VIRUS_PPM) {
                            november.add(((WaterQualityReport) report).getVirusPPM());
                        }
                        break;
                    case 12:
                        if (graphType == HistoryGraphType.CONTAMINANT_PPM) {
                            december.add(((WaterQualityReport) report).getContaminantPPM());
                        } else if (graphType == HistoryGraphType.VIRUS_PPM) {
                            december.add(((WaterQualityReport) report).getVirusPPM());
                        }
                        break;
                }
            //}
        }
    }

    /*@
      @   public normal_behavior
      @     ensures \result >= 0;
      @*/
    /**
     * Gets the average of the January points
     * @return The average of the January points
     */
    public double getJanuaryAverage() {
        double sum = 0;
        int count = 0;
        for (Double num : january) {
            sum += num;
            count++;
        }
        if (count == 0) {
            return 0;
        } else {
            return sum / count;
        }
    }

    /*@
      @   public normal_behavior
      @     ensures \result >= 0;
      @*/
    /**
     * Gets the average of the February points
     * @return The average of the February points
     */
    public double getFebruaryAverage() {
        double sum = 0;
        int count = 0;
        for (Double num : february) {
            sum += num;
            count++;
        }
        if (count == 0) {
            return 0;
        } else {
            return sum / count;
        }
    }

    /*@
      @   public normal_behavior
      @     ensures \result >= 0;
      @*/
    /**
     * Gets the average of the March points
     * @return The average of the March points
     */
    public double getMarchAverage() {
        double sum = 0;
        int count = 0;
        for (Double num : march) {
            sum += num;
            count++;
        }
        if (count == 0) {
            return 0;
        } else {
            return sum / count;
        }
    }

    /*@
      @   public normal_behavior
      @     ensures \result >= 0;
      @*/
    /**
     * Gets the average of the April points
     * @return The average of the April points
     */
    public double getAprilAverage() {
        System.out.println("Added to april!");
        double sum = 0;
        int count = 0;
        for (Double num : april) {
            sum += num;
            count++;
        }
        if (count == 0) {
            return 0;
        } else {
            return sum / count;
        }
    }

    /*@
      @   public normal_behavior
      @     ensures \result >= 0;
      @*/
    /**
     * Gets the average of the May points
     * @return The average of the May points
     */
    public double getMayAverage() {
        double sum = 0;
        int count = 0;
        for (Double num : may) {
            sum += num;
            count++;
        }
        if (count == 0) {
            return 0;
        } else {
            return sum / count;
        }
    }

    /*@
      @   public normal_behavior
      @     ensures \result >= 0;
      @*/
    /**
     * Gets the average of the June points
     * @return The average of the June points
     */
    public double getJuneAverage() {
        double sum = 0;
        int count = 0;
        for (Double num : june) {
            sum += num;
            count++;
        }
        if (count == 0) {
            return 0;
        } else {
            return sum / count;
        }
    }

    /*@
      @   public normal_behavior
      @     ensures \result >= 0;
      @*/
    /**
     * Gets the average of the July points
     * @return The average of the July points
     */
    public double getJulyAverage() {
        double sum = 0;
        int count = 0;
        for (Double num : july) {
            sum += num;
            count++;
        }
        if (count == 0) {
            return 0;
        } else {
            return sum / count;
        }
    }

    /*@
      @   public normal_behavior
      @     ensures \result >= 0;
      @*/
    /**
     * Gets the average of the August points
     * @return The average of the August points
     */
    public double getAugustAverage() {
        double sum = 0;
        int count = 0;
        for (Double num : august) {
            sum += num;
            count++;
        }
        if (count == 0) {
            return 0;
        } else {
            return sum / count;
        }
    }

    /*@
      @   public normal_behavior
      @     ensures \result >= 0;
      @*/
    /**
     * Gets the average of the September points
     * @return The average of the September points
     */
    public double getSeptemberAverage() {
        double sum = 0;
        int count = 0;
        for (Double num : september) {
            sum += num;
            count++;
        }
        if (count == 0) {
            return 0;
        } else {
            return sum / count;
        }
    }

    /*@
      @   public normal_behavior
      @     ensures \result >= 0;
      @*/
    /**
     * Gets the average of the October points
     * @return The average of the October points
     */
    public double getOctoberAverage() {
        double sum = 0;
        int count = 0;
        for (Double num : october) {
            sum += num;
            count++;
        }
        if (count == 0) {
            return 0;
        } else {
            return sum / count;
        }
    }

    /*@
      @   public normal_behavior
      @     ensures \result >= 0;
      @*/
    /**
     * Gets the average of the November points
     * @return The average of the November points
     */
    public double getNovemberAverage() {
        double sum = 0;
        int count = 0;
        for (Double num : november) {
            sum += num;
            count++;
        }
        if (count == 0) {
            return 0;
        } else {
            return sum / count;
        }
    }

    /*@
      @   public normal_behavior
      @     ensures \result >= 0;
      @*/
    /**
     * Gets the average of the December points
     * @return The average of the December points
     */
    public double getDecemberAverage() {
        double sum = 0;
        int count = 0;
        for (Double num : december) {
            sum += num;
            count++;
        }
        if (count == 0) {
            return 0;
        } else {
            return sum / count;
        }
    }


    /**
     * Add to a months average
     * @param d The double to add
     */
    public void addJanuary(Double d) {
        january.add(d);
    }

    /**
     * Add to a months average
     * @param d The double to add
     */
    public void addFebruary(Double d) {
        february.add(d);
    }

    /**
     * Add to a months average
     * @param d The double to add
     */
    public void addMarch(Double d) {
        march.add(d);
    }

    /**
     * Add to a months average
     * @param d The double to add
     */
    public void addApril(Double d) {
        april.add(d);
    }

    /**
     * Add to a months average
     * @param d The double to add
     */
    public void addMay(Double d) {
        may.add(d);
    }

    /**
     * Add to a months average
     * @param d The double to add
     */
    public void addJune(Double d) {
        june.add(d);
    }

    /**
     * Add to a months average
     * @param d The double to add
     */
    public void addJuly(Double d) {
        july.add(d);
    }

    /**
     * Add to a months average
     * @param d The double to add
     */
    public void addAugust(Double d) {
        august.add(d);
    }

    /**
     * Add to a months average
     * @param d The double to add
     */
    public void addSeptember(Double d) {
        september.add(d);
    }

    /**
     * Add to a months average
     * @param d The double to add
     */
    public void addOctober(Double d) {
        october.add(d);
    }

    /**
     * Add to a months average
     * @param d The double to add
     */
    public void addNovember(Double d) {
        november.add(d);
    }

    /**
     * Add to a months average
     * @param d The double to add
     */
    public void addDecember(Double d) {
        december.add(d);
    }
}
