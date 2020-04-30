package com.example.enomfinal.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.enomfinal.R;
import com.example.enomfinal.adapters.ViewPagerAdapter;
import com.example.enomfinal.api.BarRetrofitClient;
import com.example.enomfinal.api.EnomerRetrofitClient;
import com.example.enomfinal.api.StaticIP;
import com.example.enomfinal.fragments.BarProfileFragments.About;
import com.example.enomfinal.fragments.BarProfileFragments.Event;
import com.example.enomfinal.fragments.BarProfileFragments.Map;
import com.example.enomfinal.fragments.BarProfileFragments.Offers;
import com.example.enomfinal.fragments.BarProfileFragments.photosVideos;
import com.example.enomfinal.models.BARSFINAL;
import com.example.enomfinal.models.DefaultResponse;
import com.example.enomfinal.storage.SharedPrefManager;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BarProfile extends AppCompatActivity implements View.OnClickListener {

    private ImageView barProfileImage;
    private TextView barprofileName, barProfileLocation,bartype,barRCount;
      private RatingBar rating;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private BARSFINAL barsfinal;


    Button follow,unfollow;

    int  thefollowed;
    int theuser;
    String thefollower;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_profile);

          thefollowed = getIntent().getExtras().getInt("intent_barId");
         theuser = SharedPrefManager.getInstance(BarProfile.this).getUser().getE_id();
         thefollower = SharedPrefManager.getInstance(BarProfile.this).getUser().getE_type();

        tabLayout = findViewById(R.id.barprofile_tablayout_id);
        viewPager = findViewById(R.id.barprofile_viewpager_id);

        bartype = findViewById(R.id.BarP_type);
        barRCount = findViewById(R.id.BarP_ReviewCount);
        rating = findViewById(R.id.barp_ratingbars);



        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragment(new About(),"About");
        viewPagerAdapter.addFragment(new photosVideos(),"Media");
        viewPagerAdapter.addFragment(new Offers(),"Offers");
        viewPagerAdapter.addFragment(new Event(),"Event");
        viewPagerAdapter.addFragment(new Map(),"Map");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        follow = findViewById(R.id.barprofile_follow_btn);
        unfollow = findViewById(R.id.barprofile_unfollow_btn);

        follow.setOnClickListener(this);
        unfollow.setOnClickListener(this);
        initViews();
        isFollowed(thefollowed,theuser);

    }

    void initViews(){
        int barid = getIntent().getExtras().getInt("intent_barId");

        Call<BARSFINAL> call = BarRetrofitClient.getInstance().getApi().getBarsWithIdFinal(barid);
        call.enqueue(new Callback<BARSFINAL>() {
            @Override
            public void onResponse(Call<BARSFINAL> call, Response<BARSFINAL> response) {
                 barsfinal = response.body();
                String barname = barsfinal.getBar_name();
                String  barimage = barsfinal.getBar_photo();
                String bar_type = barsfinal.getBar_type();
                float  barRating = barsfinal.getScore();
                int barCount = barsfinal.getFeedback();

                String revCount = Integer.toString(barCount);

                bartype.setText(bar_type);
                rating.setRating(barRating);
                barRCount.setText(revCount);

                barProfileImage = findViewById(R.id.barprofile_image_id);
                Picasso.get().load(StaticIP.getWifiIP() +barimage).placeholder(R.drawable.imgplaceholder).into(barProfileImage);

                barprofileName = findViewById(R.id.BarP_Name);
                barprofileName.setText(barname);
                barProfileImage.setAnimation(AnimationUtils.loadAnimation(BarProfile.this,R.anim.scale_animation));


            }

            @Override
            public void onFailure(Call<BARSFINAL> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View view) {
//        int  thefollowed = getIntent().getExtras().getInt("intent_barId");
//        int theuser = SharedPrefManager.getInstance(BarProfile.this).getUser().getE_id();
//        String thefollower = SharedPrefManager.getInstance(BarProfile.this).getUser().getE_type();
        switch (view.getId()){
            case R.id.barprofile_follow_btn:
                Call<DefaultResponse> call = EnomerRetrofitClient.getInstance().getApi().follow(thefollowed,"Bar",thefollower,theuser,"ns","followed you","follow",thefollower,"bar");
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
            case R.id.barprofile_unfollow_btn:
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
