/**
 * Name: Christopher Ansbach
 * Last Updated: 10/1/2021
 * Purpose: Java file to create the event info activity by updating the TextViews and loading the Maps fragment.
 */

package com.example.campuseventtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class EventInfoActivity extends AppCompatActivity
{
    //TextView to display the Event's information
    TextView txtName;
    TextView txtLocation;
    TextView txtDescription;
    TextView txtDate;
    TextView txtTime;

    //Event with the information to display
    EventInfo event;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_info);

        //Update the ActionBar to an appropriate title
        getSupportActionBar().setTitle("CET - Event Information");

        //Get the event that was chosen by the user
        event = (EventInfo) getIntent().getSerializableExtra("event");

        //Update the TextViews as necessary
        txtName = findViewById(R.id.txtDispEventName);
        txtLocation = findViewById(R.id.txtDispEventLocation);
        txtDescription = findViewById(R.id.txtDispEventDescription);
        txtDescription.setMovementMethod(new ScrollingMovementMethod());
        txtDate = findViewById(R.id.txtDispEventDate);
        txtTime = findViewById(R.id.txtDispEventTime);

        //Check if the information is not null. If it is not, update the TextViews
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

        //Initialize the Google Maps fragment
        Fragment fragment = new MapsFragment();

        //Create a bundle to send the event's info to the fragment
        Bundle eventInfo = new Bundle();

        //Put the EventInfo object into the Bundle
        eventInfo.putSerializable("event", event);

        //Get the Bundle as an argument to be used by the fragment
        fragment.setArguments(eventInfo);

        //Open the fragment to display the Google Maps instance
        getSupportFragmentManager().beginTransaction().replace(R.id.map_frame_layout, fragment).commit();
    }
}