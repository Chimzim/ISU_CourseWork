package com.example.gasemissionsui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;

import android.view.MenuItem;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    Button sampleCollection, createPath, createSample;
    BottomNavigationView drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sampleCollection = findViewById(R.id.Collect);
        createPath = findViewById(R.id.pathB);
        createSample = findViewById(R.id.sampleB);
        drawerLayout = findViewById(R.id.bottomNavView);
        drawerLayout.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                String selectedItem = (String) item.getTitle();
                if(selectedItem.equals("Robot Info")) {
                    startActivity(new Intent(MainActivity.this, robotStatus.class));
                }
                //TODO add home button and then check for those clicks as well
                return false;
            }
        });
        //actionBarDrawerToggle.syncState();

        //allows the navigation bar to always appear on the navigation bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        sampleCollection.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Collection_data.class));
            }
        });

        createSample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, sampleSites.class));
            }
        });

        createPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, paths.class));
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}