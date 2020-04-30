package com.example.enomfinal.adapters.PartyGoerProfileAdapters;

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
import com.example.enomfinal.models.Users;
import com.example.enomfinal.storage.SharedPrefManager;

import java.util.List;

public class PartyGoer_PartyGoerFollowedAdapter extends RecyclerView.Adapter<PartyGoer_PartyGoerFollowedAdapter.MyViewHolder> {

    private Context mContext;
    private List<Users> usersList;

    public PartyGoer_PartyGoerFollowedAdapter(Context mContext, List<Users> usersList) {
        this.mContext = mContext;
        this.usersList = usersList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_partygoerprofile_partygoer_followed,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.partygoerPhoto.setImageResource(usersList.get(position).getPhoto());
        String name = usersList.get(position).getE_fname()+" "+usersList.get(position).getE_lname();
        holder.partygoerfName.setText(name);

        String type = SharedPrefManager.getInstance(mContext).getUser().getE_type();
        String t = "Performer";

        if(type.equals(t)){
            holder.buttonfollow.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView partygoerPhoto;
        private TextView partygoerfName;
        private Button buttonfollow;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            buttonfollow = itemView.findViewById(R.id.partyGoerProfile_performerBTNfollow);
            partygoerfName = itemView.findViewById(R.id.partyGoerProfile_partyGoerName);
            partygoerPhoto = itemView.findViewById(R.id.partyGoerProfile_partyGoerfollowed_photo);
        }
    }
}
