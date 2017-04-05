package com.crystal.cleanwaterandroidapplication.controller;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.crystal.cleanwaterandroidapplication.R;
import com.crystal.cleanwaterandroidapplication.model.HistoryGraph;
import com.crystal.cleanwaterandroidapplication.model.HistoryGraphManager;
import com.crystal.cleanwaterandroidapplication.model.HistoryGraphType;
import com.crystal.cleanwaterandroidapplication.model.WaterType;

import java.util.ArrayList;
import java.util.Collection;

public class TrendSetupActivity extends AppCompatActivity {
    //UI Reference
    EditText yearEditText;
    Spinner historyGraphTypeSpinner;
    Button generateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trends_setup);

        yearEditText = (EditText) findViewById(R.id.yearEditText);
        historyGraphTypeSpinner = (Spinner) findViewById(R.id.HistoryGraphTypeSpinner);
        generateButton = (Button) findViewById(R.id.GenerateButton);

        //Setup historyGraphTypeSpinner
        ArrayAdapter<HistoryGraphType> historyGraphTypeSpinnerAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item);
        ArrayList<HistoryGraphType> toAdd = new ArrayList<>();
        toAdd.add(HistoryGraphType.CONTAMINANT_PPM);
        toAdd.add(HistoryGraphType.VIRUS_PPM);
        historyGraphTypeSpinnerAdapter.addAll(toAdd);
        historyGraphTypeSpinner.setAdapter(historyGraphTypeSpinnerAdapter);

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = Integer.valueOf(yearEditText.getText().toString());
                HistoryGraphType choice = (HistoryGraphType) historyGraphTypeSpinner.getSelectedItem();
                HistoryGraphManager.setCurrentHistoryGraph(new HistoryGraph(year, choice));

                Intent intent = new Intent(TrendSetupActivity.this, TrendsActivity.class);
                startActivity(intent);
            }
        });
    }
}
