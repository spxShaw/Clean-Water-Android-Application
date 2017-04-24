package com.crystal.cleanwaterandroidapplication.model;

/**
 * Used to show that an object can be 'banned' from the system.
 *
 * @author Team 62
 */
public interface Bannable {

    /**
     * Marks this object as banned. Returns true if successful, false if not.
     *
     * @return true if successfully banned, false if unsuccessfully banned.
     */
    boolean ban();

    /**
     * Marks this object as not banned (unbanned). Returns true if successful, false if not.
     *
     * @return true if successfully unbanned, false if unsuccessfully unbanned.
     */
    boolean unBan();

    /**
     * Checks if this object is banned. Returns true if banned, false if unbanned.
     *
     * @return true if banned, false if unbanned
     */
    boolean isBanned();
}
