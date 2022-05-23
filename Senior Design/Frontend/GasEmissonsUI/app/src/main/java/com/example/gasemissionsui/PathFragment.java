package com.example.gasemissionsui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Path;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import java.util.ArrayList;
import android.widget.Toast;


public class PathFragment extends Fragment {


    /**
     * A simple {@link Fragment} subclass
     * Use the {@link com.example.gasemissionsui.MapFragment newInstance} factory method to
     * create an instance of this fragment.
     */

    private int numSites = 0;
    private ArrayList<Marker> pathPoints = new ArrayList<>();
    private ArrayList<Polyline> lineList = new ArrayList<>();
    private Context userContext;
    private Marker selectedMarker = null;
    private GoogleMap myMapInstance;

    public PathFragment(Context context){
        userContext = context;
    }

    public ArrayList<Marker> savePathPoints() {
        return pathPoints;
    }

    public Marker getSelectedMarker() {
        return selectedMarker;
    }

    public void deleteSelectedMarker() {
        if(pathPoints.size() > 0 && selectedMarker != null && pathPoints.contains(selectedMarker)) {
            pathPoints.remove(selectedMarker);
            selectedMarker.remove();
            for(int i = 0; i < lineList.size(); i++) {
                lineList.get(i).remove();
            }
            //if there are still more than 1 marker
            if(pathPoints.size() >= 2) {
                Toast.makeText(userContext, "Loading ... redrawing robot path", Toast.LENGTH_LONG).show();
                for(int i = 0; i < pathPoints.size()-1; i++) {
                    PolylineOptions newLine = new PolylineOptions();
                    newLine.add(pathPoints.get(i).getPosition(), pathPoints.get(i+1).getPosition());
                    newLine.width(5);
                    newLine.color(Color.RED);
                    //add new line to map
                    Polyline mapLine = myMapInstance.addPolyline(newLine);
                    //add the line
                    lineList.add(mapLine);
                }
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*Initalizing view*/
        View view = inflater.inflate(R.layout.fragment_path, container, false);

        /*Initalize map fragment*/
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_map_path);
        /*Asyncing the map*/
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                myMapInstance = googleMap;
                //Changing the map layout to terrain view
                googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                //Once the map is loaded
                //TODO Load Gas Emissions Robot location and camera orientation to be relative
                LatLng robotLocation = new LatLng(42.024475, -93.64782);
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(robotLocation, 17));
                //add Gas Emissions Robot location to the google map
                MarkerOptions gasEmissions = new MarkerOptions();
                gasEmissions.position(robotLocation);
                gasEmissions.title("Gas Emissions Robot Location: " + robotLocation.latitude + ": " + robotLocation.longitude);
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
                        newMarker.title("PathPoint #"+pathPoints.size());
                        //Making all other markers beside the robot yellow
                        newMarker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
                        //Adding sample site marker to google map
                        Marker mapMarker = googleMap.addMarker(newMarker);
                        //setting the tag to monitor the amount of clicks
                        mapMarker.setTag(0);
                        mapMarker.setDraggable(true);
                        //Save the path point
                        pathPoints.add(mapMarker);

                        //updates the map when multiple path points have been added
                        if (pathPoints.size() == 2) {
                                PolylineOptions newLine = new PolylineOptions();
                                newLine.add(pathPoints.get(0).getPosition(), pathPoints.get(1).getPosition());
                                newLine.width(5);
                                newLine.color(Color.RED);
                                //add new line to map
                                Polyline mapLine = googleMap.addPolyline(newLine);
                                //add the line
                                lineList.add(mapLine);
                        }else if(pathPoints.size() > 2){
                            PolylineOptions newLine = new PolylineOptions();
                            newLine.add(pathPoints.get(pathPoints.size()-2).getPosition(), pathPoints.get(pathPoints.size()-1).getPosition());
                            newLine.width(5);
                            newLine.color(Color.RED);
                            //add new line to map
                            Polyline mapLine = googleMap.addPolyline(newLine);
                            //add the line
                            lineList.add(mapLine);
                        }
                    }
                });

                //listens for marker clicks on the map
                googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(@NonNull Marker marker) {
                        //Grab the tag for the marker
                        Integer clickCount = (Integer) marker.getTag();
                        selectedMarker = marker;
                        return true;
                    }
                });
            }
        });
        return view;
    }
}

