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
    EditText inpEmail, inpPassword;
    Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("CET - User Login");

        inpEmail =  findViewById(R.id.txtEmailInput);
        inpPassword = findViewById(R.id.txtPasswordInput);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);



        btnLogin.setOnClickListener(view -> {

            String  email = inpEmail.getText().toString(),
                    password = inpPassword.getText().toString();

            String[] fields = {"txtEmail", "txtPassword"};
            String[] data = {email, password};

            Handler handler = new Handler();
            handler.post(() -> {
                Login login = new Login(fields, data);

                if(login.login())
                {
                    if(login.completed())
                    {
                        String loginResult = login.result();
                        Toast.makeText(getApplicationContext(), loginResult, Toast.LENGTH_SHORT).show();

                        if (login.result().equals("Successfully Logged In"))
                        {
                            Intent homeScreen = new Intent(MainActivity.this, EventHomeScreen.class);
                            homeScreen.putExtra("Email", data[0]);
                            startActivity(homeScreen);
                        }
                    }
                }
            });
        });

        btnRegister.setOnClickListener(view -> {
            Intent registrationPage = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.citcapstones.com/CIT498/php/registeruser.php"));
            startActivity(registrationPage);
        });
    }
}