package com.example.enomfinal.adapters.BarFragmentAdapters;

import android.content.Context;


import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.enomfinal.R;
import com.example.enomfinal.api.EnomerRetrofitClient;
import com.example.enomfinal.api.StaticIP;
import com.example.enomfinal.interfaces.BarItemClickListener;
import com.example.enomfinal.models.BARSFINAL;
import com.example.enomfinal.models.Bars;
import com.example.enomfinal.models.DefaultResponse;
import com.example.enomfinal.storage.SharedPrefManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;


import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Time;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotsuggestedBarAdapter extends RecyclerView.Adapter<NotsuggestedBarAdapter.ViewHolder>{

    Context mContext;
    List<BARSFINAL> myData;
    BarItemClickListener barItemClickListener;
String s;





    public NotsuggestedBarAdapter(Context mContext, List<BARSFINAL> myData, BarItemClickListener barItemClickListener) {
        this.mContext = mContext;
        this.myData = myData;
        this.barItemClickListener = barItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_bar_not_suggested,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final int theuser = SharedPrefManager.getInstance(mContext).getUser().getE_id();
        final String thefollower = SharedPrefManager.getInstance(mContext).getUser().getE_type();
        final int thefollowed = myData.get(position).getBar_id();

        Call<DefaultResponse> call2 = EnomerRetrofitClient.getInstance().getApi().isFollowed(thefollowed,theuser);
        call2.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {


                if(response.code() == 201){
                    holder.follow.setVisibility(View.GONE);
                    holder.unfollow.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {

            }
        });

        float score = myData.get(position).getScore();
        if(score <= 0){
            s = "0";
        }else if(score > 0) {
            s = String.format("%.1f", score);
        }
        if(score >= 0 && score <= 1.5){
            holder.rating_relativeL.setBackgroundColor(Color.parseColor("#CB202D"));
        }else if(score >= 1.6 && score <= 2.9){
            holder.rating_relativeL.setBackgroundColor(Color.parseColor("#FAA916"));
        }else if(score >= 3.0 && score <= 4.4){
            holder.rating_relativeL.setBackgroundColor(Color.parseColor("#00ff00"));
        }else if(score >= 4.5 && score <= 5.0){
            holder.rating_relativeL.setBackgroundColor(Color.parseColor("#026838"));
        }

        holder.BarTitle.setText(myData.get(position).getBar_name());
        holder.BarRating.setText(String.valueOf(s));
        holder.BarLocation.setText(myData.get(position).getBar_location());


        holder.BarType.setText(myData.get(position).getBar_type());

        int Rcount = myData.get(position).getFeedback();
        String Fcount = Integer.toString(Rcount);

        holder.BarReviewsCount.setText(Fcount);

        if(myData.get(position).getBar_photo() != null && myData.get(position).getBar_photo().length() > 0){
            Picasso.get().load(StaticIP.getWifiIP() +myData.get(position).getBar_photo()).placeholder(R.drawable.imgplaceholder).into(holder.Barimage);
        }else{

            Picasso.get().load(R.drawable.imgplaceholder).into(holder.Barimage);
        }

        holder.follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<DefaultResponse> call = EnomerRetrofitClient.getInstance().getApi().follow(thefollowed,"Bar",thefollower,theuser,"ns","followed you","follow",thefollower,"bar");
                call.enqueue(new Callback<DefaultResponse>() {
                    @Override
                    public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                        if(response.code() == 201){
                            holder.follow.setVisibility(View.GONE);
                            holder.unfollow.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onFailure(Call<DefaultResponse> call, Throwable t) {

                    }
                });
            }
        });

        holder.unfollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<DefaultResponse> call2 = EnomerRetrofitClient.getInstance().getApi().unfollow(thefollowed,theuser);
                call2.enqueue(new Callback<DefaultResponse>() {
                    @Override
                    public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                        if(response.code() == 201){
                            holder.follow.setVisibility(View.VISIBLE);
                            holder.unfollow.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<DefaultResponse> call, Throwable t) {

                    }
                });
            }
        });




    }

    @Override
    public int getItemCount() {
        int data = 0;
        if(myData != null) {
            return data = myData.size();
        }else if(myData == null){
            return data = 0;
        }
        return data;

    }



    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView BarTitle,BarRating,BarLocation,BarOpen,BarClose,BarReviewsCount,BarType;
        private ImageView Barimage;
        private Button follow,unfollow;
        private RelativeLayout rating_relativeL;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            rating_relativeL = itemView.findViewById(R.id.notsuggested_ratingRelativeL_id);

            follow = itemView.findViewById(R.id.notsug_btnfollow);
            unfollow = itemView.findViewById(R.id.notsug_btnunfollow);

            BarTitle = itemView.findViewById(R.id.not_suggested_barname);
            Barimage = itemView.findViewById(R.id.notsuggested_barImage_id);
            BarRating = itemView.findViewById(R.id.notsuggested_barRating_id);

            BarLocation = itemView.findViewById(R.id.not_suggested_barlocation);
            BarOpen = itemView.findViewById(R.id.not_suggested_barOpenTime);
            BarClose = itemView.findViewById(R.id.not_suggested_barCloseTime);
            BarReviewsCount = itemView.findViewById(R.id.notsuggested_bar_feedback_count);
            BarType = itemView.findViewById(R.id.notsuggested_bartype_id);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    barItemClickListener.onBarClick(myData.get(getAdapterPosition()),Barimage);
                }
            });
        }
    }
}
