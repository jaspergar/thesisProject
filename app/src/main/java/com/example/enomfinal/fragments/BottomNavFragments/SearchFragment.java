package com.example.enomfinal.fragments.BottomNavFragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.enomfinal.R;
import com.example.enomfinal.adapters.ViewPagerAdapter;
import com.example.enomfinal.fragments.SearchFragments.Bar_Fragment;
import com.example.enomfinal.fragments.SearchFragments.PartyGoer_Fragment;
import com.example.enomfinal.fragments.SearchFragments.Performer_Fragment;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private TabLayout tabLayout;
//    private AppBarLayout appBarLayout;
    private ViewPager viewPager;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        tabLayout = view.findViewById(R.id.tablayout_id);
//        appBarLayout = view.findViewById(R.id.appbar_id);
        viewPager = view.findViewById(R.id.viewpager_id);


        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());


        adapter.addFragment(new Bar_Fragment(),"Bar");
        adapter.addFragment(new Performer_Fragment(),"Performer");
        adapter.addFragment(new PartyGoer_Fragment(),"Party-Goer");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


    }
}
