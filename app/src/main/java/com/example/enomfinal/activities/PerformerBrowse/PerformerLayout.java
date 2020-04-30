package com.example.enomfinal.activities.PerformerBrowse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.enomfinal.R;
import com.example.enomfinal.activities.PerformerProfile;
import com.example.enomfinal.adapters.PerformerFragmentAdapters.Singer.NotTopRatedSingerPerformerAdapter;
import com.example.enomfinal.adapters.PerformerFragmentAdapters.Singer.TopRatedSingerPerformerAdapter;
import com.example.enomfinal.api.PerformerRetrofitClient;
import com.example.enomfinal.interfaces.PerformerItemClickListener;
import com.example.enomfinal.models.PERFORMERFINAL;
import com.example.enomfinal.models.PerfomerProfile_JoinedTable;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerformerLayout extends AppCompatActivity {

    RecyclerView topratedRV,nottopratedRV;
    //singer
    TopRatedSingerPerformerAdapter topRatedSingerPerformerAdapter;
    NotTopRatedSingerPerformerAdapter notTopRatedSingerPerformerAdapter;

    List<PerfomerProfile_JoinedTable> perfomerProfile_joinedTableList;
    List<PERFORMERFINAL> performerfinalList;

    TextView toprated,allperformer,oops;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performer_layout);

        String category = getIntent().getExtras().getString("category");
        String type = getIntent().getExtras().getString("type");
        topratedRV = findViewById(R.id.toprated_Rv);
        nottopratedRV = findViewById(R.id.all_RV);

        toprated = findViewById(R.id.performerlayout_topratedTV);
        allperformer = findViewById(R.id.performerlayout_allperformerTV);
        oops = findViewById(R.id.performerlayout_noitemTV);

        getSupportActionBar().setTitle(category+" , "+type);
        //top rated
        Call<List<PERFORMERFINAL>> call = PerformerRetrofitClient.getInstance().getApi().getallperformersuggested(category,type);
        call.enqueue(new Callback<List<PERFORMERFINAL>>() {
            @Override
            public void onResponse(Call<List<PERFORMERFINAL>> call, Response<List<PERFORMERFINAL>> response) {
                performerfinalList = response.body();
                if(performerfinalList.size() != 0) {
                    topRatedSingerPerformerAdapter = new TopRatedSingerPerformerAdapter(PerformerLayout.this, performerfinalList, new PerformerItemClickListener() {
                        @Override
                        public void onPerfomerClick(PERFORMERFINAL performerfinal, ImageView performerImageView, TextView performerName) {
                            Intent intent = new Intent(PerformerLayout.this, PerformerProfile.class);
                            intent.putExtra("intent_performerName", performerfinal.getPerformer_name());
                            intent.putExtra("intent_performerImage", performerfinal.getE_photo());
                            intent.putExtra("intent_performerID", performerfinal.getPerformer_id());
                            intent.putExtra("intent_performerCategory", performerfinal.getPerformer_category());
                            intent.putExtra("intent_performerType", performerfinal.getPerformer_type());
                            intent.putExtra("intent_performerScore", performerfinal.getScore());
                            intent.putExtra("intent_performerRCount", performerfinal.getFeedback());
                            intent.putExtra("intent_performerBio", performerfinal.getPerformer_bio());
//                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(PerformerLayout.this,performerImageView,"sharedName");
//                startActivityForResult(intent,1,options.toBundle());
                            startActivityForResult(intent, 1);
                        }
                    });
                    toprated.setVisibility(View.VISIBLE);
                    topratedRV.setAdapter(topRatedSingerPerformerAdapter);
                    topratedRV.setLayoutManager(new LinearLayoutManager(PerformerLayout.this, LinearLayoutManager.HORIZONTAL, false));
                }else if(performerfinalList.size() == 0){
//                    topratedRV.setVisibility(View.GONE);
//                    oops.setVisibility(View.VISIBLE);
                    toprated.setVisibility(View.GONE);
                }


            }

            @Override
            public void onFailure(Call<List<PERFORMERFINAL>> call, Throwable t) {

            }
        });

//all

        Call<List<PERFORMERFINAL>> call2 = PerformerRetrofitClient.getInstance().getApi().getallperformer(category,type);
        call2.enqueue(new Callback<List<PERFORMERFINAL>>() {
            @Override
            public void onResponse(Call<List<PERFORMERFINAL>> call, Response<List<PERFORMERFINAL>> response) {
                performerfinalList = response.body();
                if(performerfinalList.size() != 0) {
                    notTopRatedSingerPerformerAdapter = new NotTopRatedSingerPerformerAdapter(PerformerLayout.this, performerfinalList, new PerformerItemClickListener() {
                        @Override
                        public void onPerfomerClick(PERFORMERFINAL performerfinal, ImageView performerImageView, TextView performerName) {
                            Intent intent = new Intent(PerformerLayout.this, PerformerProfile.class);
                            intent.putExtra("intent_performerName", performerfinal.getPerformer_name());
                            intent.putExtra("intent_performerImage", performerfinal.getE_photo());
                            intent.putExtra("intent_performerID", performerfinal.getPerformer_id());
                            intent.putExtra("intent_performerCategory", performerfinal.getPerformer_category());
                            intent.putExtra("intent_performerType", performerfinal.getPerformer_type());
                            intent.putExtra("intent_performerScore", performerfinal.getScore());
                            intent.putExtra("intent_performerRCount", performerfinal.getFeedback());
                            intent.putExtra("intent_performerBio", performerfinal.getPerformer_bio());
//                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(PerformerLayout.this,performerImageView,"sharedName");
//                startActivityForResult(intent,1,options.toBundle());
                            startActivityForResult(intent, 1);
                        }
                    });
                    allperformer.setVisibility(View.VISIBLE);
                    oops.setVisibility(View.GONE);
                    nottopratedRV.setAdapter(notTopRatedSingerPerformerAdapter);
                    nottopratedRV.setLayoutManager(new LinearLayoutManager(PerformerLayout.this));
                }else if(performerfinalList.size() == 0){
//                    nottopratedRV.setVisibility(View.GONE);
                    oops.setVisibility(View.VISIBLE);
                    allperformer.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<PERFORMERFINAL>> call, Throwable t) {

            }
        });



    }
}
