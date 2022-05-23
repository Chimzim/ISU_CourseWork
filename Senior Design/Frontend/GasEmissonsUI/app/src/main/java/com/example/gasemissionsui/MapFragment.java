package com.example.gasemissionsui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass
 * Use the {@link MapFragment newInstance} factory method to
 * create an instance of this fragment.
 */

public class MapFragment extends Fragment {
    private int numSites = 0;
    private ArrayList<Marker> siteList = new ArrayList<>();
    private Context userContext;
    private Marker toDelete = null;

    MapFragment(Context context) {
        userContext = context;
    }

    public ArrayList<Marker> saveSites() {
        return siteList;
    }

    public void deleteSelectedMarker(){
        if(siteList.size() > 0 && toDelete != null && siteList.contains(toDelete)) {
            siteList.remove(toDelete);
            toDelete.remove();
            numSites--;
        }
    }
    public Marker getSelectedMarker(){
        return toDelete;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*Initalizing view*/
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        /*Initalize map fragment*/
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_map);

        /*Asyncing the map*/
        supportMapFragment.getMapAsync(new OnMapReadyCallback()  {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                //Changing the map layout to terrain view
                googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                //TODO Load any saved sample sites onto the map
               // mapLogicUI test = new mapLogicUI(userContext);

                //TODO Load Gas Emissions Robot location and camera orientation to be relative
                LatLng robotLocation = new LatLng(42.024475, -93.64782);
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(robotLocation, 17));
                //add Gas Emissions Robot location to the google map
                MarkerOptions gasEmissions = new MarkerOptions();
                gasEmissions.position(robotLocation);
                gasEmissions.title("Gas Emissions Robot Location: "+ robotLocation.latitude +": "+ robotLocation.longitude);
                googleMap.addMarker(gasEmissions).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

                //TODO Load data from database

                //Listens for clicks on the map
                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        //when the map is clicked add a new Collection site
                        MarkerOptions newMarker = new MarkerOptions();
                        //Set the location of the marker
                        newMarker.position(latLng);
                        //label the marker with the corresponding sample site
                        newMarker.title("Sample site: "+(numSites++)+ " Location: "+latLng.latitude +": " + latLng.longitude);
                        //Adding sample site marker to google map
                        //making all markers added that aren't the robot yellow
                        newMarker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
                        Marker mapMarker = googleMap.addMarker(newMarker);
                        //Save the sample site
                        siteList.add(mapMarker);
                        //setting the tag to monitor the amount of clicks
                        mapMarker.setTag(0);
                        mapMarker.setDraggable(true);
                        Toast.makeText(userContext, "Adding ... " + mapMarker.getTitle(), Toast.LENGTH_LONG).show();
                    }
                });
                //listens for marker clicks on the map
                googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(@NonNull Marker marker) {
                        Integer clickCount = (Integer) marker.getTag();
                        //Show the information of the selected sample site
                        marker.showInfoWindow();
                        //save marker so it can be deleted
                        toDelete = marker;
                        return true;
                    }
                });

            }
        });
        return view;
    }
}