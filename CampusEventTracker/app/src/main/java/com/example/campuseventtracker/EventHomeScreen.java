package com.example.campuseventtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class EventHomeScreen extends AppCompatActivity
{
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_home_screen);

        Bundle email = getIntent().getExtras();

        if (email != null)
        {
            this.email = email.getString("Email");

            Toast.makeText(getApplicationContext(), this.email, Toast.LENGTH_SHORT).show();
        }
    }
}