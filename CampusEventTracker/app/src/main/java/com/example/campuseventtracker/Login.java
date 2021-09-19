package com.example.campuseventtracker;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Login extends Thread
{
    String loginResult = "None";
    String[] fields;
    String[] data;
    OutputStream out;
    HttpURLConnection connection;

    public Login (String[] fields, String[] data)
    {
        this.fields = fields;
        this.data = data;
    }

    public void run()
    {
        try
        {
            URL url = new URL("https://www.citcapstones.com/CIT498/php/userloginapp.php");

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            out = connection.getOutputStream();

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8));
            StringBuilder input_data = new StringBuilder();

            for(int i = 0; i < fields.length; i++)
            {
                input_data.append(URLEncoder.encode(fields[i], "UTF-8")).append("=").append(URLEncoder.encode(data[i], "UTF-8")).append("&");
            }

            Log.d("URL", input_data.toString());

            writer.write(input_data.toString());
            writer.flush();
            writer.close();

            out.close();

            InputStream in = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.ISO_8859_1));
            StringBuilder result = new StringBuilder();

            String result_line;

            while((result_line = reader.readLine()) != null)
            {
                result.append(result_line);
            }

            reader.close();
            in.close();
            connection.disconnect();
            setResult(result.toString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public boolean login()
    {
        Login.this.start();
        return true;
    }

    public boolean completed()
    {
        while (true)
        {
            if (!this.isAlive())
            {
                return true;
            }
        }
    }

    public void setResult(String result)
    {
        loginResult = result;
    }

    public String result()
    {
        return this.loginResult;
    }
}
