package com.crystal.cleanwaterandroidapplication.model;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Enum for the different types of water that a source can be.
 *
 * @author TEAM 62
 */
public enum WaterType {
    BOTTLED("Bottled"),
    WELL("Well"),
    STREAM("Stream"),
    LAKE("Lake"),
    SPRING("Spring"),
    OTHER("Other");

    private String stringRepresentation;

    WaterType(String s) {
        stringRepresentation = s;
    }

    @Override
    public String toString() {
        return stringRepresentation;
    }

    /**
     * Returns a collection of all different types of possible WaterTypes
     * @return A collection of all different WaterTypes
     */
    public static Collection<WaterType> getWaterTypeCollection() {
        ArrayList<WaterType> temp = new ArrayList<>();
        temp.add(BOTTLED);
        temp.add(WELL);
        temp.add(STREAM);
        temp.add(LAKE);
        temp.add(SPRING);
        temp.add(OTHER);
        return temp;
    }
}
