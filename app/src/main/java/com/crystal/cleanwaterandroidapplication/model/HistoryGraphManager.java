package com.crystal.cleanwaterandroidapplication.model;


public class HistoryGraphManager {
    private static HistoryGraph currentHistoryGraph;

    public static void setCurrentHistoryGraph(HistoryGraph newHistoryGraph) {
        currentHistoryGraph = newHistoryGraph;
    }

    public static HistoryGraph getCurrentHistoryGraph() {
        return currentHistoryGraph;
    }
}
