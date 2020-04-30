package com.example.enomfinal.fragments.BarProfileFragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.enomfinal.R;
import com.example.enomfinal.activities.BarProfile;
import com.example.enomfinal.activities.RateAndReview;
import com.example.enomfinal.adapters.RateReviewsAdapter;
import com.example.enomfinal.api.BarRetrofitClient;
import com.example.enomfinal.api.EnomerRetrofitClient;
import com.example.enomfinal.api.StaticIP;
import com.example.enomfinal.models.BARSFINAL;
import com.example.enomfinal.models.BarId;
import com.example.enomfinal.models.DefaultResponse;
import com.example.enomfinal.models.Reviews;
import com.example.enomfinal.storage.SharedPrefManager;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class About extends Fragment {
    private List<BarId> barIds;
    TextView rateTv,barDesc;
    RatingBar ratingbar;
    Button submitbtn;
    EditText reviewEt;
    Intent intent;
    int  barId;
    BARSFINAL barsfinal;

    //reviews
    private  List<Reviews> reviews;
    RecyclerView reviewsRv;
    RateReviewsAdapter rateReviewsAdapter;

    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int bid = getActivity().getIntent().getExtras().getInt("intent_barId");
       int enomerId = SharedPrefManager.getInstance(getActivity()).getUser().getE_id();
        isDoneRate(enomerId,bid);



        return view = inflater.inflate(R.layout.barprofile_about_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final String  barimage = getActivity().getIntent().getExtras().getString("intent_barImage");
//        final int bar_id = getActivity().getIntent().getExtras().getInt("intent_barId");

        rateTv = view.findViewById(R.id.rateTv);
        ratingbar = view.findViewById(R.id.barp_ratingbar);
        reviewEt = view.findViewById(R.id.barp_reviewET);
        submitbtn = view.findViewById(R.id.barp_submitbtn);
        reviewsRv = view.findViewById(R.id.barp_reviewsRv);
        barDesc = view.findViewById(R.id.barp_desc);

           int BARID = getActivity().getIntent().getExtras().getInt("intent_barId");
        String bardesc = getActivity().getIntent().getExtras().getString("intent_barDesc");


        Call<BARSFINAL> call2 = BarRetrofitClient.getInstance().getApi().getBarsWithIdFinal(BARID);
        call2.enqueue(new Callback<BARSFINAL>() {
            @Override
            public void onResponse(Call<BARSFINAL> call, Response<BARSFINAL> response) {
                barsfinal = response.body();
                String bardesc = barsfinal.getBar_description();
                barDesc.setText(bardesc);

            }

            @Override
            public void onFailure(Call<BARSFINAL> call, Throwable t) {

            }
        });

        Call<List<Reviews>> call = BarRetrofitClient.getInstance().getApi().getReviewsRating(BARID);
        call.enqueue(new Callback<List<Reviews>>() {
            @Override
            public void onResponse(Call<List<Reviews>> call, Response<List<Reviews>> response) {
                reviews = response.body();

                rateReviewsAdapter = new RateReviewsAdapter(reviews,getActivity());
                reviewsRv.setAdapter(rateReviewsAdapter);
                reviewsRv.setLayoutManager(new LinearLayoutManager(getActivity()));
            }

            @Override
            public void onFailure(Call<List<Reviews>> call, Throwable t) {

            }
        });

        submitbtn.setOnClickListener(new View.OnClickListener() {
            float score;
            String feedback;
            int enomerid;
            @Override
            public void onClick(View view) {
                score = ratingbar.getRating();
                feedback = reviewEt.getText().toString().trim();
                enomerid = SharedPrefManager.getInstance(getActivity()).getUser().getE_id();



                Call<DefaultResponse> call = EnomerRetrofitClient.getInstance().getApi().AddBarRating(barId,enomerid,score,feedback);
                call.enqueue(new Callback<DefaultResponse>() {
                    @Override
                    public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                        if(response.code() == 201){
                            DefaultResponse dr = response.body();
                               isDoneRate(enomerid,barId);
                          Toast.makeText(getActivity(),dr.getMsg(),Toast.LENGTH_LONG).show();
                        }else if(response.code() == 422){
                            DefaultResponse dr = response.body();
                            Toast.makeText(getActivity(),dr.getMsg(),Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<DefaultResponse> call, Throwable t) {

                    }
                });
            }
        });




    }

    public void isDoneRate(int enomer_id,int bar_id){
        Call<DefaultResponse> call2 = EnomerRetrofitClient.getInstance().getApi().isRated(enomer_id,bar_id);
        call2.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {


                if(response.code() == 201){
                      ratingbar.setVisibility(View.GONE);
                      reviewEt.setVisibility(View.GONE);
                      submitbtn.setVisibility(View.GONE);
                      rateTv.setVisibility(View.GONE);
                 }else if(response.code() == 422){
                    Toast.makeText(getActivity(),"Rate us",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {

            }
        });
    }


}
