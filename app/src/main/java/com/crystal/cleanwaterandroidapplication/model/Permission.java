package com.crystal.cleanwaterandroidapplication.model;


public enum Permission {
    USER("User"), WORKER("Worker"), MANAGER("Manager"), ADMINISTRATOR("Administrator"),
            DEVELOPER("Developer");

    private String permission;

    Permission(String p) {
        permission = p;
    }

    public String toString() {
        return permission;
    }

}
