package com.example.enomfinal.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.enomfinal.R;
import com.example.enomfinal.adapters.PartyGoerProfileAdapters.PartyGoer_BarFollowedAdapter;
import com.example.enomfinal.adapters.PartyGoerProfileAdapters.PartyGoer_PartyGoerFollowedAdapter;
import com.example.enomfinal.adapters.PartyGoerProfileAdapters.PartyGoer_PerformerFollowedAdaper;
import com.example.enomfinal.api.EnomerRetrofitClient;
import com.example.enomfinal.api.StaticIP;
import com.example.enomfinal.models.BARSFINAL;
import com.example.enomfinal.models.Bars;
import com.example.enomfinal.models.DefaultResponse;
import com.example.enomfinal.models.PerfomerProfile_JoinedTable;
import com.example.enomfinal.models.Users;
import com.example.enomfinal.storage.SharedPrefManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PartyGoerProfile extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView barfollowedRv,performerfollowedRv,partygoerfollowedRv;
    private PartyGoer_BarFollowedAdapter partyGoer_barFollowedAdapter;
    private PartyGoer_PerformerFollowedAdaper partyGoer_performerFollowedAdaper;
    private PartyGoer_PartyGoerFollowedAdapter partyGoer_partyGoerFollowedAdapter;
    private List<Bars> barlist;
    private List<PerfomerProfile_JoinedTable> perfomerProfile_joinedTables;
    private List<Users> usersList;
    private Users users;

    String userType;

    int thefollowed,theuser;
    String thefollower;
    String followedtype;
    Button follow,unfollow;

    TextView fname,lname,gen;
    ImageView pp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_goer_profile);


        barfollowedRv = findViewById(R.id.partyGoerProfile_BarfollowedRV);
        performerfollowedRv = findViewById(R.id.partyGoerProfile_performerfollowedRV);
        partygoerfollowedRv = findViewById(R.id.partyGoerProfile_partyGoerfollowedRV);

        follow = findViewById(R.id.partyGoerProfile_btnFollow);
        unfollow = findViewById(R.id.partyGoerProfile_btnunFollow);

         fname = findViewById(R.id.partyGoerProfile_fname);
        lname = findViewById(R.id.partyGoerProfile_lname);
        gen = findViewById(R.id.partyGoerProfile_Gender);
        pp = findViewById(R.id.partyGoerProfile_photo);






        follow.setOnClickListener(this);
        unfollow.setOnClickListener(this);

         thefollowed = getIntent().getExtras().getInt("intent_partygoer_id");
         followedtype = getIntent().getExtras().getString("intent_partygoertype");
        theuser = SharedPrefManager.getInstance(PartyGoerProfile.this).getUser().getE_id();
        thefollower = SharedPrefManager.getInstance(PartyGoerProfile.this).getUser().getE_type();

//        String type = "Performer";
//        userType = getIntent().getExtras().getString("intent_type");
//
//        if(userType.equals(type) && !userType.isEmpty()){
//            follow.setVisibility(View.GONE);
//            unfollow.setVisibility(View.GONE);
//        }else if(userType.isEmpty()){
//            follow.setVisibility(View.VISIBLE);
//            unfollow.setVisibility(View.VISIBLE);
//        }

//get partygoer api and set on layout
        Call<Users> call = EnomerRetrofitClient.getInstance().getApi().getPartygoerWithId(thefollowed);
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                users = response.body();
                String f_name = users.getE_fname();
                String l_name = users.getE_lname();
                String gender = users.getE_gender();
                String image = users.getE_photo();

                fname.setText(f_name);
                lname.setText(l_name);
                gen.setText(gender);

                if(image != null){
                    Picasso.get().load(StaticIP.getWifiIP() +image).placeholder(R.drawable.imgplaceholder).into(pp);
                }else{

                    Picasso.get().load(R.drawable.imgplaceholder).into(pp);
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {

            }
        });




//bar followed
        barlist = new ArrayList<>();
        barlist.add(new Bars(R.drawable.dj,"The Bar"));
        barlist.add(new Bars(R.drawable.bball,"Mr.j"));
        barlist.add(new Bars(R.drawable.jazz,"Jazz Music"));
        barlist.add(new Bars(R.drawable.ddj,"We the Best"));
        barlist.add(new Bars(R.drawable.duo,"Music"));
        barlist.add(new Bars(R.drawable.jazz,"Live Super Club"));
        barlist.add(new Bars(R.drawable.ddj,"Harrison"));


        partyGoer_barFollowedAdapter = new PartyGoer_BarFollowedAdapter(this,barlist);
        barfollowedRv.setAdapter(partyGoer_barFollowedAdapter);
        barfollowedRv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

//performer followed
        perfomerProfile_joinedTables = new ArrayList<>();
        perfomerProfile_joinedTables.add(new PerfomerProfile_JoinedTable(R.drawable.dj,"The Band"));
        perfomerProfile_joinedTables.add(new PerfomerProfile_JoinedTable(R.drawable.bball,"We the Gwapo"));
        perfomerProfile_joinedTables.add(new PerfomerProfile_JoinedTable(R.drawable.jazz,"Jazz Music"));
        perfomerProfile_joinedTables.add(new PerfomerProfile_JoinedTable(R.drawable.ddj,"We the Best"));
        perfomerProfile_joinedTables.add(new PerfomerProfile_JoinedTable(R.drawable.duo,"Music"));
        perfomerProfile_joinedTables.add(new PerfomerProfile_JoinedTable(R.drawable.jazz,"Moira"));
        perfomerProfile_joinedTables.add(new PerfomerProfile_JoinedTable(R.drawable.ddj,"Harrison"));

        partyGoer_performerFollowedAdaper = new PartyGoer_PerformerFollowedAdaper(this,perfomerProfile_joinedTables);
        performerfollowedRv.setAdapter(partyGoer_performerFollowedAdaper);
        performerfollowedRv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

  //party goer followed


        usersList = new ArrayList<>();
        usersList.add(new Users(R.drawable.dj,"Jasper","Gargar"));
        usersList.add(new Users(R.drawable.bball,"Geoo","Lo"));
        usersList.add(new Users(R.drawable.jazz,"Mia","Malkova"));
        usersList.add(new Users(R.drawable.ddj,"Tidert","Durano"));
        usersList.add(new Users(R.drawable.duo,"Music","Flores"));
        usersList.add(new Users(R.drawable.jazz,"Moira","Delatore"));
        usersList.add(new Users(R.drawable.ddj,"Harrison","Park"));

        partyGoer_partyGoerFollowedAdapter = new PartyGoer_PartyGoerFollowedAdapter(this,usersList);
        partygoerfollowedRv.setAdapter(partyGoer_partyGoerFollowedAdapter);
        partygoerfollowedRv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));


        isFollowed(thefollowed,theuser);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.partyGoerProfile_btnFollow:
                Call<DefaultResponse> call = EnomerRetrofitClient.getInstance().getApi().follow(thefollowed,followedtype,thefollower,theuser,"ns","followed you","follow",thefollower,followedtype);
                call.enqueue(new Callback<DefaultResponse>() {
                    @Override
                    public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                        if(response.code() == 201){
                            follow.setVisibility(View.GONE);
                            unfollow.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onFailure(Call<DefaultResponse> call, Throwable t) {

                    }
                });
                break;
            case R.id.partyGoerProfile_btnunFollow:
                Call<DefaultResponse> call2 = EnomerRetrofitClient.getInstance().getApi().unfollow(thefollowed,theuser);
                call2.enqueue(new Callback<DefaultResponse>() {
                    @Override
                    public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                        if(response.code() == 201){
                            follow.setVisibility(View.VISIBLE);
                            unfollow.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<DefaultResponse> call, Throwable t) {

                    }
                });
                break;
        }
    }

    public void isFollowed(int thefollowed,int TheUser){
        Call<DefaultResponse> call2 = EnomerRetrofitClient.getInstance().getApi().isFollowed(thefollowed,TheUser);
        call2.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {


                if(response.code() == 201){
                    follow.setVisibility(View.GONE);
                    unfollow.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {

            }
        });
    }
}
