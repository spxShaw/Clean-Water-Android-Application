package com.crystal.cleanwaterandroidapplication;

import com.crystal.cleanwaterandroidapplication.model.HistoryGraph;
import com.crystal.cleanwaterandroidapplication.model.HistoryGraphType;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Spencer Shaw's junit test. Used to test the functionality of the getMonthAverage Methods.
 */

public class SpencerShawTest {

    //fdsafdsa

    HistoryGraph testGraph = new HistoryGraph(2017, HistoryGraphType.CONTAMINANT_PPM);


    double janEmpty, febEmpty, marEmpty, aprilEmpty, mayEmpty, juneEmpty, julyEmpty,augEmpty,sepEmpty,octEmpty,novEmpty,decEmpty ;
    double janWithVal, febWithVal, marWithVal, aprilWithVal, mayWithVal, juneWithVal, julyWithVal, augWithVal, sepWithVal, octWithVal, novWithVal, decWithVal;
    double average;

    @Before
    public void setup() {

        //Initialize references

        janEmpty = testGraph.getJanuaryAverage();
        febEmpty = testGraph.getFebruaryAverage();
        marEmpty = testGraph.getMarchAverage();
        aprilEmpty = testGraph.getAprilAverage();
        mayEmpty = testGraph.getMayAverage();
        juneEmpty = testGraph.getJuneAverage();
        julyEmpty = testGraph.getJulyAverage();
        augEmpty = testGraph.getAugustAverage();
        sepEmpty = testGraph.getSeptemberAverage();
        octEmpty = testGraph.getOctoberAverage();
        novEmpty = testGraph.getNovemberAverage();
        decEmpty = testGraph.getDecemberAverage();

        int n = 4;
        int size = 4;
        double total = 0;
        while (n > -1) {
            total = total + n;
            n--;
        }
        average = total/size;

        for (double i=0; i<n; i++ ) {
            testGraph.addJanuary(i);
            testGraph.addFebruary(i);
            testGraph.addMarch(i);
            testGraph.addApril(i);
            testGraph.addMay(i);
            testGraph.addJune(i);
            testGraph.addJuly(i);
            testGraph.addAugust(i);
            testGraph.addSeptember(i);
            testGraph.addOctober(i);
            testGraph.addNovember(i);
            testGraph.addDecember(i);
        }

        janWithVal = testGraph.getJanuaryAverage();
        febWithVal = testGraph.getFebruaryAverage();
        marWithVal = testGraph.getMarchAverage();
        aprilWithVal = testGraph.getAprilAverage();
        mayWithVal = testGraph.getMayAverage();
        juneWithVal = testGraph.getJuneAverage();
        julyWithVal = testGraph.getJulyAverage();
        augWithVal = testGraph.getAugustAverage();
        sepWithVal = testGraph.getSeptemberAverage();
        octWithVal = testGraph.getOctoberAverage();
        novWithVal = testGraph.getNovemberAverage();
        decWithVal = testGraph.getDecemberAverage();

    }


    @Test
    public void testAverages() {
        //Ensure empty data sets return 0
        Assert.assertTrue(janEmpty == 0);
        Assert.assertTrue(febEmpty == 0);
        Assert.assertTrue(marEmpty == 0);
        Assert.assertTrue(aprilEmpty == 0);
        Assert.assertTrue(mayEmpty == 0);
        Assert.assertTrue(juneEmpty == 0);
        Assert.assertTrue(julyEmpty == 0);
        Assert.assertTrue(augEmpty == 0);
        Assert.assertTrue(sepEmpty == 0);
        Assert.assertTrue(octEmpty == 0);
        Assert.assertTrue(novEmpty == 0);
        Assert.assertTrue(decEmpty == 0);

        //Ensure returns from getAverage methods are the actual averages.
        Assert.assertTrue(janWithVal == average);
        Assert.assertTrue(febWithVal == average);
        Assert.assertTrue(marWithVal == average);
        Assert.assertTrue(aprilWithVal == average);
        Assert.assertTrue(mayWithVal == average);
        Assert.assertTrue(juneWithVal == average);
        Assert.assertTrue(julyWithVal == average);
        Assert.assertTrue(augWithVal == average);
        Assert.assertTrue(sepWithVal == average);
        Assert.assertTrue(octWithVal == average);
        Assert.assertTrue(novWithVal == average);
        Assert.assertTrue(decWithVal == average);

    }

}
