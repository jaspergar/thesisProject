package com.example.enomfinal.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.enomfinal.R;
import com.example.enomfinal.api.StaticIP;
import com.example.enomfinal.fragments.BottomNavFragments.EventFragment;
import com.example.enomfinal.fragments.BottomNavFragments.HomeFragment;
import com.example.enomfinal.fragments.BottomNavFragments.NotificationFragment;
import com.example.enomfinal.fragments.BottomNavFragments.PartyGoerProfileFragment;
import com.example.enomfinal.fragments.BottomNavFragments.SearchFragment;
import com.example.enomfinal.fragments.SearchFragments.Search_layout_barFragment;
import com.example.enomfinal.storage.SharedPrefManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

   private ImageView userProfilepic;
   private TextView userfname,userlname,useremail;
    private DrawerLayout drawerLayout;
    private View navheader;

    BottomNavigationView bottomNavigationView;

    final HomeFragment home_fragment = new HomeFragment();
    final SearchFragment search_fragment = new SearchFragment();
    final EventFragment event_fragment = new EventFragment();
    final NotificationFragment notification_fragment = new NotificationFragment();
    final Search_layout_barFragment search_layout_barFragment = new Search_layout_barFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        final Toolbar toolbar = findViewById(R.id.side_toolbar_id);
        setSupportActionBar(toolbar);
////
//        //sidebar drawer
        drawerLayout = findViewById(R.id.MA_side_navLayout);



  //   Picasso.get().load(StaticIP.getWifiIP() +userimage).into(userProfilepic);


//
        NavigationView navigationView = findViewById(R.id.side_barNavigationView);
        navheader = navigationView.getHeaderView(0);
        userfname = navheader.findViewById(R.id.sidebar_enomer_fname);
        userlname = navheader.findViewById(R.id.sidebar_enomer_lname);
        useremail = navheader.findViewById(R.id.sidebar_enomer_email);
        userProfilepic = navheader.findViewById(R.id.side_bar_profile);
//
        String userimage = SharedPrefManager.getInstance(MainActivity.this).getUser().getE_photo();
        userfname.setText(SharedPrefManager.getInstance(MainActivity.this).getUser().getE_fname());
        userlname.setText(SharedPrefManager.getInstance(MainActivity.this).getUser().getE_lname());
        useremail.setText(SharedPrefManager.getInstance(MainActivity.this).getUser().getE_email());
        Picasso.get().load(StaticIP.getWifiIP() +userimage).placeholder(R.drawable.imgplaceholder).into(userProfilepic);
        navigationView.setNavigationItemSelectedListener(this);

        userProfilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int userId = SharedPrefManager.getInstance(MainActivity.this).getUser().getE_id();
                Intent intent = new Intent(MainActivity.this,UserPartyGoerProfile.class);
                intent.putExtra("intent_partygoer_id",userId);
                startActivity(intent);
            }
        });
////
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


//        if(savedInstanceState == null){
//                navigationView.setCheckedItem(R.id.side_bar_followed_id);
//            }


        bottomNavigationView = findViewById(R.id.bottom_navi);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                if (id == R.id.home_ic) {
                    MainActivity.this.runOnUiThread(new Runnable(){
                        public void run() {//No.1
                            setFragment(home_fragment);
                        }});
                    return true;

                } else if (id == R.id.search_ic) {
                            setFragment(search_fragment);
                    return true;
                } else if (id == R.id.event_ic) {
                            setFragment(event_fragment);
                    return true;
                } else if (id == R.id.notification_ic) {
                            setFragment(notification_fragment);
                    return true;
                }
                return false;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.home_ic);



    }


    private void logout() {
        SharedPrefManager.getInstance(this).clear();
        Intent intent = new Intent(this, WelcomeScreen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
//            case R.id.side_bar_followed_id:
//                getSupportFragmentManager().beginTransaction().replace(R.id.side_bar_framelayout_id,new BarFollowedFragment()).commit();
//                break;
//            case R.id.side_performer_followed_id:
//                getSupportFragmentManager().beginTransaction().replace(R.id.side_bar_framelayout_id,new PerformerFollowedFragment()).commit();
//                break;
//            case R.id.side_people_followed_id:
//                getSupportFragmentManager().beginTransaction().replace(R.id.side_bar_framelayout_id,new PeopleFollowFragment()).commit();
//                break;
            case R.id.side_logout_id:
                logout();
               break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;


    }



    //sidebar on back press
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //bottom navigation
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.relativeLayout, fragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!SharedPrefManager.getInstance(this).isLoggedin()) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        return super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.search_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) MainActivity.this.getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(MainActivity.this.getComponentName())
        );
        searchView.setIconifiedByDefault(false);
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(search_layout_barFragment);
                bottomNavigationView.setVisibility(View.GONE);
            }
        });

        menuItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                bottomNavigationView.setSelectedItemId(R.id.home_ic);
                setFragment(home_fragment);
                bottomNavigationView.setVisibility(View.VISIBLE);
                return true;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
//                searchBars(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                searchBars(newText);
                return false;
            }
        });
        return true;
    }
}
