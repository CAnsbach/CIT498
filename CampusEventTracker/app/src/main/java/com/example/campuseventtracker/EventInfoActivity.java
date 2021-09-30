package com.example.campuseventtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class EventInfoActivity extends AppCompatActivity implements OnMapReadyCallback
{
    TextView txtName;
    TextView txtLocation;
    TextView txtDescription;
    TextView txtDate;
    TextView txtTime;
    EventInfo event;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_info);

        getSupportActionBar().setTitle("CET - Event Information");

        event = (EventInfo) getIntent().getSerializableExtra("event");

        txtName = findViewById(R.id.txtDispEventName);
        txtLocation = findViewById(R.id.txtDispEventLocation);
        txtDescription = findViewById(R.id.txtDispEventDescription);
        txtDate = findViewById(R.id.txtDispEventDate);
        txtTime = findViewById(R.id.txtDispEventTime);

        if (event.getName() != null)
        {
            txtName.setText(event.getName());
        }
        if (event.getLocation() != null)
        {
            txtLocation.setText(event.getLocation());
        }
        if (event.getDescription() != null)
        {
            txtDescription.setText(event.getDescription());
        }
        if (event.getDate() != null)
        {
            txtDate.setText(event.getDate());
        }
        if (event.getTime() != null)
        {
            txtTime.setText(event.getTime());
        }

        // Set the layout file as the content view.
        setContentView(R.layout.activity_main);

        // Get a handle to the fragment and register the callback.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng eventLocation = new LatLng(Float.parseFloat(event.getLatitude()), Float.parseFloat(event.getLongitude()));
        googleMap.addMarker(new MarkerOptions()
                .position(eventLocation)
                .title("Event"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(eventLocation));
    }
}