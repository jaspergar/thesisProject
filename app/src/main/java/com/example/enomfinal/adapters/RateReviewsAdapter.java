package com.example.enomfinal.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.enomfinal.R;
import com.example.enomfinal.api.StaticIP;
import com.example.enomfinal.models.Reviews;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RateReviewsAdapter extends RecyclerView.Adapter<RateReviewsAdapter.MyViewHolder>  {

    List<Reviews> myData;
    Context context;

    public RateReviewsAdapter(List<Reviews> myData, Context context) {
        this.myData = myData;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_reviews,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
          holder.fname.setText(myData.get(position).getE_fname());
          holder.lname.setText(myData.get(position).getE_lname());

//          Date d = new Date(myData.get(position).getDate_rated();)
          Date d = myData.get(position).getDate_rated();
          DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
          String date = df.format(d);

          holder.daterated.setText(date);
          holder.feedback.setText(myData.get(position).getFeedback());
          holder.score.setRating(myData.get(position).getScore());

        if(myData.get(position).getE_photo() != null && myData.get(position).getE_photo().length() > 0){
            Picasso.get().load(StaticIP.getWifiIP() +myData.get(position).getE_photo()).placeholder(R.drawable.imgplaceholder).into(holder.reviewphoto);
        }else{

            Picasso.get().load(R.drawable.imgplaceholder).into(holder.reviewphoto);
        }
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


    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView reviewphoto;
        TextView fname,lname,feedback;
        RatingBar score;
        TextView daterated;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            reviewphoto = itemView.findViewById(R.id.reviews_img);
            fname = itemView.findViewById(R.id.reviews_fname);
            lname = itemView.findViewById(R.id.reviews_lname);
            feedback = itemView.findViewById(R.id.reviews_feedback);
            score = itemView.findViewById(R.id.reviews_ratingStar);
            daterated = itemView.findViewById(R.id.reviews_dateRated);
        }
    }
}
