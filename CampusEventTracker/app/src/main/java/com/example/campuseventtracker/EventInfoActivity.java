package com.example.campuseventtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class EventInfoActivity extends AppCompatActivity
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
        txtDescription.setMovementMethod(new ScrollingMovementMethod());
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
        //Initialize fragment

        Fragment fragment = new MapsFragment();

        Bundle eventInfo = new Bundle();

        eventInfo.putSerializable("event", event);

        fragment.setArguments(eventInfo);

        //Open Fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.map_frame_layout, fragment).commit();
    }
}