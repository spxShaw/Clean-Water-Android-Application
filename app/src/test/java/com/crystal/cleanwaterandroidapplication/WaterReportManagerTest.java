package com.crystal.cleanwaterandroidapplication;

/**
 * Created by christopherpham on 4/25/17.
 */

import com.crystal.cleanwaterandroidapplication.model.ReportDoesNotExistException;
import com.crystal.cleanwaterandroidapplication.model.WaterReportManager;

import org.junit.Before;
import org.junit.Test;

public class WaterReportManagerTest {
    WaterReportManager man;

    @Before
    public void setup() {
        man = new WaterReportManager();
    }

    /**
     * Testing addReport
     */
    @Test
    public void testAddReport() {

    }


    @Test(expected = ReportDoesNotExistException.class)
    public void testGetReport() {
        try {
            man.getReport(32);
        } catch (ReportDoesNotExistException e) {

        }
    }

}
