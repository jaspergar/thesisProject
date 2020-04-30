package com.example.enomfinal.fragments.BottomNavFragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.enomfinal.R;
import com.example.enomfinal.adapters.ViewPagerAdapter;
import com.example.enomfinal.fragments.BarProfileFragments.About;
import com.example.enomfinal.fragments.BarProfileFragments.Event;
import com.example.enomfinal.fragments.BarProfileFragments.Map;
import com.example.enomfinal.fragments.BarProfileFragments.Offers;
import com.example.enomfinal.fragments.BarProfileFragments.photosVideos;
import com.example.enomfinal.fragments.EventGoingFragments.BarEventGoingFragment;
import com.example.enomfinal.fragments.EventGoingFragments.PerformerEventGoingFragment;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventFragment extends Fragment {

private TabLayout tabLayout;
private ViewPager viewPager;
private ViewPagerAdapter viewPagerAdapter;


    public EventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabLayout = view.findViewById(R.id.eventfragment_tablayout_id);
        viewPager = view.findViewById(R.id.eventfragment_viewpager_id);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());

        viewPagerAdapter.addFragment(new BarEventGoingFragment(),"Bar Schedule");
        viewPagerAdapter.addFragment(new PerformerEventGoingFragment(),"Performer Schedule");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }


}
