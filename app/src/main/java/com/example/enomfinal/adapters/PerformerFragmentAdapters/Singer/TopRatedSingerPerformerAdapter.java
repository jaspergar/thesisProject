package com.example.enomfinal.adapters.PerformerFragmentAdapters.Singer;

import android.content.Context;
import android.graphics.Color;
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
import com.example.enomfinal.interfaces.PerformerItemClickListener;
import com.example.enomfinal.models.DefaultResponse;
import com.example.enomfinal.models.PERFORMERFINAL;
import com.example.enomfinal.models.PerfomerProfile_JoinedTable;
import com.example.enomfinal.storage.SharedPrefManager;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopRatedSingerPerformerAdapter extends RecyclerView.Adapter<TopRatedSingerPerformerAdapter.MyViewHolder> {

    private Context mContext;
    private List<PERFORMERFINAL> performerfinalList;
    PerformerItemClickListener performerItemClickListener;
String s;
    public TopRatedSingerPerformerAdapter(Context mContext, List<PERFORMERFINAL> performerfinalList, PerformerItemClickListener performerItemClickListener) {
        this.mContext = mContext;
        this.performerfinalList = performerfinalList;
        this.performerItemClickListener = performerItemClickListener;
    }
//
//    public TopRatedSingerPerformerAdapter(Context mContext, List<PerfomerProfile_JoinedTable> perfomerProfile_joinedTableList) {
//        this.mContext = mContext;
//        this.perfomerProfile_joinedTableList = perfomerProfile_joinedTableList;
//    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_partygoerprofile_perfomer_followed,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final int theuser = SharedPrefManager.getInstance(mContext).getUser().getE_id();
        final String thefollower = SharedPrefManager.getInstance(mContext).getUser().getE_type();
        final int thefollowed = performerfinalList.get(position).getPerformer_id();
        final String thefollowedtype= performerfinalList.get(position).getPerformer_type();

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


        float score = performerfinalList.get(position).getScore();
        if(score <= 0){
            s = "0";
        }else if(score > 0) {
            s = String.format("%.1f", score);
        }
        if(score >= 0 && score <= 1.5){
            holder.relativeLayout.setBackgroundColor(Color.parseColor("#CB202D"));
        }else if(score >= 1.6 && score <= 2.9){
            holder.relativeLayout.setBackgroundColor(Color.parseColor("#FAA916"));
        }else if(score >= 3.0 && score <= 4.4){
            holder.relativeLayout.setBackgroundColor(Color.parseColor("#00ff00"));
        }else if(score >= 4.5 && score <= 5.0){
            holder.relativeLayout.setBackgroundColor(Color.parseColor("#026838"));
        }

        holder.performerRating.setText(String.valueOf(s));
         holder.performerName.setText(performerfinalList.get(position).getPerformer_name());
         holder.performerType.setText(performerfinalList.get(position).getPerformer_type());
        if(performerfinalList.get(position).getE_photo() != null && performerfinalList.get(position).getE_photo().length() > 0){
            Picasso.get().load(StaticIP.getWifiIP() +performerfinalList.get(position).getE_photo()).placeholder(R.drawable.imgplaceholder).into(holder.performerPhoto);
        }else{

            Picasso.get().load(R.drawable.imgplaceholder).into(holder.performerPhoto);
        }


        holder.follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<DefaultResponse> call = EnomerRetrofitClient.getInstance().getApi().follow(thefollowed,thefollowedtype,thefollower,theuser,"ns","followed you","follow",thefollower,"Performer");
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
        if(performerfinalList != null) {
            return data = performerfinalList.size();
        }else if(performerfinalList == null){
            return data = 0;
        }
        return data;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView performerPhoto;
        private TextView performerName,performerRating,performerType;
        private Button follow,unfollow;
        private RelativeLayout relativeLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            relativeLayout = itemView.findViewById(R.id.toprated_rl);

            follow = itemView.findViewById(R.id.partyGoerProfile_performerBTNfollow);
            unfollow = itemView.findViewById(R.id.partyGoerProfile_performerBTNunfollow);

            performerPhoto = itemView.findViewById(R.id.partyGoerProfile_performerfollowed_photo);
            performerName = itemView.findViewById(R.id.partyGoerProfile_performerName);
            performerRating = itemView.findViewById(R.id.partyGoerProfile_performerRating);
            performerType = itemView.findViewById(R.id.partyGoerProfile_performerType);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    performerItemClickListener.onPerfomerClick(performerfinalList.get(getAdapterPosition()),performerPhoto,performerName);
                }
            });
        }
    }
}
