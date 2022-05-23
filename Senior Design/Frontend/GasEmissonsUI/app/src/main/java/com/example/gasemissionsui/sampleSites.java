package com.example.gasemissionsui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.gasemissionsui.MapFragment;
import com.example.gasemissionsui.MapFragment;
import com.google.android.gms.maps.model.Marker;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;

public class sampleSites extends AppCompatActivity {
        Button saveSites, deleteBtn;
        //mapLogicUI myHelper = new mapLogicUI(getApplicationContext());
        BottomNavigationView navigationBar;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_sample_sites);

                saveSites = (Button) findViewById(R.id.saveSites);
                deleteBtn = findViewById(R.id.deleteB);

                MapFragment toManipulate = new MapFragment(getApplicationContext());
                Fragment myFragment = toManipulate;

                //open map fragment
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, myFragment).commit();

                navigationBar = findViewById(R.id.bottomNavView4);
                navigationBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                                String selectedItem = (String) item.getTitle();
                                if(selectedItem.equals("Robot Info")) {
                                        startActivity(new Intent(sampleSites.this, robotStatus.class));
                                }else if(selectedItem.equals("HOME")) {
                                        startActivity(new Intent(sampleSites.this, MainActivity.class));
                                }
                                //TODO add home button and then check for those clicks as well
                                return false;
                        }
                });
                //actionBarDrawerToggle.syncState();

                //allows the navigation bar to always appear on the navigation bar
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                saveSites.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                Toast.makeText(getApplicationContext(), "Hmm ... this button doesn't seem to be working", Toast.LENGTH_LONG).show();
                                ArrayList<Marker> toSave = toManipulate.saveSites();
                              //  try {
                                 //       myHelper.saveSampleSites(toManipulate.saveSites());
                             //   } catch (JSONException e) {
                                //        e.printStackTrace();
                             //   }
                        }
                });

                //when the delete button is selected remove the selected marker
                deleteBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                Marker toRemove = toManipulate.getSelectedMarker();
                                toManipulate.deleteSelectedMarker();
                                Toast.makeText(getApplicationContext(), "Removing "+toRemove.getTitle(), Toast.LENGTH_LONG).show();
                        }
                });

        }
}