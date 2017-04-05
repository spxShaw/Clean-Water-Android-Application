package com.crystal.cleanwaterandroidapplication.model;

/**
 * Enum for the different levels of permission: User, Worker, Manager, Administrator, Developer
 * @author Team 62
 */
public enum Permission {
    USER("User"), WORKER("Worker"), MANAGER("Manager"), ADMINISTRATOR("Administrator"),
            DEVELOPER("Developer");

    private String permission;

    Permission(String p) {
        permission = p;
    }

    @Override
    public String toString() {
        return permission;
    }

}
