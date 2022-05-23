package com.example.gasemissionsui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.SystemClock;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Marker;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 * Use the DataCollectionFragmentnewInstance} factory method to
 * create an instance of this fragment.
 */
public class DataCollectionFragment extends Fragment  {
    private Context userContext;
    private Marker selectedMarker;
    private Class userClass;
    GoogleMap myMapInstance;

    public DataCollectionFragment(Context context, Class myClass) {
        userClass = myClass;
        userContext = context;
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public void sendSelectedMarker(Marker selected){
        selectedMarker = selected;
    }

    public GoogleMap getMapInstance() {
        return myMapInstance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //create new simple date format to periodically ping the Gas emissions robot for its current location
        SimpleDateFormat myDate = new SimpleDateFormat("HH:mm:ss");

        /*Initalizing view*/
        View view = inflater.inflate(R.layout.fragment_data_collection, container, false);

        /*Initalize map fragment*/
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.collect_map);


        /*Asyncing the map*/
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
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
                gasEmissions.title("Gas Emissions Robot ");
                googleMap.addMarker(gasEmissions).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

                myMapInstance = googleMap;

                //when a marker is clicked go to new Activity where data collected from robot can be seen.
                googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(@NonNull Marker marker) {
                        selectedMarker = marker;
                        ((Collection_data) getActivity()).startGraphActivity(marker);
                        return true;
                    }
                });
            }
        });
        return view;
    }
}