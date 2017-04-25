package com.crystal.cleanwaterandroidapplication.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.crystal.cleanwaterandroidapplication.R;
import com.crystal.cleanwaterandroidapplication.model.WaterReportManager;

/**
 * Activity for controlling the splash screen.
 * @author Team 62
 */
public class SplashScreen extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Thread timerThread = new Thread() {
            public void run(){
                try {
                    new LoadDatabaseTask().execute();
                    sleep(2200);
                } catch(InterruptedException e){
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(SplashScreen.this, WelcomeActivity.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }

    /*
     * Loads from the database
     */
    private class LoadDatabaseTask extends AsyncTask<Integer, Integer, String> {
        @Override
        protected String doInBackground(Integer... params) {
            WaterReportManager.updateReports();
            return "Task Completed.";
        }
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }

}
