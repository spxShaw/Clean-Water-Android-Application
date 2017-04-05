package com.crystal.cleanwaterandroidapplication.controller;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.crystal.cleanwaterandroidapplication.R;
import com.crystal.cleanwaterandroidapplication.model.HistoryGraph;
import com.crystal.cleanwaterandroidapplication.model.HistoryGraphManager;
import com.crystal.cleanwaterandroidapplication.model.HistoryGraphType;
import com.google.android.gms.maps.model.LatLng;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class TrendsActivity extends AppCompatActivity {

    //UI References
    private GraphView graph;

    //Series Reference
    private LineGraphSeries<DataPoint> series;

    //History Graph Reference
    private HistoryGraph historyGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trends);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initialize UI References
        graph = (GraphView) findViewById(R.id.trendsGraph);

        //Initialize History Graph
        historyGraph = HistoryGraphManager.getCurrentHistoryGraph();

        //Initialize Series
        series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(1, historyGraph.getJanuaryAverage()),
                new DataPoint(2, historyGraph.getFebruaryAverage()),
                new DataPoint(3, historyGraph.getMarchAverage()),
                new DataPoint(4, historyGraph.getAprilAverage()),
                new DataPoint(5, historyGraph.getMayAverage()),
                new DataPoint(6, historyGraph.getJuneAverage()),
                new DataPoint(7, historyGraph.getJulyAverage()),
                new DataPoint(8, historyGraph.getAugustAverage()),
                new DataPoint(9, historyGraph.getSeptemberAverage()),
                new DataPoint(10, historyGraph.getOctoberAverage()),
                new DataPoint(11, historyGraph.getNovemberAverage()),
                new DataPoint(12, historyGraph.getDecemberAverage()),
        });

        //Series settings
        series.setTitle("This is the title");
        series.setColor(Color.BLUE);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(10);
        series.setThickness(8);

        //Graph settings
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(1);
        graph.getViewport().setMaxX(12);

        //Add series to the graph
        graph.addSeries(series);
    }

}
