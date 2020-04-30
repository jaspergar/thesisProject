package com.example.enomfinal.fragments.EventGoingFragments;

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
import com.example.enomfinal.adapters.EventFragmentAdapters.BarGoingAdapter;
import com.example.enomfinal.adapters.EventFragmentAdapters.PerformerGoingAdapter;
import com.example.enomfinal.api.EnomerRetrofitClient;
import com.example.enomfinal.models.EventModel;
import com.example.enomfinal.models.FinalPerformerSchedNotif;
import com.example.enomfinal.storage.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerformerEventGoingFragment extends Fragment {
    private RecyclerView performereventgoingrv;
    private PerformerGoingAdapter performerGoingAdapter;
    private List<FinalPerformerSchedNotif> eventModelList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.performerevent_going_fragment,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        performereventgoingrv = view.findViewById(R.id.performerevent_going_Rv);

        int enomerid = SharedPrefManager.getInstance(getActivity()).getUser().getE_id();
        Call<List<FinalPerformerSchedNotif>> call = EnomerRetrofitClient.getInstance().getApi().getnotifPerformerSched(enomerid);
        call.enqueue(new Callback<List<FinalPerformerSchedNotif>>() {
            @Override
            public void onResponse(Call<List<FinalPerformerSchedNotif>> call, Response<List<FinalPerformerSchedNotif>> response) {
                eventModelList =response.body();

                performerGoingAdapter = new PerformerGoingAdapter(getActivity(),eventModelList);
                performereventgoingrv.setAdapter(performerGoingAdapter);
                performereventgoingrv.setLayoutManager(new LinearLayoutManager(getActivity()));
            }

            @Override
            public void onFailure(Call<List<FinalPerformerSchedNotif>> call, Throwable t) {

            }
        });



    }
}
