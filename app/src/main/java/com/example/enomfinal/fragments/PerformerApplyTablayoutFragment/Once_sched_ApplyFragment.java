package com.example.enomfinal.fragments.PerformerApplyTablayoutFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.enomfinal.R;
import com.example.enomfinal.activities.ViewOnceSchedDetail;
import com.example.enomfinal.adapters.PerformerActivityAdapters.GigsAdapter;
import com.example.enomfinal.api.PerformerRetrofitClient;
import com.example.enomfinal.interfaces.GigsItemClickListener;
import com.example.enomfinal.models.BarGigs;
import com.example.enomfinal.storage.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Once_sched_ApplyFragment extends Fragment {

    private RecyclerView onceRv;
    GigsAdapter gigsAdapter;
    private List<BarGigs> barGigsList;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.once_performance_pact_applyfragment,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

                onceRv = view.findViewById(R.id.onceRv);
       int performer_id =  SharedPrefManager.getInstance(getActivity()).getUser().getE_id();
                Call<List<BarGigs>> call = PerformerRetrofitClient.getInstance().getApi().getOnceSched(performer_id);
                call.enqueue(new Callback<List<BarGigs>>() {
                    @Override
                    public void onResponse(Call<List<BarGigs>> call, Response<List<BarGigs>> response) {
                        barGigsList = response.body();
                        gigsAdapter = new GigsAdapter(getActivity(), barGigsList, new GigsItemClickListener() {
                            @Override
                            public void onBarClick(BarGigs barGigs, ImageView barImageView) {
                                Intent intent = new Intent(getActivity(), ViewOnceSchedDetail.class);
                                intent.putExtra("barid",barGigs.getBar_id());
                                intent.putExtra("schedDateId",barGigs.getSched_date_id());
                                intent.putExtra("barname",barGigs.getBar_name());
                                intent.putExtra("performancetype",barGigs.getPerformance_type());
                                intent.putExtra("date",barGigs.getOnce_schedule());
                                intent.putExtra("stime",barGigs.getStart_time());
                                intent.putExtra("etime",barGigs.getEnd_time());
                                intent.putExtra("bphoto",barGigs.getBar_photo());
                                intent.putExtra("description",barGigs.getSched_description());
                                intent.putExtra("status",barGigs.getStatus());
                                intent.putExtra("performerid",barGigs.getPerformer_id());
                                intent.putExtra("theUserWhoNotify",barGigs.getEnomer_id());
                                startActivity(intent);
                            }
                        });
                        onceRv.setAdapter(gigsAdapter);
                        onceRv.setLayoutManager(new LinearLayoutManager(getActivity()));
                    }

                    @Override
                    public void onFailure(Call<List<BarGigs>> call, Throwable t) {

                    }
                });


    }
}
