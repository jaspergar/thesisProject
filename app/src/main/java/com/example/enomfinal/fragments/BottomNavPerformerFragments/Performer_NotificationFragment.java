package com.example.enomfinal.fragments.BottomNavPerformerFragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.enomfinal.R;
import com.example.enomfinal.activities.PartyGoerProfile;
import com.example.enomfinal.activities.ViewOnceSchedDetail;
import com.example.enomfinal.adapters.NotificationAdapter;
import com.example.enomfinal.api.EnomerRetrofitClient;
import com.example.enomfinal.api.PerformerRetrofitClient;
import com.example.enomfinal.interfaces.NotificationItemClickListener;
import com.example.enomfinal.models.DefaultResponse;
import com.example.enomfinal.models.Notification;
import com.example.enomfinal.storage.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Performer_NotificationFragment extends Fragment {

    private RecyclerView notifRv;
    private NotificationAdapter notificationAdapter;
    private List<Notification> notificationList;
    private int TheUser,thefollower;
//    private ConstraintLayout constraintLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.performer_act_notification_fragment,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        notifRv = view.findViewById(R.id.performeract_notification_rv);
//        constraintLayout = view.findViewById(R.id.notif_background);
        int enomer_id = SharedPrefManager.getInstance(getActivity()).getUser().getE_id();


        Call<List<Notification>> call = EnomerRetrofitClient.getInstance().getApi().retrievenotifFollow(enomer_id);
        call.enqueue(new Callback<List<Notification>>() {
            @Override
            public void onResponse(Call<List<Notification>> call, Response<List<Notification>> response) {
                notificationList = response.body();

                notificationAdapter = new NotificationAdapter(getActivity(),notificationList, new NotificationItemClickListener() {
                    @Override
                    public void onNotificationClick(Notification notification, ImageView notificationImageView) {
                        TheUser = SharedPrefManager.getInstance(getActivity()).getUser().getE_id();
                        thefollower = notification.getEnomer_id();
                        final String userType = SharedPrefManager.getInstance(getActivity()).getUser().getE_type();
                        Call<DefaultResponse> call1 = EnomerRetrofitClient.getInstance().getApi().notifUpdateStatus(TheUser,thefollower);
                        call1.enqueue(new Callback<DefaultResponse>() {
                            @Override
                            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                                if(response.code() == 201) {
//                                    constraintLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                                    Intent intent = new Intent(getActivity(), PartyGoerProfile.class);
                                    intent.putExtra("intent_partygoer_id", thefollower);
                                    intent.putExtra("intent_type",userType);
                                    startActivity(intent);
                                }else if(response.code() == 422){
                                    Intent intent = new Intent(getActivity(), PartyGoerProfile.class);
                                    intent.putExtra("intent_partygoer_id", thefollower);
                                    intent.putExtra("intent_type",userType);
                                    startActivity(intent);
                                }
                            }

                            @Override
                            public void onFailure(Call<DefaultResponse> call, Throwable t) {

                            }
                        });


                    }
                });
                notifRv.setAdapter(notificationAdapter);
                notifRv.setLayoutManager(new LinearLayoutManager(getActivity()));
            }

            @Override
            public void onFailure(Call<List<Notification>> call, Throwable t) {

            }
        });


    }
}
