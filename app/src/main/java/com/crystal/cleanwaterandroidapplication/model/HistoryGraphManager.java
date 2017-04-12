package com.crystal.cleanwaterandroidapplication.model;

/**
 * Static wrapper class for containing the app's current HistoryGraph. Allows retrieval and setting.
 * @author Team 62
 */
public class HistoryGraphManager {
    private static HistoryGraph currentHistoryGraph;

    private HistoryGraphManager() {
        //Do nothing for now
    }

    /**
     * Sets the current HistoryGraph
     * @param newHistoryGraph The HistoryGraph to set the current HistoryGraph to.
     */
    public static void setCurrentHistoryGraph(HistoryGraph newHistoryGraph) {
        currentHistoryGraph = newHistoryGraph;
    }

    /**
     * Gets the current HistoryGraph
     * @return the current HistoryGraph
     */
    public static HistoryGraph getCurrentHistoryGraph() {
        return currentHistoryGraph;
    }
}
