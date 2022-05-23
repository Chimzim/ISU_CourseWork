package com.example.gasemissionsui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.jjoe64.graphview.series.DataPoint;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Collection_data extends AppCompatActivity {
    Button collectSample;
    GoogleMap myMap;
    BottomNavigationView navigationBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_data);
        collectSample = findViewById(R.id.collectSample1);
        //mapLogicUI myHelper = new mapLogicUI(getApplicationContext());
        Spinner sampleSiteSpinner = findViewById(R.id.sampleList);
        navigationBar = findViewById(R.id.bottomNavView2);
        navigationBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                String selectedItem = (String) item.getTitle();
                if(selectedItem.equals("Robot Info")) {
                    startActivity(new Intent(Collection_data.this, robotStatus.class));
                }else if(selectedItem.equals("HOME")) {
                    startActivity(new Intent(Collection_data.this, MainActivity.class));
                }
                return false;
            }
        });
        //actionBarDrawerToggle.syncState();

        //allows the navigation bar to always appear on the navigation bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //TODO import sample sites from backend database using test data for now

        /*
        --- Spinner Set up -----
        */
        List<String> loadedSites = new ArrayList<String>();
        loadedSites.add("TODO -- NEEDS BACKEND");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, loadedSites);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sampleSiteSpinner.setAdapter(arrayAdapter);

        DataCollectionFragment toManipulate = new DataCollectionFragment(getApplicationContext(), getClass());
        Fragment myFragment = toManipulate;

        //open map fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_path_1, myFragment).commit();



            if (SystemClock.elapsedRealtime() % 5000 == 0) {
                Toast.makeText(getApplicationContext(), "5 Seconds has passed: TODO request new Robot location", Toast.LENGTH_LONG).show();
            }

            // Toast.makeText(getApplicationContext(), Integer.toString((int)SystemClock.elapsedRealtime()),Toast.LENGTH_LONG).show();
            collectSample.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "Hmmm ... this button doesn't seem to be working yet", Toast.LENGTH_SHORT).show();
                }
            });
    }
    public void startGraphActivity(Marker selectedMarker) {
        // pull data on sensors from data base and put them in the intent extras
        Intent newIntent = new Intent(Collection_data.this, GraphData.class);
        newIntent.putExtra("MarkerTitle", selectedMarker.getTitle());
        DataPoint[] temp1 = new DataPoint[200];
        DataPoint[] temp2 = new DataPoint[200];
        DataPoint[] temp3 = new DataPoint[200];
        DataPoint[] temp4 = new DataPoint[200];
        //DataPoint[] temp5 = new DataPoint[20];
        double x = 5, y, z = -5, w;
        for(int i = 0; i < 200; i++) {
            x = x + 0.25;
            y = x;
            temp1[i] = new DataPoint(x, y);
            temp2[i] = new DataPoint(x, y*2);
            temp3[i] = new DataPoint(x, -y*2);
            temp4[i] = new DataPoint(x, y/2);
           // temp5[i] = new DataPoint(i, Math.pow(Math.E, i));

        }
        newIntent.putExtra("data1", temp1);
        newIntent.putExtra("data2", temp2);
        newIntent.putExtra("data3", temp3);
        newIntent.putExtra("data4", temp4);
        //newIntent.putExtra("array5", temp5);
        startActivity(newIntent);
       // Toast.makeText(getApplicationContext(), Integer.toString((int)temp3[0].getX()), Toast.LENGTH_LONG).show();
    }
}