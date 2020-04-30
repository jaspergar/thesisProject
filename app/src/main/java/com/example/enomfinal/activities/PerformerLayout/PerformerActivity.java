package com.example.enomfinal.activities.PerformerLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.enomfinal.R;
import com.example.enomfinal.activities.BarProfile;
import com.example.enomfinal.activities.MainActivity;
import com.example.enomfinal.activities.WelcomeScreen;
import com.example.enomfinal.adapters.BarFragmentAdapters.NotsuggestedBarAdapter;
import com.example.enomfinal.adapters.BarFragmentAdapters.SuggestedBarAdapter;
import com.example.enomfinal.api.PerformerRetrofitClient;
import com.example.enomfinal.api.StaticIP;
import com.example.enomfinal.fragments.BottomNavFragments.EventFragment;
import com.example.enomfinal.fragments.BottomNavFragments.HomeFragment;
import com.example.enomfinal.fragments.BottomNavFragments.NotificationFragment;
import com.example.enomfinal.fragments.BottomNavFragments.SearchFragment;
import com.example.enomfinal.fragments.BottomNavPerformerFragments.Performer_ApplyFragment;
import com.example.enomfinal.fragments.BottomNavPerformerFragments.Performer_BrowseFragment;
import com.example.enomfinal.fragments.BottomNavPerformerFragments.Performer_HomeFragment;
import com.example.enomfinal.fragments.BottomNavPerformerFragments.Performer_MyInvitesFragment;
import com.example.enomfinal.fragments.BottomNavPerformerFragments.Performer_NotificationFragment;
import com.example.enomfinal.fragments.BottomNavPerformerFragments.Performer_ProfileFragment;
import com.example.enomfinal.fragments.SearchFragments.Search_layout_barFragment;
import com.example.enomfinal.fragments.SearchFragments.Search_layout_perfomerfragment;
import com.example.enomfinal.interfaces.BarItemClickListener;
import com.example.enomfinal.models.BARSFINAL;
import com.example.enomfinal.storage.SharedPrefManager;
import com.example.enomfinal.storage.SharedPrefManagerPProfile;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.widget.SearchView;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerformerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawerLayout;
SearchView searchView;
    private ImageView userProfilepic;
    private TextView userfname,userlname,useremail,suggestedsearch;
    private View navheader;

    private List<BARSFINAL> barsfinalList;
    private NotsuggestedBarAdapter notadapter;
    private RecyclerView suggestedbarRecyclerV,notsuggestedbarRecyclerV,searchrv;

    final Performer_HomeFragment performer_homeFragment = new Performer_HomeFragment();
    final Performer_ApplyFragment performer_applyFragment = new Performer_ApplyFragment();
    final Performer_BrowseFragment performer_browseFragment = new Performer_BrowseFragment();
    final Performer_NotificationFragment performer_notificationFragment = new Performer_NotificationFragment();
    final Performer_MyInvitesFragment performer_myInvitesFragment = new Performer_MyInvitesFragment();
    final Performer_ProfileFragment performer_profileFragment  = new Performer_ProfileFragment();
    final Search_layout_barFragment search_layout_barFragment = new Search_layout_barFragment();
    final Search_layout_perfomerfragment search_layout_perfomerfragment = new Search_layout_perfomerfragment();
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performer);

        final Toolbar toolbar = findViewById(R.id.performeract_side_toolbar_id);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.PA_side_navLayout);

        NavigationView navigationView = findViewById(R.id.pa_side_barNavigationView);
        navheader = navigationView.getHeaderView(0);
        userfname = navheader.findViewById(R.id.sidebar_enomer_fname);
        userlname = navheader.findViewById(R.id.sidebar_enomer_lname);
        useremail = navheader.findViewById(R.id.sidebar_enomer_email);
        userProfilepic = navheader.findViewById(R.id.side_bar_profile);
//
        String userimage = SharedPrefManager.getInstance(PerformerActivity.this).getUser().getE_photo();
        userfname.setText(SharedPrefManager.getInstance(PerformerActivity.this).getUser().getE_fname());
        userlname.setText(SharedPrefManager.getInstance(PerformerActivity.this).getUser().getE_lname());
        useremail.setText(SharedPrefManager.getInstance(PerformerActivity.this).getUser().getE_email());
        Picasso.get().load(StaticIP.getWifiIP() +userimage).placeholder(R.drawable.imgplaceholder).into(userProfilepic);
        navigationView.setNavigationItemSelectedListener(this);
////
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        searchrv = findViewById(R.id.peract_searchrv);
        suggestedsearch = findViewById(R.id.PerAct_suggestedTVsearch);

         bottomNavigationView = findViewById(R.id.performerActivity_BottomNav);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                if (id == R.id.performeract_home_ic) {
                    PerformerActivity.this.runOnUiThread(new Runnable(){
                        public void run() {//No.1
                            setFragment(performer_homeFragment);
                        }});
                    return true;

                } else if (id == R.id.performeract_apply_ic) {
                    setFragment(performer_applyFragment);
                    return true;
                } else if (id == R.id.performeract_browse_ic) {
                    setFragment(performer_browseFragment);
                    return true;
                } else if (id == R.id.performeract_notification_ic) {
                    setFragment(performer_notificationFragment);
                    return true;
                }else if(id == R.id.performeract_invite_ic){
                    setFragment(performer_myInvitesFragment);
                    return true;
                }
                return false;

            }
        });
        bottomNavigationView.setSelectedItemId(R.id.performeract_home_ic);

        searchBars("");
    }


    //bottom navigation
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.performerActivity_relLayout, fragment);
        fragmentTransaction.commit();
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
            case R.id.pa_side_logout_id:
                logout();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;


    }

//    private void clearPP() {
//        SharedPrefManagerPProfile.getInstance(this).clear();
//        Intent intent = new Intent(this, WelcomeScreen.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(intent);
//    }
    private void logout() {
        SharedPrefManager.getInstance(this).clear();
        SharedPrefManagerPProfile.getInstance(this).clear();
        Intent intent = new Intent(this, WelcomeScreen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
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


//
    @Override
    protected void onStart() {
        super.onStart();
        if (!SharedPrefManager.getInstance(this).isLoggedin()) {
            if(SharedPrefManagerPProfile.getInstance(this).isFilledup()) {
                Intent intent = new Intent(this, PerformerActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }else if(!SharedPrefManagerPProfile.getInstance(this).isFilledup()){
                Intent intent = new Intent(this, SelectCategoryPerformer.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        }
    }


    //search function
    public void searchBars(String key){
        Call<List<BARSFINAL>> call3 = PerformerRetrofitClient.getInstance().getApi().performersearchBar(key);
        call3.enqueue(new Callback<List<BARSFINAL>>() {
            @Override
            public void onResponse(Call<List<BARSFINAL>> call, Response<List<BARSFINAL>> response) {
                barsfinalList = response.body();

                notadapter = new NotsuggestedBarAdapter(PerformerActivity.this,barsfinalList , new BarItemClickListener() {
                    @Override
                    public void onBarClick(BARSFINAL barsfinal, ImageView barImageView) {
                        Intent intent = new Intent(PerformerActivity.this, BarProfile.class);
                        intent.putExtra("intent_barName",barsfinal.getBar_name());
                        intent.putExtra("intent_barImage",barsfinal.getBar_photo());
                        intent.putExtra("intent_barId",barsfinal.getBar_id());
//                            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Performer_BrowseFragment.this.getActivity(),barImageView,"sharedName");
//                            getActivity().startActivityFromFragment(Performer_BrowseFragment.this,intent,1,options.toBundle());
//                        getActivity().startActivityFromFragment(Performer_BrowseFragment.this,intent,1);
                        startActivityForResult(intent,1);
                    }
                });
                searchrv.setAdapter(notadapter);
                searchrv.setLayoutManager(new LinearLayoutManager(PerformerActivity.this));
                notadapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<BARSFINAL>> call, Throwable t) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        return super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.search_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) PerformerActivity.this.getSystemService(Context.SEARCH_SERVICE);
       final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(PerformerActivity.this.getComponentName())
        );
        searchView.setIconifiedByDefault(false);
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchrv.setVisibility(View.VISIBLE);
                suggestedsearch.setVisibility(View.VISIBLE);
                setFragment(search_layout_perfomerfragment);
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
                searchrv.setVisibility(View.GONE);
                suggestedsearch.setVisibility(View.GONE);
                bottomNavigationView.setVisibility(View.VISIBLE);
                bottomNavigationView.setSelectedItemId(R.id.performeract_home_ic);
                setFragment(performer_homeFragment);
                return true;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchBars(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchBars(newText);
                return false;
            }

        });
        return true;
    }

    //sidebar on back press
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        if (!SharedPrefManagerPProfile.getInstance(this).isFilledup()) {
//            Intent intent = new Intent(this, PerformerActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
//        }
//    }
}
