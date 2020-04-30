package com.example.enomfinal.fragments.BottomNavPerformerFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.enomfinal.R;
import com.example.enomfinal.adapters.NotificationAdapter;
import com.example.enomfinal.adapters.PerformerActivityAdapters.GigsAdapter;
import com.example.enomfinal.adapters.ViewPagerAdapter;
import com.example.enomfinal.fragments.PerformerApplyTablayoutFragment.Once_sched_ApplyFragment;
import com.example.enomfinal.fragments.PerformerApplyTablayoutFragment.Weekly_sched_ApplyFragment;
import com.example.enomfinal.fragments.userpFragments.Userp_Mybars;
import com.example.enomfinal.fragments.userpFragments.Userp_Mypartygoers;
import com.example.enomfinal.fragments.userpFragments.Userp_Myperformers;
import com.example.enomfinal.fragments.userpFragments.Userp_Mysched;
import com.example.enomfinal.models.BarGigs;
import com.example.enomfinal.models.Notification;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class Performer_ApplyFragment extends Fragment {

    private RecyclerView gigsrv;
    private GigsAdapter gigsAdapter;
    private List<BarGigs> barGigsList;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.performer_act_apply_fragment,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        tabLayout = view.findViewById(R.id.performeract_apply_tablayout);
        viewPager = view.findViewById(R.id.performeract_apply_viewpager_id);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());

        viewPagerAdapter.addFragment(new Once_sched_ApplyFragment(),"Once Schedule");
        viewPagerAdapter.addFragment(new Weekly_sched_ApplyFragment(),"Weekly Schedule");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);





    }
}
