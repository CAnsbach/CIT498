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
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap)
        {
            LatLng eventLocation = new LatLng(Float.parseFloat(event.getLatitude()), Float.parseFloat(event.getLongitude()));
            googleMap.addMarker(new MarkerOptions()
                    .position(eventLocation)
                    .title("Event"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(eventLocation));
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

        //Get Event Information
        assert getArguments() != null;
        event = (EventInfo) getArguments().getSerializable("event");

        //Initialize map fragment
        SupportMapFragment sMF = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        //Async map
        assert sMF != null;
        sMF.getMapAsync(googleMap -> {
            LatLng eventLocation = new LatLng(Float.parseFloat(event.getLatitude()), Float.parseFloat(event.getLongitude()));
            googleMap.addMarker(new MarkerOptions()
                    .position(eventLocation)
                    .title(event.getLocation()));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(eventLocation, 17f));
        });

        //return view
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null)
        {
            mapFragment.getMapAsync(callback);
        }
    }
}