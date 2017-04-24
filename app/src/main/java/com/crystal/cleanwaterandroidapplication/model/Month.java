package com.crystal.cleanwaterandroidapplication.model;

/**
 * Enum for months. Might be useful later.
 * @author Team 62
 */
public enum Month {
    JANUARY("January"), FEBRUARY("February"), MARCH("March"), APRIL("April"), MAY("May"),
    JUNE("June"), JULY("July"), AUGUST("August"), SEPTEMBER("September"), OCTOBER("October"),
    NOVEMBER("November"), DECEMBER("December");

    private String stringRep;

    Month(String stringRep) {
        this.stringRep = stringRep;
    }

    @Override
    public String toString() {
        return stringRep;
    }

    /*@
      @   public normal_behavior
      @     ensures \result > 0;
      @*/
    /**
     * gets the number equivalent of the month (1 is January, 2 is February, 3 is March etc.)
     * @return the month number
     */
    public int getMonthNumber() {
        return this.ordinal() + 1;
    }

    /*@
      @   public normal_behavior
      @     ensures \result > 0;
      @*/
    /**
     * gets the number equivalent of the specific month passed in (1 is January, 2 is February,
     * 3 is March etc.)
     * @param month the month to get the number of
     * @return the month number
     */
    public static int getMonthNumber(Month month) {
        return month.ordinal() + 1;
    }

    /*@
      @   public normal_behavior
      @     ensures \result != null;
      @*/
    /**
     * Get the month equivalent of a specific number passed in (1 is January, 2 is February,
     * 3 is March etc.)
     * @param monthNumber the month number to get the month of
     * @return the month
     */
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
