package com.crystal.cleanwaterandroidapplication.model;

import java.util.Collection;
import java.util.ArrayList;

//TODO come up with more types, and come up with better string representations

/**
 * Enum for the different conditions water can be.
 *
 * @author TEAM 62
 */
public enum WaterCondition {
    WASTE("Waste"),
    TREATABLE_CLEAR("Treatable_Clear"),
    TREATABLE_MUDDY("Treatable_Muddy"),
    POTABLE("Potable");

    private String stringRepresentation;

    WaterCondition(String s) {
        stringRepresentation = s;
    }

    @Override
    public String toString() {
        return stringRepresentation;
    }

    /*@
      @   public normal_behavior
      @     ensures \result != null;
      @*/
    /**
     * Returns a collection of all different types of possible WaterConditions
     * @return A collection of all different WaterConditions
     */
    public static Collection<WaterCondition> getWaterConditionCollection() {
        ArrayList<WaterCondition> temp = new ArrayList<>();
        temp.add(WASTE);
        temp.add(TREATABLE_CLEAR);
        temp.add(TREATABLE_MUDDY);
        temp.add(POTABLE);
        return temp;
    }
}