package com.example.gasemissionsui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class GraphData extends AppCompatActivity {
    GraphView myView1, myView2, myView3, myView4,myView5;
    DataPoint[] data1, data2, data3, data4, data5;
    Button sensor1Btn, sensor2Btn, sensor3Btn, sensor4Btn, sensor5Btn;
    String nameOfMarker="";
    BottomNavigationView navigationBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_data);

        myView1 = findViewById(R.id.graph1);
        nameOfMarker = getIntent().getExtras().getString("MarkerTitle");
        data1 = (DataPoint[]) getIntent().getExtras().get("data1");
        data2 = (DataPoint[]) getIntent().getExtras().get("data2");
        data3 = (DataPoint[]) getIntent().getExtras().get("data3");
        data4 = (DataPoint[]) getIntent().getExtras().get("data4");
       // data5 = (DataPoint[]) getIntent().getExtras().get("array5");
        Toast.makeText(getApplicationContext(), Integer.toString((int)data3[0].getX()), Toast.LENGTH_LONG).show();

        navigationBar = findViewById(R.id.bottomNavView1);
        navigationBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                String selectedItem = (String) item.getTitle();
                if(selectedItem.equals("Robot Info")) {
                    startActivity(new Intent(GraphData.this, robotStatus.class));
                }else if(selectedItem.equals("HOME")) {
                    startActivity(new Intent(GraphData.this, MainActivity.class));
                }
                //TODO add home button and then check for those clicks as well
                return false;
            }
        });
        //actionBarDrawerToggle.syncState();

        //allows the navigation bar to always appear on the navigation bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //TODO Needs to load data from backend database
        LineGraphSeries<DataPoint> CO2_1;

        CO2_1 = new LineGraphSeries<>();
        CO2_1.resetData(data1);
        CO2_1.setColor(Color.RED);
        CO2_1.setTitle("CO2 sensor 1");
        LineGraphSeries<DataPoint> CO2_2 = new LineGraphSeries<DataPoint>();
        CO2_2.resetData(data2);
        CO2_2.setTitle("CO2 sensor 2");
        CO2_2.setColor(Color.BLUE);
        LineGraphSeries<DataPoint> CO2_3 = new LineGraphSeries<DataPoint>();
        CO2_3.resetData(data3);
        CO2_3.setColor(Color.GREEN);
        CO2_3.setTitle("CO2 sensor 3");
        LineGraphSeries<DataPoint> CO2_4 = new LineGraphSeries<DataPoint>();
        CO2_4.resetData(data4);
        CO2_4.setColor(Color.MAGENTA);
        CO2_4.setTitle("CO2 sensor 4");
       /* LineGraphSeries<DataPoint> CO2_5 = new LineGraphSeries<DataPoint>();
        CO2_2.resetData(data5);
        CO2_5.setColor(Color.MAGENTA);
        CO2_5.setTitle("CO2 sensor 5");*/

        myView1.setHorizontalScrollBarEnabled(true);
        myView1.setVerticalScrollBarEnabled(true);
        myView1.getGridLabelRenderer().setHorizontalAxisTitle("Time (needs units)");
        myView1.getGridLabelRenderer().setVerticalAxisTitle("C02 Concentration (ppm)");
        myView1.setTitle("Gas Emissions CO2 sensor 1 data from: "+nameOfMarker);
        myView1.addSeries(CO2_1);
        myView1.addSeries(CO2_2);
        myView1.addSeries(CO2_3);
        myView1.addSeries(CO2_4);

        myView1.getLegendRenderer().setVisible(true);
        myView1.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.BOTTOM);
       // myView1.addSeries(CO2_5);
        //when sensor 1 button is clicked

       Toast.makeText(getApplicationContext(), "Clicked on Sample Site: "+nameOfMarker+ " Showing the extras put in the Intent Activity", Toast.LENGTH_LONG).show();
    }
}