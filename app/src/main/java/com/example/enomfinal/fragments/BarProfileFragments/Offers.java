package com.example.enomfinal.fragments.BarProfileFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.enomfinal.R;
import com.example.enomfinal.adapters.BarProfileAdapters.BarPOfferItemAdapter;
import com.example.enomfinal.adapters.BarProfileAdapters.BarPOfferSliderAdapter;
import com.example.enomfinal.api.BarRetrofitClient;
import com.example.enomfinal.models.Offer;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Offers extends Fragment {
    private List<Offer> offerList;
    private ViewPager viewPager;
    private TabLayout indicator;
    private RecyclerView offeritemRV;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return view = inflater.inflate(R.layout.barprofile_offers_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager = view.findViewById(R.id.barprofile_offer_viewpager_id);
        indicator = view.findViewById(R.id.slide_indicator);

        //item rv
        offeritemRV = view.findViewById(R.id.barp_offers_rv);

        int BARID = getActivity().getIntent().getExtras().getInt("intent_barId");
        Call<List<Offer>> call = BarRetrofitClient.getInstance().getApi().getOffers(BARID);
        call.enqueue(new Callback<List<Offer>>() {
            @Override
            public void onResponse(Call<List<Offer>> call, Response<List<Offer>> response) {
                offerList = response.body();

                // slider
                BarPOfferSliderAdapter adapter = new BarPOfferSliderAdapter(getActivity(),offerList);
                viewPager.setAdapter(adapter);
                //item
                BarPOfferItemAdapter itemAdapter = new BarPOfferItemAdapter(getActivity(),offerList);
                offeritemRV.setLayoutManager(new GridLayoutManager(getActivity(),3));
                offeritemRV.setAdapter(itemAdapter);


                //slider timer
                Timer timer = new Timer();
                timer.scheduleAtFixedRate(new Offers.sliderTimer(),4000,6000);

                indicator.setupWithViewPager(viewPager,true);
            }

            @Override
            public void onFailure(Call<List<Offer>> call, Throwable t) {

            }
        });






     }

     //timer class
     class sliderTimer extends TimerTask{
         @Override
         public void run() {
             if(getActivity()== null)
                 return;
//             if (getActivity() != null) {
                 getActivity().runOnUiThread(new Runnable() {
                     @Override
                     public void run() {
                         if (viewPager.getCurrentItem() < offerList.size() - 1) {
                             viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                         } else {
                             viewPager.setCurrentItem(0);
                         }
                     }
                 });
//             }
         }
     }
}
