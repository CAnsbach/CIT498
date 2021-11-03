/**
 * Name: Christopher Ansbach
 * Last Updated: 10/1/2021
 * Purpose: Java file to act as the adapter for the RecyclerView to show the events.
 */

package com.example.campuseventtracker;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder>
{
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        //Elements used to display the event's date, name, and location
        public TextView dateTextView;
        public TextView nameTextView;
        public TextView descriptionTextView;

        //Button to select an event to display additional information for
        public Button moreInfoButton;

        public ViewHolder(View itemView)
        {
            super(itemView);

            //Get the elements to be updated
            dateTextView = itemView.findViewById(R.id.txtEventDate);
            nameTextView = itemView.findViewById(R.id.txtEventName);
            descriptionTextView = itemView.findViewById(R.id.txtEventDescription);
            moreInfoButton = itemView.findViewById(R.id.btnEventInfo);
        }
    }

    //List to hold the events to be displayed
    private final List<EventInfo> mEvents;
    private final Context mContext;

    /**
     * Constructor for the RecyclerView
     *
     * @param events List of events to be displayed
     * @param context Context of the activity displaying the RecyclerView
     */
    public EventsAdapter(List<EventInfo> events, Context context)
    {
        mEvents = events;
        mContext = context;
    }

    @Override
    public EventsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //Inflate the layout for the rows
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.events_row_layout, parent, false);

        //Return the viewHolder to display it
        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        //Get the event based on position in the ArrayList
        EventInfo event = mEvents.get(position);

        //Update the TextViews and Button based on the event in the ArrayList
        TextView date = viewHolder.dateTextView;
        date.setText(new StringBuilder().append(event.getDate()).append(" to ").append(event.getEndDate()));
        TextView name = viewHolder.nameTextView;
        name.setText(new StringBuilder().append(event.getName()).append(" - ").append(event.getLocation()));
        TextView description = viewHolder.descriptionTextView;
        description.setText(event.getDescription());

        Button viewMoreInfo = viewHolder.moreInfoButton;

        //When the button is clicked, go to the EventInfoActivity
        viewMoreInfo.setOnClickListener(v -> {
            display(viewHolder.getLayoutPosition());
        });
    }

    @Override
    public int getItemCount() {
        return mEvents.size();
    }

    /**
     * Method used to change to the EventInfoActivity when the More Info Button is pressed.
     *
     *  @param position Position of the chosen event in the ArrayList
     */
    public void display(int position)
    {
        //Get the EventInfo object selected
        EventInfo info = mEvents.get(position);

        //Create a new Intent to change activities
        Intent intent = new Intent(mContext, EventInfoActivity.class);

        //Add the EventInfo object as an extra to be used by the new activity
        intent.putExtra("event", info);

        //Start the new activity
        mContext.startActivity(intent);
    }
}
