package com.example.enomfinal.adapters.PartyGoerFragmentAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.enomfinal.R;
import com.example.enomfinal.api.EnomerRetrofitClient;
import com.example.enomfinal.api.StaticIP;
import com.example.enomfinal.interfaces.PartyGoerItemClickListener;
import com.example.enomfinal.models.DefaultResponse;
import com.example.enomfinal.models.Users;
import com.example.enomfinal.storage.SharedPrefManager;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SuggestedPartyGoerAdapter extends RecyclerView.Adapter<SuggestedPartyGoerAdapter.ViewHolder>{

    Context mContext;
    List<Users> myData;
    PartyGoerItemClickListener partyGoerItemClickListener;




    public SuggestedPartyGoerAdapter(Context mContext, List<Users> myData) {
        this.mContext = mContext;
        this.myData = myData;
    }

    public SuggestedPartyGoerAdapter(Context mContext, List<Users> myData, PartyGoerItemClickListener partyGoerItemClickListener) {
        this.mContext = mContext;
        this.myData = myData;
        this.partyGoerItemClickListener = partyGoerItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_party_goer,parent,false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final int theuser = SharedPrefManager.getInstance(mContext).getUser().getE_id();
        final String thefollower = SharedPrefManager.getInstance(mContext).getUser().getE_type();
        final int thefollowed = myData.get(position).getE_id();
        final String thefollowedtype= myData.get(position).getE_type();

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

        holder.PartyGoerFName.setText(myData.get(position).getE_fname());
        holder.PartyGoerLName.setText(myData.get(position).getE_lname());
        holder.PartyGoerGender.setText(myData.get(position).getE_gender());

        if(myData.get(position).getE_photo() != null && myData.get(position).getE_photo().length() > 0){
            Picasso.get().load(StaticIP.getWifiIP() +myData.get(position).getE_photo()).placeholder(R.drawable.imgplaceholder).into(holder.PartyGoerProfilePic);
        }else{

            Picasso.get().load(R.drawable.imgplaceholder).into(holder.PartyGoerProfilePic);
        }

        holder.follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<DefaultResponse> call = EnomerRetrofitClient.getInstance().getApi().follow(thefollowed,thefollowedtype,thefollower,theuser,"ns","followed you","follow",thefollower,thefollowedtype);
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
        return myData.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView PartyGoerFName,PartyGoerLName,PartyGoerGender;
        private ImageView PartyGoerProfilePic;
        private Button follow,unfollow;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            follow = itemView.findViewById(R.id.party_goerFragment_followbtn);
            unfollow = itemView.findViewById(R.id.party_goerFragment_unfollowbtn);

            PartyGoerFName = itemView.findViewById(R.id.party_goer_fname);
            PartyGoerLName = itemView.findViewById(R.id.party_goer_lname);
            PartyGoerProfilePic = itemView.findViewById(R.id.suggested_partygoerImage_id);
            PartyGoerGender = itemView.findViewById(R.id.party_goer_gender_id);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    partyGoerItemClickListener.onPartyGoerClick(myData.get(getAdapterPosition()),PartyGoerProfilePic);
                }
            });

        }
    }
}
