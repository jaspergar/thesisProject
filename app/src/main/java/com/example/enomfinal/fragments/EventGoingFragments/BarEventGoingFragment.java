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
import com.example.enomfinal.api.EnomerRetrofitClient;
import com.example.enomfinal.models.EventModel;
import com.example.enomfinal.models.FinalBarSchedNotif;
import com.example.enomfinal.storage.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BarEventGoingFragment extends Fragment {

    private RecyclerView bareventgoingrv;
    private BarGoingAdapter barGoingAdapter;
    private List<FinalBarSchedNotif> eventModelList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.barevent_going_fragment,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bareventgoingrv = view.findViewById(R.id.barevent_going_Rv);

       int theuser = SharedPrefManager.getInstance(getActivity()).getUser().getE_id();
        Call<List<FinalBarSchedNotif>> call = EnomerRetrofitClient.getInstance().getApi().getnotifBarrSched(theuser);
       call.enqueue(new Callback<List<FinalBarSchedNotif>>() {
           @Override
           public void onResponse(Call<List<FinalBarSchedNotif>> call, Response<List<FinalBarSchedNotif>> response) {
               eventModelList = response.body();
               barGoingAdapter = new BarGoingAdapter(getActivity(),eventModelList);
               bareventgoingrv.setAdapter(barGoingAdapter);
               bareventgoingrv.setLayoutManager(new LinearLayoutManager(getActivity()));
           }

           @Override
           public void onFailure(Call<List<FinalBarSchedNotif>> call, Throwable t) {

           }
       });


    }
}
