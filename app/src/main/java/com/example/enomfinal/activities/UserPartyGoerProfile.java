package com.example.enomfinal.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.enomfinal.R;
import com.example.enomfinal.adapters.ViewPagerAdapter;
import com.example.enomfinal.api.EnomerRetrofitClient;
import com.example.enomfinal.api.StaticIP;
import com.example.enomfinal.fragments.BarProfileFragments.About;
import com.example.enomfinal.fragments.BarProfileFragments.Event;
import com.example.enomfinal.fragments.BarProfileFragments.Map;
import com.example.enomfinal.fragments.BarProfileFragments.Offers;
import com.example.enomfinal.fragments.BarProfileFragments.photosVideos;
import com.example.enomfinal.fragments.userpFragments.Userp_Mybars;
import com.example.enomfinal.fragments.userpFragments.Userp_Mypartygoers;
import com.example.enomfinal.fragments.userpFragments.Userp_Myperformers;
import com.example.enomfinal.fragments.userpFragments.Userp_Mysched;
import com.example.enomfinal.models.Users;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserPartyGoerProfile extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private ImageView pic;
    private TextView name,gender,followerC,followingC;

    private int pid;
    private Users users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_party_goer_profile);

        tabLayout = findViewById(R.id.userp_tablayout);
        viewPager = findViewById(R.id.userp_viewpager_id);

        pic = findViewById(R.id.userp_image);
        name = findViewById(R.id.userp_name);
        gender = findViewById(R.id.userp_gender);
        followerC = findViewById(R.id.userp_followerscount);
        followingC = findViewById(R.id.userp_followingcount);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragment(new Userp_Mybars(),"Bars");
        viewPagerAdapter.addFragment(new Userp_Myperformers(),"Performers");
        viewPagerAdapter.addFragment(new Userp_Mypartygoers(),"Party Goers");
        viewPagerAdapter.addFragment(new Userp_Mysched(),"Schedule");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        pid = getIntent().getExtras().getInt("intent_partygoer_id");

        Call<Users> call = EnomerRetrofitClient.getInstance().getApi().getPartygoerWithId(pid);
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                users = response.body();
                String f_name = users.getE_fname();
                String l_name = users.getE_lname();
                String n = f_name+" "+l_name;
                String gen = users.getE_gender();
                String image = users.getE_photo();

                name.setText(n);
                gender.setText(gen);

                if(image != null){
                    Picasso.get().load(StaticIP.getWifiIP() +image).placeholder(R.drawable.imgplaceholder).into(pic);
                }else{

                    Picasso.get().load(R.drawable.imgplaceholder).into(pic);
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {

            }
        });



    }
}
