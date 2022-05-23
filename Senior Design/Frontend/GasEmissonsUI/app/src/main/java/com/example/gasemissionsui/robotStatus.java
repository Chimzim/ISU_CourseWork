package com.example.gasemissionsui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class robotStatus extends AppCompatActivity {
    ProgressBar robotBatteryBar;
    TextView batterPercentText, sensor1Text, sensor2Text, sensor3Text, sensor4Text, sensor5Text, robotJobStatusText;
    BottomNavigationView navigationBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_robot_status);

        //finding all objects
        robotBatteryBar = findViewById(R.id.progressBar);
        batterPercentText = findViewById(R.id.batteryPercent);
        sensor1Text = findViewById(R.id.CO2sensor1);
        sensor2Text = findViewById(R.id.CO2sensor2);
        sensor3Text = findViewById(R.id.CO2sensor3);
        sensor4Text = findViewById(R.id.CO2sensor4);
        sensor5Text = findViewById(R.id.CO2sensor5);
        robotJobStatusText = findViewById(R.id.jobStatus);

        //TODO needs to ping robot to update information from default settings
        robotBatteryBar.setProgress(77);
        robotBatteryBar.setIndeterminate(false);

    }
}