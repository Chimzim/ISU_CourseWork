package com.example.gasemissionsui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.*;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
//import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class mapLogicUI extends AppCompatActivity {
    RequestQueue myQueue = Volley.newRequestQueue(this);
    private  Context userContext;
    private Class userClass;
    public mapLogicUI(Context current, Class currentClass) {
        userContext = current;
        userClass = currentClass;
    }
    /*
    * TODO Add a method that pulls saved Sample Sites and last sample recorded for each sample site
    */

    /*
    * This method will be used in conjunctiuon
     */
  /*  public ArrayList<MarkerOptions> loadSampleSites() throws JSONException {
        //url to where the backend Sample Site database
        String url = "Change to backend database";
        //Array of marker options for use on the frontend UI
        ArrayList<MarkerOptions> loadedSites = new ArrayList<>();
        JsonArrayRequest samplesRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //able to receive data from database
                try{
                    for(int i = 0; i < response.length(); i++) {
                        JSONObject tempSite = response.getJSONObject(i);
                        //parsing through the JSON object grabbing the required fields for creating UI markers
                        String title = tempSite.getString("title");
                        int latitude = Integer.parseInt(tempSite.getString("latitude"));
                        int longitude = Integer.parseInt(tempSite.getString("longitude"));
                        //Creating the new marker and adding it to the return array with parsed information
                        MarkerOptions newMarker = new MarkerOptions().position((new LatLng(latitude, longitude))).title(title);
                        loadedSites.add(newMarker);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Print error message
                Toast.makeText(userContext.getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        return loadedSites;
    }

    public ArrayList<MarkerOptions> loadPathPoints() {
        //url to backend database which holds the path points to construct the path the robot travels on
        String url = "Change to backend database";
        ArrayList<MarkerOptions> loadedPathPoints = new ArrayList<>();
        JsonArrayRequest samplesRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //able to receive data from database
                try {
                    for(int i = 0; i < response.length(); i++) {
                        JSONObject tempPoint = response.getJSONObject(i);
                        //parse JSON object for path point data
                        String position = tempPoint.getString("position");
                        int latitude = Integer.parseInt(tempPoint.getString("latitude"));
                        int longitude = Integer.parseInt(tempPoint.getString("longitude"));
                        //Create a new marker option with the parsed JSON data
                        MarkerOptions temp = new MarkerOptions().position(new LatLng(latitude, longitude)).title(position);
                        loadedPathPoints.add(temp);
                    }
                }catch(Exception e) {
                    e.printStackTrace();
                    Toast.makeText(userContext.getApplicationContext(), "Hmm there was an error while parsing through the JSON object", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Hmmm ... it seems like the request isn't working", Toast.LENGTH_LONG).show();
            }
        });
        return loadedPathPoints;
    }

    public void saveSampleSites(ArrayList<Marker> tempMarkers) throws JSONException {
        //Url to backend database where the sample site are saved
        String url = "Change to backend database";
        JSONArray toSend = new JSONArray();
        //loop through each of the saved sample sites and convert them into a JSON object
        for(int i = 0; i < tempMarkers.size(); i++) {
            JSONObject toAdd = new JSONObject();
            try{
                //convert each Marker to JSON object with corresponding name and value
                toAdd.put("title", tempMarkers.get(i).getTitle());
                toAdd.put("latitude", tempMarkers.get(i).getPosition().latitude);
                toAdd.put("longitude", tempMarkers.get(i).getPosition().longitude);
            }catch(Exception e) {
                e.printStackTrace();
            }
            //add the JSON object to the JSON Array
            toSend.put(toAdd);
        }
        JsonArrayRequest samplesRequest = new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //able to send data to the backend database
                Toast.makeText(userContext.getApplicationContext(), "Successfully saved your sample sites", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(userContext.getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        myQueue.add(samplesRequest);
    }

    public void savePathPoints(ArrayList<Marker> tempPoints) throws JSONException {
        String url = "change to db";
        JSONArray toSend = new JSONArray();
        int tempVal = 0;
        //looping through the entire path and adding each point as a JSON object
        for(int i = 0; i < tempPoints.size(); i++) {
            JSONObject toAdd = new JSONObject();
            try{
                //Adding the contents of each point on the map
                toAdd.put("position", i);
                toAdd.put("longitude", tempPoints.get(i).getPosition().longitude);
                toAdd.put("latitude", tempPoints.get(i).getPosition().latitude);

            }catch (JSONException e) {
                e.printStackTrace();
            }
            //adding each JSON object to the Array
            toSend.put(toAdd);
        }
        JsonArrayRequest pathRequest = new JsonArrayRequest(Request.Method.POST, url, toSend, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //on Successful send to the backend database
                Toast.makeText(userContext.getApplicationContext(), "Success your path was saved", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Print error message
                Toast.makeText(userContext.getApplicationContext(), "Hmmm ... there seemed to be an error while sending your JSON request", Toast.LENGTH_LONG).show();
            }
        });
        myQueue.add(pathRequest);
    }*/

    public void startMyGraphActivity(Marker clickedMarker) {
        Intent myIntent = new Intent(mapLogicUI.this, GraphData.class);
        myIntent.putExtra("markerTitle", clickedMarker.getTitle());
        startActivity(myIntent);
    }
}
