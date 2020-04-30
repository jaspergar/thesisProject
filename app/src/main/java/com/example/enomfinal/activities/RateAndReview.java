package com.example.enomfinal.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.enomfinal.R;
import com.example.enomfinal.api.EnomerRetrofitClient;
import com.example.enomfinal.fragments.BarProfileFragments.About;
import com.example.enomfinal.models.DefaultResponse;
import com.example.enomfinal.storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RateAndReview extends AppCompatActivity {

    RatingBar ratingbar;
    Button submitbtn;
    EditText reviewEt;
     String  barimage;
     int barId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_and_review);
        barId = getIntent().getExtras().getInt("barId");
         barimage =getIntent().getExtras().getString("barimage");

        Toast.makeText(this,barId+"",Toast.LENGTH_LONG).show();

        ratingbar = findViewById(R.id.barp_ratingbar);
        reviewEt = findViewById(R.id.barp_reviewET);
        submitbtn = findViewById(R.id.barp_submitbtn);

        ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Toast.makeText(RateAndReview.this,v+"",Toast.LENGTH_LONG).show();
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
                barId = getIntent().getExtras().getInt("barId");
                enomerid = SharedPrefManager.getInstance(RateAndReview.this).getUser().getE_id();

//                 Toast.makeText(RateAndReview.this,barId+"-"+enomerid+"-"+score+"-"+feedback,Toast.LENGTH_LONG).show();
                 Call<DefaultResponse> call = EnomerRetrofitClient.getInstance().getApi().AddBarRating(barId,enomerid,score,feedback);
                 call.enqueue(new Callback<DefaultResponse>() {
                     @Override
                     public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                         DefaultResponse dr = response.body();
                         if(response.code() == 201){

//                             Intent output = new Intent();
//                             output.putExtra("message",dr.getMsg());
//                             setResult(barId, output);
//                             finish();

                             Intent intent = new Intent(RateAndReview.this, BarProfile.class);
                             startActivity(intent);

                         }

                     }

                     @Override
                     public void onFailure(Call<DefaultResponse> call, Throwable t) {

                     }
                 });
            }
        });


    }
}
