package com.example.enomfinal.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.enomfinal.R;
import com.example.enomfinal.activities.PerformerLayout.PerformerActivity;
import com.example.enomfinal.adapters.ViewPagerAdapter;
import com.example.enomfinal.api.EnomerRetrofitClient;
import com.example.enomfinal.api.PerformerRetrofitClient;
import com.example.enomfinal.api.StaticIP;
import com.example.enomfinal.fragments.PerformerProfileFragments.Performer_About;
import com.example.enomfinal.fragments.PerformerProfileFragments.Performer_media;
import com.example.enomfinal.fragments.PerformerProfileFragments.Performer_event;
import com.example.enomfinal.models.DefaultResponse;
import com.example.enomfinal.models.PERFORMERFINAL;
import com.example.enomfinal.storage.SharedPrefManager;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerformerProfile extends AppCompatActivity implements View.OnClickListener {
    int thefollowed,theuser;
    String thefollower,name,category,type,image;

    Button follow,unfollow;
    float rating;
    int rcount;

    ImageView img;
    TextView pname,pcategory,ptype,prcount;
    RatingBar pscore;
    String count;

    private PERFORMERFINAL performerfinal;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performer_profile);

        thefollowed = getIntent().getExtras().getInt("intent_performerID");
        theuser = SharedPrefManager.getInstance(PerformerProfile.this).getUser().getE_id();
        thefollower = SharedPrefManager.getInstance(PerformerProfile.this).getUser().getE_type();





        img = findViewById(R.id.performerProfile_photo);
        pname = findViewById(R.id.performerProfile_fname);
        pcategory = findViewById(R.id.performerProfile_Category);
        ptype = findViewById(R.id.performerProfile_Type);
        pscore = findViewById(R.id.performerProfile_star);
        prcount = findViewById(R.id.performerProfile_reviewsCount);

         follow = findViewById(R.id.performerProfile_btnFollow);
         unfollow = findViewById(R.id.performerProfile_btnunFollow);

         follow.setOnClickListener(this);
         unfollow.setOnClickListener(this);

        tabLayout = findViewById(R.id.performerprofile_tablayout_id);
        viewPager = findViewById(R.id.performerprofile_viewpager_id);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragment(new Performer_About(),"About");
        viewPagerAdapter.addFragment(new Performer_media(),"Media");
        viewPagerAdapter.addFragment(new Performer_event(),"Event");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);


        Call<PERFORMERFINAL> call = PerformerRetrofitClient.getInstance().getApi().getperformerById(thefollowed);
        call.enqueue(new Callback<PERFORMERFINAL>() {
            @Override
            public void onResponse(Call<PERFORMERFINAL> call, Response<PERFORMERFINAL> response) {
                performerfinal = response.body();

                name = performerfinal.getPerformer_name();
                category = performerfinal.getPerformer_category();
                type = performerfinal.getPerformer_type();
                rating = performerfinal.getScore();
                rcount = performerfinal.getFeedback();
                image = performerfinal.getE_photo();

                count = Integer.toString(rcount);

                //layout
                pname.setText(name);
                pcategory.setText(category);
                ptype.setText(type);
                pscore.setRating(rating);
                prcount.setText(count);


                if(image != null && image.length() > 0){
                    Picasso.get().load(StaticIP.getWifiIP() +image).placeholder(R.drawable.imgplaceholder).into(img);
                }else{

                    Picasso.get().load(R.drawable.imgplaceholder).into(img);
                }
            }

            @Override
            public void onFailure(Call<PERFORMERFINAL> call, Throwable t) {

            }
        });




        isFollowed(thefollowed,theuser);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.performerProfile_btnFollow:
                Call<DefaultResponse> call = EnomerRetrofitClient.getInstance().getApi().follow(thefollowed,"Performer",thefollower,theuser,"ns","followed you","follow",thefollower,"Performer");
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
            case R.id.performerProfile_btnunFollow:
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
