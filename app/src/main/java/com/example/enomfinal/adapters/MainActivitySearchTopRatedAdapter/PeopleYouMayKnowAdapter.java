package com.example.enomfinal.adapters.MainActivitySearchTopRatedAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.enomfinal.R;
import com.example.enomfinal.api.StaticIP;
import com.example.enomfinal.interfaces.PartyGoerItemClickListener;
import com.example.enomfinal.models.Users;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PeopleYouMayKnowAdapter extends RecyclerView.Adapter<PeopleYouMayKnowAdapter.ViewHolder>{

    Context mContext;
    List<Users> myData;
    PartyGoerItemClickListener partyGoerItemClickListener;

    public PeopleYouMayKnowAdapter(Context mContext, List<Users> myData, PartyGoerItemClickListener partyGoerItemClickListener) {
        this.mContext = mContext;
        this.myData = myData;
        this.partyGoerItemClickListener = partyGoerItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_partygoerprofile_partygoer_followed,parent,false);
        return new PeopleYouMayKnowAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.PartyGoerFName.setText(myData.get(position).getE_fname());
        holder.PartyGoerLName.setText(myData.get(position).getE_lname());

        if(myData.get(position).getE_photo() != null && myData.get(position).getE_photo().length() > 0){
            Picasso.get().load(StaticIP.getWifiIP() +myData.get(position).getE_photo()).placeholder(R.drawable.imgplaceholder).into(holder.PartyGoerProfilePic);
        }else{

            Picasso.get().load(R.drawable.imgplaceholder).into(holder.PartyGoerProfilePic);
        }
    }

    @Override
    public int getItemCount() {
        return myData.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView PartyGoerFName,PartyGoerLName;
        private ImageView PartyGoerProfilePic;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            PartyGoerFName = itemView.findViewById(R.id.party_goer_fname);
            PartyGoerLName = itemView.findViewById(R.id.party_goer_lname);
            PartyGoerProfilePic = itemView.findViewById(R.id.suggested_partygoerImage_id);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    partyGoerItemClickListener.onPartyGoerClick(myData.get(getAdapterPosition()),PartyGoerProfilePic);
                }
            });

        }
    }
}
