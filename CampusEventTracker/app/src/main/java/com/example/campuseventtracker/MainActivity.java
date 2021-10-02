/**
 * Name: Christopher Ansbach
 * Last Updated: 10/1/2021
 * Purpose: Java file to load the login activity that is shown when the app is launched.
 */

package com.example.campuseventtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    //Elements to be used for logging in
    EditText inpEmail, inpPassword;
    Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Update the ActionBar to an appropriate title
        getSupportActionBar().setTitle("CET - User Login");

        //Get the elements
        inpEmail =  findViewById(R.id.txtEmailInput);
        inpPassword = findViewById(R.id.txtPasswordInput);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);



        btnLogin.setOnClickListener(view -> {

            //Get the email and password entered by the user
            String  email = inpEmail.getText().toString(),
                    password = inpPassword.getText().toString();

            //Array to be passed to the Login class to log the user in if valid credentials were given
            String[] fields = {"txtEmail", "txtPassword"};
            String[] data = {email, password};

            //Handler to handle the Login process
            Handler handler = new Handler();
            handler.post(() -> {

                //Pass the information to be used for logging in to a new Login class instance
                Login login = new Login(fields, data);

                //If the login process has started, check if it was completed
                if(login.login())
                {
                    //If the login process completed, get the result and display it to the user
                    if(login.completed())
                    {
                        //Get the result from the process
                        String loginResult = login.result();

                        //Make a toast to notify the user of the result of the login
                        Toast.makeText(getApplicationContext(), loginResult, Toast.LENGTH_SHORT).show();

                        //If the login was successful, change activities to the display the events for the user's school
                        if (login.result().equals("Successfully Logged In"))
                        {
                            //Create a new intent
                            Intent homeScreen = new Intent(MainActivity.this, EventHomeScreen.class);

                            //Provide the user's email to be used by the next activity
                            homeScreen.putExtra("Email", data[0]);

                            //Start the new activity
                            startActivity(homeScreen);
                        }
                    }
                }
            });
        });

        //Set a listener to open the registration webpage when the Register button is pressed
        btnRegister.setOnClickListener(view -> {
            //Create a new intention to open a webpage with the user's default browser
            Intent registrationPage = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.citcapstones.com/CIT498/php/registeruser.php"));

            //Open the webpage
            startActivity(registrationPage);
        });
    }
}