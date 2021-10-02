/**
 * Name: Christopher Ansbach
 * Last Updated: 10/1/2021
 * Purpose: Java file to create the Maps instance, place the marker, and zoom in on the given latitude and longitude.
 */

package com.example.campuseventtracker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends Fragment
{
    EventInfo event;
    private final OnMapReadyCallback callback = new OnMapReadyCallback()
    {
        /**
         * When the map is ready, place a marker at the given Latitude and Longitude and zoom in on the marker.
         *
         * @param googleMap Google Maps instance to add the marker to
         */

        @Override
        public void onMapReady(GoogleMap googleMap)
        {

            //Create new LatLng with the latitude and longitude of the event's location
            LatLng eventLocation = new LatLng(Float.parseFloat(event.getLatitude()), Float.parseFloat(event.getLongitude()));

            //Add the marker to the instance and set it's title to be the location's name
            googleMap.addMarker(new MarkerOptions()
                    .position(eventLocation)
                    .title(event.getLocation()));

            //Move the camera to the added marker and zoom in on it
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(eventLocation, 17f));
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        //Initialize view
        View view = inflater.inflate(R.layout.fragment_maps, container, false);

        //Get the event's information from the passed Bundle
        assert getArguments() != null;
        event = (EventInfo) getArguments().getSerializable("event");

        //Return the inflated view
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        //Update the Google Maps instance when the view is created
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null)
        {
            mapFragment.getMapAsync(callback);
        }
    }
}