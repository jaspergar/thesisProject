package com.example.enomfinal.fragments.BottomNavPerformerFragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.enomfinal.R;
import com.example.enomfinal.activities.ViewOnceSchedDetail;
import com.example.enomfinal.activities.WelcomeScreen;
import com.example.enomfinal.adapters.MyInvitesAdapter;
import com.example.enomfinal.api.PerformerRetrofitClient;
import com.example.enomfinal.interfaces.InviteItemClickListener;
import com.example.enomfinal.models.MyInvites;
import com.example.enomfinal.storage.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Performer_MyInvitesFragment extends Fragment{
     private List<MyInvites> myInvites;
     private MyInvitesAdapter myInvitesAdapter;
     private InviteItemClickListener inviteItemClickListener;
     private RecyclerView invitesRv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.performer_myinvitesfragment,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        invitesRv = view.findViewById(R.id.myinvitesRV_id);

        int TheUser = SharedPrefManager.getInstance(getActivity()).getUser().getE_id();
        Call<List<MyInvites>> call = PerformerRetrofitClient.getInstance().getApi().getInvitePerformer(TheUser);
        call.enqueue(new Callback<List<MyInvites>>() {
            @Override
            public void onResponse(Call<List<MyInvites>> call, Response<List<MyInvites>> response) {
                myInvites = response.body();

                myInvitesAdapter = new MyInvitesAdapter(getContext(), myInvites, new InviteItemClickListener() {
                    @Override
                    public void onBarClick(MyInvites myInvites, ImageView barImageView) {
                        Intent intent = new Intent(getActivity(), ViewOnceSchedDetail.class);
                        intent.putExtra("barid",myInvites.getBar_id());
                        intent.putExtra("schedDateId",myInvites.getSched_date_id());
                        intent.putExtra("barname",myInvites.getBar_name());
                        intent.putExtra("performancetype",myInvites.getPerformance_type());
                        intent.putExtra("date",myInvites.getOnce_schedule());
                        intent.putExtra("stime",myInvites.getStart_time());
                        intent.putExtra("etime",myInvites.getEnd_time());
                        intent.putExtra("bphoto",myInvites.getBar_photo());
                        intent.putExtra("description",myInvites.getSched_description());
                        intent.putExtra("status",myInvites.getStatus());
                        startActivity(intent);
                    }
                });
                invitesRv.setAdapter(myInvitesAdapter);
                invitesRv.setLayoutManager(new LinearLayoutManager(getActivity()));

            }

            @Override
            public void onFailure(Call<List<MyInvites>> call, Throwable t) {

            }
        });

    }


}
