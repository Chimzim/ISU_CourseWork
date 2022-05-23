package com.example.gasemissionsui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.model.Marker;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONException;

import java.util.ArrayList;

public class paths extends AppCompatActivity {
    Button savePathBtn, deleteBtn;
    BottomNavigationView navigationBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paths);
         savePathBtn = (Button) findViewById(R.id.savePath);
         deleteBtn = findViewById(R.id.DeleteP);

    //    mapLogicUI myHelper = new mapLogicUI(getApplicationContext());
        PathFragment toManipulate = new PathFragment(getApplicationContext());
        Fragment myFragment = toManipulate;

        //open map fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_path, myFragment).commit();

        navigationBar = findViewById(R.id.bottomNavView3);
        navigationBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                String selectedItem = (String) item.getTitle();
                if(selectedItem.equals("Robot Info")) {
                    startActivity(new Intent(paths.this, robotStatus.class));
                }else if(selectedItem.equals("HOME")) {
                    startActivity(new Intent(paths.this, MainActivity.class));
                }
                //TODO add home button and then check for those clicks as well
                return false;
            }
        });
        //actionBarDrawerToggle.syncState();

        //allows the navigation bar to always appear on the navigation bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        savePathBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Hmm ... this button doesn't seem to be working", Toast.LENGTH_LONG).show();
                ArrayList<Marker> toSend = toManipulate.savePathPoints();
              //  try {
              //      myHelper.savePathPoints(p.savePathPoints());
              //  } catch (JSONException e) {
               //     e.printStackTrace();
              //  }
            }
        });

        //when the delete button is clicked removed the selected sample site redraw robot path if needed.
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Marker toRemove = toManipulate.getSelectedMarker();
                toManipulate.deleteSelectedMarker();
                Toast.makeText(getApplicationContext(), "Removing ...  "+toRemove.getTitle(), Toast.LENGTH_LONG).show();
            }
        });
    }

}