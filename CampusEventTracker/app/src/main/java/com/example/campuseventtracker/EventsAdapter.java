package com.example.campuseventtracker;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder>
{
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public TextView descriptionTextView;
        public Button moreInfoButton;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = itemView.findViewById(R.id.txtEventName);
            descriptionTextView = itemView.findViewById(R.id.txtEventDescription);
            moreInfoButton = itemView.findViewById(R.id.btnEventInfo);
        }
    }
    private List<EventInfo> mEvents;
    private Context mContext;

    public EventsAdapter(List<EventInfo> events, Context context)
    {
        mEvents = events;
        mContext = context;
    }

    @Override
    public EventsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.events_row_layout, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        // Get the event based on position
        EventInfo event = mEvents.get(position);

        // Set the text shown for the list item to the name of the event
        TextView name = viewHolder.nameTextView;
        name.setText(new StringBuilder().append(event.getName()).append(" - ").append(event.getLocation()).toString());
        TextView description = viewHolder.descriptionTextView;       //Set the message button as the button to open the contacts information
        description.setText(event.getDescription());

        Button viewMoreInfo = viewHolder.moreInfoButton;

        //Lambda expression would not work: "Lambda expressions are not supported at language level 7."
        viewMoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display(viewHolder.getLayoutPosition());        //Display the contacts information by passing the event's position in the list
            }
        });
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mEvents.size();
    }

    //Add an item to the list
    public void add(int position, EventInfo newEvent)
    {
        mEvents.add(position, newEvent);
        notifyItemInserted(position);
    }

    //Display the information of the contact at the given position
    public void display(int position)
    {
        EventInfo info = mEvents.get(position);                          //Get the selected contact
        Intent intent = new Intent(mContext, EventInfoActivity.class);     //Create a new intent to switch to the DisplayActivity class from the current class
        intent.putExtra("event", info);                          //Give the intent the contact to be passed to the DisplayActivity class.
        mContext.startActivity(intent);                                  //State the new intent from the current context
    }
}
