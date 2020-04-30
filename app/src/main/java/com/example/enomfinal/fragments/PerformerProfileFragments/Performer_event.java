package com.example.enomfinal.fragments.PerformerProfileFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.enomfinal.R;
import com.example.enomfinal.adapters.BarProfileAdapters.EventAdapter;
import com.example.enomfinal.adapters.performerprofileAdpters.PerformerProfile_EventAdapter;
import com.example.enomfinal.models.EventModel;

import java.util.ArrayList;
import java.util.List;

public class Performer_event extends Fragment {
    RecyclerView recyclerView;
    PerformerProfile_EventAdapter adapter;
    List<EventModel> eventList;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return view = inflater.inflate(R.layout.performerprofile_schedule, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.eventprofile_event_rv);

        eventList = new ArrayList();
        eventList.add(new EventModel(R.drawable.dj));
        eventList.add(new EventModel(R.drawable.bball));
        eventList.add(new EventModel(R.drawable.jazz));
        eventList.add(new EventModel(R.drawable.ddj));
        eventList.add(new EventModel(R.drawable.duo));
        eventList.add(new EventModel(R.drawable.jazz));
        eventList.add(new EventModel(R.drawable.ddj));

        adapter = new PerformerProfile_EventAdapter(getActivity(),eventList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
