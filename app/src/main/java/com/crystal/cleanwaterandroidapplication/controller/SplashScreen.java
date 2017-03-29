package com.crystal.cleanwaterandroidapplication.controller;

//import android.support.v7.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.crystal.cleanwaterandroidapplication.R;
import com.crystal.cleanwaterandroidapplication.model.AccountManager;
import com.crystal.cleanwaterandroidapplication.model.WaterReportManager;


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
    /**
     * Loads from the database
     */
    class LoadDatabaseTask extends AsyncTask<Integer, Integer, String> {
        @Override
        protected String doInBackground(Integer... params) {
            AccountManager.updateAccounts();
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
