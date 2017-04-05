package com.crystal.cleanwaterandroidapplication.model;


public enum Month {
    JANUARY("January"), FEBRUARY("February"), MARCH("March"), APRIL("April"), MAY("May"),
    JUNE("June"), JULY("July"), AUGUST("August"), SEPTEMBER("September"), OCTOBER("October"),
    NOVEMBER("November"), DECEMBER("December");

    private String stringRep;

    Month(String stringRep) {
        this.stringRep = stringRep;
    }

    public String toString() {
        return stringRep;
    }

    public int getMonthNumber() {
        return this.ordinal() + 1;
    }

    public static int getMonthNumber(Month month) {
        return month.ordinal() + 1;
    }

    public static Month getMonth(int monthNumber) {
        switch (monthNumber) {
            case 1:
                return JANUARY;
            case 2:
                return FEBRUARY;
            case 3:
                return MARCH;
            case 4:
                return APRIL;
            case 5:
                return MAY;
            case 6:
                return JUNE;
            case 7:
                return JULY;
            case 8:
                return AUGUST;
            case 9:
                return SEPTEMBER;
            case 10:
                return OCTOBER;
            case 11:
                return NOVEMBER;
            case 12:
                return DECEMBER;
            default:
                return null;
        }
    }
}
