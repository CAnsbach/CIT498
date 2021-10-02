/**
 * Name: Christopher Ansbach
 * Last Updated: 10/1/2021
 * Purpose: Java file to define the EventInfo object to be used to store event data from the JSON file.
 */

package com.example.campuseventtracker;

import java.io.Serializable;
import java.util.ArrayList;

public class EventInfo implements Serializable
{
    //String to hold event information
    private final String mName;
    private final String mDescription;
    private final String mLocation;
    private final String mDate;
    private final String mTime;
    private final String mLatitude;
    private final String mLongitude;

    //Constructor for the EventInfo object
    public EventInfo(String name, String description, String location, String date, String time, String latitude, String longitude)
    {
        mName = name;
        mDescription = description;
        mLocation = location;
        mDate = date;
        mTime = time;
        mLatitude = latitude;
        mLongitude = longitude;
    }

    //Getters
    public String getName()
    {
        return mName;
    }

    public String getDescription()
    {
        return mDescription;
    }

    public String getLocation()
    {
        return mLocation;
    }

    public String getDate()
    {
        return mDate;
    }

    public String getTime()
    {
        return mTime;
    }

    public String getLatitude()
    {
        return mLatitude;
    }

    public String getLongitude()
    {
        return mLongitude;
    }

    //Create the ArrayList of events to store the events at the school
    public static ArrayList<EventInfo> createEventsList()
    {
        return new ArrayList<>();
    }
}
