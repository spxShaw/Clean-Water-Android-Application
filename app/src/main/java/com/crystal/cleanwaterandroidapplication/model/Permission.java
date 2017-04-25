package com.crystal.cleanwaterandroidapplication.model;

/**
 * Enum for the different levels of permission: User, Worker, Manager, Administrator, Developer
 * @author Team 62
 */
public enum Permission {
    USER("User", "USER"),
    WORKER("Worker", "WORK"),
    MANAGER("Manager", "MANG"),
    ADMINISTRATOR("Administrator", "ADMN"),
    DEVELOPER("Developer", "DEV");

    private String permission;
    private String shortCode;

    Permission(String p, String s) {
        permission = p;
        shortCode = s;
    }

    /**
     * Returns the shortCode version of the permission. Useful for things like a database, were
     * storing the least amount of characters is best.
     * @return the shortCode version of the permission.
     */
    public String getShortCode() {
        return shortCode;
    }

    @Override
    public String toString() {
        return permission;
    }

}
