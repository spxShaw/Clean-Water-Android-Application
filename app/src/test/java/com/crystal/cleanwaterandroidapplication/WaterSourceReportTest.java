package com.crystal.cleanwaterandroidapplication;

import com.crystal.cleanwaterandroidapplication.model.WaterCondition;
import com.crystal.cleanwaterandroidapplication.model.WaterSourceReport;
import com.crystal.cleanwaterandroidapplication.model.WaterType;
import com.google.android.gms.maps.model.LatLng;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit to test WaterSourceReport
 * @author Team 62
 * @version 1.0
 */

public class WaterSourceReportTest {
    WaterSourceReport report;
    WaterSourceReport nullReport;
    WaterSourceReport reportComp;

    @Before
    /**
     * Setting up 3 WaterSourceReports with two identical
     */
    public void setUp() {
        report = new WaterSourceReport(100, "asdf", new LatLng(51.5033640, -0.1276250), WaterType.LAKE, WaterCondition.TREATABLE_CLEAR);
        reportComp = new WaterSourceReport(100, "asdf", new LatLng(51.5033640, -0.1276250), WaterType.LAKE, WaterCondition.TREATABLE_CLEAR);
        nullReport = new WaterSourceReport();
    }

    @Test
    /**
     * Tests the different methods in WaterSourceReport with the three reports in the setUp
     */
    public void testWaterSourceReport() {
        boolean expected = true;
        boolean expected2 = false;

        Assert.assertEquals("Expected to be true", expected, report.isOwner("asdf"));
        Assert.assertEquals("Expected to be true", expected, nullReport.isOwner(null));

        Assert.assertEquals("Expected to be false", expected2, report.isOwner("wert"));
        Assert.assertEquals("Expected to be false", expected2, nullReport.isOwner("as"));

        Assert.assertEquals("Expected to be true", expected, report.isWaterType(WaterType.LAKE));
        Assert.assertEquals("Expected to be true", expected, nullReport.isWaterType(null));

        Assert.assertEquals("Expected to be false", expected2, report.isWaterType(WaterType.BOTTLED));
        Assert.assertEquals("Expected to be false", expected2, nullReport.isWaterType(WaterType.BOTTLED));

        Assert.assertEquals("Expected to be true... equal", expected, report.equals(reportComp));
        Assert.assertEquals("Expected to be false... not equal", expected2, report.equals(nullReport));
    }
}
