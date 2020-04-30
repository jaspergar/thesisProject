package com.example.enomfinal.fragments.PerformerProfileFragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.enomfinal.adapters.RateReviewsAdapter;
import com.example.enomfinal.api.BarRetrofitClient;
import com.example.enomfinal.api.EnomerRetrofitClient;
import com.example.enomfinal.api.PerformerRetrofitClient;
import com.example.enomfinal.models.DefaultResponse;
import com.example.enomfinal.models.Reviews;
import com.example.enomfinal.storage.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Performer_About extends Fragment {
    TextView rateTv,pbio;
    RatingBar ratingbar;

    //reviews
    RecyclerView reviewsrv;
    private  List<Reviews> reviews;
    RateReviewsAdapter rateReviewsAdapter;

    Button submitbtn;
    EditText reviewEt;
    Intent intent;
    int  performerId;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //change if PerformerLayout is not static
        int pid = getActivity().getIntent().getExtras().getInt("intent_performerID");
        int enomerId = SharedPrefManager.getInstance(getActivity()).getUser().getE_id();
        isDoneRatePerformer(enomerId,pid);
        return view = inflater.inflate(R.layout.performerprofile_about, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rateTv = view.findViewById(R.id.performer_rateTv);
        ratingbar = view.findViewById(R.id.performerp_ratingbar);
        reviewEt = view.findViewById(R.id.performerp_reviewET);
        submitbtn = view.findViewById(R.id.performerp_submitbtn);
        pbio = view.findViewById(R.id.performerp_bio);

        reviewsrv = view.findViewById(R.id.performerp_reviewsRv);

        submitbtn.setOnClickListener(new View.OnClickListener() {
            float score;
            String feedback;
            int enomerid;
            @Override
            public void onClick(View view) {
                score = ratingbar.getRating();
                feedback = reviewEt.getText().toString().trim();
                enomerid = SharedPrefManager.getInstance(getActivity()).getUser().getE_id();
                //change if PerformerLayout is not static
                performerId = getActivity().getIntent().getExtras().getInt("intent_performerID");

                Call<DefaultResponse> call = EnomerRetrofitClient.getInstance().getApi().AddPerformerRating(performerId,enomerid,score,feedback);
                call.enqueue(new Callback<DefaultResponse>() {
                    @Override
                    public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                        if(response.code() == 201){
                            DefaultResponse dr = response.body();
                            isDoneRatePerformer(enomerid,performerId);
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
        performerId = getActivity().getIntent().getExtras().getInt("intent_performerID");
        String bio = getActivity().getIntent().getExtras().getString("intent_performerBio");

        pbio.setText(bio);

        Call<List<Reviews>> call3 = PerformerRetrofitClient.getInstance().getApi().getPerformerReviewsRating(performerId);
        call3.enqueue(new Callback<List<Reviews>>() {
            @Override
            public void onResponse(Call<List<Reviews>> call3, Response<List<Reviews>> response) {
                reviews = response.body();

                rateReviewsAdapter = new RateReviewsAdapter(reviews,getActivity());
                reviewsrv.setAdapter(rateReviewsAdapter);
                reviewsrv.setLayoutManager(new LinearLayoutManager(getActivity()));
            }

            @Override
            public void onFailure(Call<List<Reviews>> call, Throwable t) {

            }
        });
    }



    public void isDoneRatePerformer(int enomer_id,int performer_id){
        Call<DefaultResponse> call2 = EnomerRetrofitClient.getInstance().getApi().isRatedPerformer(enomer_id,performer_id);
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
