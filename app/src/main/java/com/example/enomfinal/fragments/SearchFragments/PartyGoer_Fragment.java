package com.example.enomfinal.fragments.SearchFragments;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.enomfinal.R;
import com.example.enomfinal.activities.PartyGoerProfile;
import com.example.enomfinal.adapters.PartyGoerFragmentAdapters.SuggestedPartyGoerAdapter;
import com.example.enomfinal.api.EnomerRetrofitClient;
import com.example.enomfinal.interfaces.PartyGoerItemClickListener;
import com.example.enomfinal.models.UserResponse;
import com.example.enomfinal.models.Users;
import com.example.enomfinal.storage.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PartyGoer_Fragment extends Fragment {

    private RecyclerView suggestedPartyGoerRV;
    private List<Users> userList;
    private SuggestedPartyGoerAdapter adapter;

    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      view = inflater.inflate(R.layout.party_goer_fragment,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        suggestedPartyGoerRV = view.findViewById(R.id.PartyGoer_fragment_RecyclerView_id);

        final ProgressBar suggestedPartyGoerProgressBar = view.findViewById(R.id.progressbar_partygoer);
        suggestedPartyGoerProgressBar.setIndeterminate(true);
        suggestedPartyGoerProgressBar.setVisibility(View.VISIBLE);

        int userid = SharedPrefManager.getInstance(getActivity()).getUser().getE_id();

        Call<UserResponse> call = EnomerRetrofitClient.getInstance().getApi().getUsers(userid);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                suggestedPartyGoerProgressBar.setVisibility(View.GONE);
                LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(getActivity(),R.anim.layout_anim_falldown);
                suggestedPartyGoerRV.setLayoutAnimation(layoutAnimationController);
                userList = response.body().getUsers();
                adapter = new SuggestedPartyGoerAdapter(getActivity(), userList, new PartyGoerItemClickListener(){
                    @Override
                    public void onPartyGoerClick(Users users, ImageView PartyGoerImageView) {
                        Intent intent = new Intent(getActivity(), PartyGoerProfile.class);
                        intent.putExtra("intent_PartyGoeFName",users.getE_fname());
                        intent.putExtra("intent_PartyGoerLName",users.getE_lname());
                        intent.putExtra("intent_PartyGoerImage",users.getE_photo());
                        intent.putExtra("intent_partygoer_id",users.getE_id());
                        intent.putExtra("intent_partygoertype",users.getE_type());
                        intent.putExtra("intent_partygoergender",users.getE_gender());
//                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(PartyGoer_Fragment.this.getActivity(),PartyGoerImageView,"sharedName");
//                        getActivity().startActivityFromFragment(PartyGoer_Fragment.this,intent,1,options.toBundle());
                        getActivity().startActivityFromFragment(PartyGoer_Fragment.this,intent,1);
                    }
                });
                suggestedPartyGoerRV.setAdapter(adapter);
                suggestedPartyGoerRV.setLayoutManager(new LinearLayoutManager(getActivity()));

            }
            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
//                suggestedbarProgressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
