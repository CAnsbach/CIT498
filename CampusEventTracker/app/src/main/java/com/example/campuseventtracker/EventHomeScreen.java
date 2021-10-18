/**
 * Name: Christopher Ansbach
 * Last Updated: 10/1/2021
 * Purpose: Java file used to display the RecyclerView with the events from the school.
 */

package com.example.campuseventtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class EventHomeScreen extends AppCompatActivity
{
    String[] fields = {"txtEmail"}; //Fields to be sent to the website
    ArrayList<EventInfo> events;    //Array list of EventInfo object to hold data about the school's events.

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_home_screen);

        //Update the ActionBar to an appropriate title
        getSupportActionBar().setTitle("CET - School Events");

        //Create a bundle to retrieve the email fo the user to sent to the website
        Bundle email = getIntent().getExtras();

        //Instantiate the RecyclerView
        RecyclerView rvEvents = findViewById(R.id.rvEvents);

        //If there is an email to send, send it to the website to get the events of the school related to the email.
        if (email != null)
        {
            //Get the email of the user
            String[] data = {email.getString("Email")};

            //Create a handler to handle the retrieval of the JSON object
            Handler handler = new Handler();
            handler.post(() ->
            {
                RetrieveEventJSONData retrieval = new RetrieveEventJSONData(fields, data);

                //If the retrieval is active, check if it is complete
                if(retrieval.retrieve())
                {
                    //If the retrieval is complete, try to fill the RecyclerView
                    if(retrieval.completed())
                    {
                        try
                        {
                            //String to hold the JSON string from the website
                            String eventInfo = retrieval.result();

                            //Create the list to be used to store the EventInfo objects
                            events = EventInfo.createEventsList();

                            //Create a JSONArray from the JSON string from the website
                            JSONObject eventInfoJSON = new JSONObject(eventInfo);
                            JSONArray eventInfoArray = eventInfoJSON.getJSONArray("Events");

                            //For each event in the JSONArray, create a new EventInfo object and add it to the ArrayList.
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
                                String endDate = event.getString("Event_EndDate");
                                String endTime = event.getString("Event_EndTime");
                                events.add(events.size(), new EventInfo(name, description, location, date, time, latitude, longitude, endDate, endTime));
                            }

                            //Create an adapter for the list
                            final EventsAdapter adapter = new EventsAdapter(events, this);

                            //Set up the RecyclerView to display the EventInfo objects
                            rvEvents.setAdapter(adapter);
                            rvEvents.setLayoutManager(new LinearLayoutManager(this));

                        }
                        //Catch a JSON or general exception and notify the user
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