/**
 * Name: Christopher Ansbach
 * Last Updated: 10/1/2021
 * Purpose: Java file to connect to the website and retrieve the events from the user's school.
 */

package com.example.campuseventtracker;

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

public class RetrieveEventJSONData extends Thread
{
    String retrievalResult = "None";
    String[] fields;                //Fields for the website input
    String[] data;                  //Data to be used for the respective field.
    OutputStream out;               //Output stream to provide information to the website.
    HttpURLConnection connection;   //Connection to connect to the website.
    InputStream in;                 //Input stream to retrieve result from the website.

    /**
     * Constructor for the RetrieveEventJSONData class
     *
     * @param fields String array to hold the field information for the input.
     * @param data String array to hold the data to be sent with the related field.
     */
    public RetrieveEventJSONData(String[] fields, String[] data)
    {
        this.fields = fields;
        this.data = data;
    }

    /**
     * Run method of the thread, used to establish a connection to the website, post the user's input, and retrieve the login result.
     */
    public void run()
    {
        try
        {
            //URL to login php file on website
            URL url = new URL("https://www.citcapstones.com/CIT498/php/schoolevents.php");

            //Establish a connection with the website using the post method to provide information.
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            out = connection.getOutputStream();

            //Create a writer to provide the website with the email and password of the user.
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8));

            //Create a string builder to construct the string of input to provide to the website
            StringBuilder inputDataString = new StringBuilder();

            //Build the input string
            for(int i = 0; i < fields.length; i++)
            {
                inputDataString.append(URLEncoder.encode(fields[i], "UTF-8")).append("=").append(URLEncoder.encode(data[i], "UTF-8")).append("&");
            }

            //Write the input string to the website and close the writer.
            writer.write(inputDataString.toString());
            writer.flush();
            writer.close();

            //Close the output stream
            out.close();

            //Get the input stream to retrieve the login feedback
            in = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.ISO_8859_1));

            String eventInfo = "";

            String info = "";

            //While there are results to receive, append them to the results.
            while((info = reader.readLine()) != null)
            {
                eventInfo = eventInfo + info;
            }

            //Close the reader, input stream, and connection, and set the result.
            reader.close();
            in.close();
            connection.disconnect();
            setResult(eventInfo);
        }
        catch (Exception e)
        {
            setResult("An error has occurred while fetching event data.");
        }
    }

    /**
     * Method used to start the retrieval process.
     *
     * @return Boolean stating whether the process has started or not.
     */
    public boolean retrieve()
    {
        RetrieveEventJSONData.this.start();
        return true;
    }

    /**
     * Method used to determine whether the process has ended.
     *
     * @return Boolean stating whether the process has completed or not.
     */
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

    /**
     * Method used to set the value of the retrieval result.
     *
     * @param result Value of the results.
     */
    public void setResult(String result)
    {
        retrievalResult = result;
    }

    /**
     * Method used to return the result of the retrieval.
     *
     * @return Result of the login.
     */
    public String result()
    {
        return this.retrievalResult;
    }
}
