package com.example.campuseventtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class EventHomeScreen extends AppCompatActivity
{
    String[] fields = {"txtEmail"};
    TextView info;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_home_screen);
        info = findViewById(R.id.txtTestingInfo);

        Bundle email = getIntent().getExtras();


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
                        String eventInfo = retrieval.result();

                        info.setText(eventInfo);
                    }
                }
            });
        }
    }
}