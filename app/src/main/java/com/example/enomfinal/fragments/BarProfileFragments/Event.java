package com.example.enomfinal.fragments.BarProfileFragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.enomfinal.R;
import com.example.enomfinal.activities.BarProfile;
import com.example.enomfinal.activities.EventDetail;
import com.example.enomfinal.adapters.BarProfileAdapters.EventAdapter;
import com.example.enomfinal.fragments.SearchFragments.Bar_Fragment;
import com.example.enomfinal.interfaces.EventItemClickListener;
import com.example.enomfinal.models.EventModel;

import java.util.ArrayList;
import java.util.List;

public class Event extends Fragment {

    RecyclerView recyclerView;
    EventAdapter adapter;
    List<EventModel> eventList;

    View view;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return view = inflater.inflate(R.layout.barprofile_event_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        recyclerView = view.findViewById(R.id.barp_event_rv);

        eventList = new ArrayList<>();
        eventList.add(new EventModel(R.drawable.dj));
        eventList.add(new EventModel(R.drawable.bball));
        eventList.add(new EventModel(R.drawable.jazz));
        eventList.add(new EventModel(R.drawable.ddj));
        eventList.add(new EventModel(R.drawable.duo));
        eventList.add(new EventModel(R.drawable.jazz));
        eventList.add(new EventModel(R.drawable.ddj));

        adapter = new EventAdapter(getActivity(), eventList, new EventItemClickListener() {
            @Override
            public void onPartyGoerClick(EventModel eventModel, ImageView EventImageView) {
                Intent intent = new Intent(getActivity(), EventDetail.class);
                intent.putExtra("intent_eventDetailHostName",eventModel.getEvent_photo());
                intent.putExtra("intent_eventDetailHostImage",eventModel.getEvent_photo());
//                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Bar_Fragment.this.getActivity(),barImageView,"sharedName");
//                        getActivity().startActivityFromFragment(Bar_Fragment.this,intent,1,options.toBundle());
                getActivity().startActivityFromFragment(Event.this,intent,1);
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


    }
}
