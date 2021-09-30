package com.example.campuseventtracker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EventInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventInfoFragment extends Fragment
{

    private static final EventInfo event = new EventInfo("Placeholder", "Placeholder");
    TextView txtName;
    TextView txtLocation;
    TextView txtDescription;
    TextView txtDate;
    TextView txtTime;

    private EventInfo info;

    public EventInfoFragment()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param event EventInfo object that contains event information.
     * @return A new instance of fragment EventInfoFragment.
     */
    public static EventInfoFragment newInstance(EventInfo event)
    {
        EventInfoFragment fragment = new EventInfoFragment();
        Bundle args = new Bundle();
        args.putSerializable("event", event);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            info = (EventInfo) getArguments().getSerializable("event");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View fragmentView = inflater.inflate(R.layout.fragment_event_info, container, false);
        // Inflate the layout for this fragment

        return fragmentView;
    }
}