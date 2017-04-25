package com.crystal.cleanwaterandroidapplication.model;

/**
 * Used to indicate that an object has permission levels. Relies on the Permission enum for
 * permission states and permission ordering.
 *
 * @see Permission
 * @author Team 62
 */
public interface Permissible {

    /**
     * Checks to see if object has the given permission.
     *
     * @return true if object does have the given permission, false if object does not have the
     * given permission level
     */
    boolean hasPermission(Permission permission);

    /**
     * Updates an objects permission to the given permission.
     *
     * @param permission Permission to update too.
     */
    void updatePermission(Permission permission);
}
