package com.example.campuseventtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class EventHomeScreen extends AppCompatActivity
{
    String[] fields = {"txtEmail"};
    ArrayList<EventInfo> events;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_home_screen);

        Bundle email = getIntent().getExtras();
        RecyclerView rvEvents = findViewById(R.id.rvEvents);

        if (email != null)
        {

            String[] data = {email.getString("Email")};

            Handler handler = new Handler();
            handler.post(() -> {
                RetrieveEventJSONData retrieval = new RetrieveEventJSONData(fields, data);

                if(retrieval.retrieve())
                {
                    if(retrieval.completed())
                    {
                        try
                        {

                            String eventInfo = retrieval.result();

                            events = EventInfo.createEventsList();

                            JSONObject eventInfoJSON = new JSONObject(eventInfo);
                            JSONArray eventInfoArray = eventInfoJSON.getJSONArray("Events");


                            for (int i = 0; i < eventInfoArray.length(); i++)
                            {
                                JSONObject event = eventInfoArray.getJSONObject(i);
                                String name = event.getString("Event_Name");
                                String description = event.getString("Event_Description");
                                String location = event.getString("Location_Name");
                                String date = event.getString("Event_Date");
                                String time = event.getString("Event_Time");
                                String latitude = event.getString("Location_Lat");
                                String longitude = event.getString("Location_Long");
                                events.add(events.size(), new EventInfo(name, description, location, date, time, latitude, longitude));
                            }

                            //Create an adapter for the list
                            final EventsAdapter adapter = new EventsAdapter(events, this);

                            // Attach the adapter to the recyclerview to populate items
                            rvEvents.setAdapter(adapter);

                            // Set layout manager to position the items
                            rvEvents.setLayoutManager(new LinearLayoutManager(this));

                        }
                        catch (JSONException e)
                        {
                            Toast.makeText(getApplicationContext(), "An Error has occurred with JSON", Toast.LENGTH_SHORT).show();
                        }
                        catch (Exception e)
                        {
                            Toast.makeText(getApplicationContext(), "An Error has occurred", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }
    }
}