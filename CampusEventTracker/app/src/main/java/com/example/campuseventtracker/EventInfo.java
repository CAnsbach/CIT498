package com.example.campuseventtracker;

import java.io.Serializable;
import java.util.ArrayList;

public class EventInfo implements Serializable
{
    //Attributes of a Contact
    private String mName;
    private String mDescription;
    private String mLocation;
    private String mDate;
    private String mTime;
    private String mLatitude;
    private String mLongitude;

    private static int lastEventId = 0;

    //Constructor
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

    public EventInfo(String name, String description)
    {
        mName = name;
        mDescription = description;
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

    //Create the default ArrayList of "empty" contacts
    public static ArrayList<EventInfo> createEventsList()
    {
        ArrayList<EventInfo> events = new ArrayList<>();

        return events;
    }
}
