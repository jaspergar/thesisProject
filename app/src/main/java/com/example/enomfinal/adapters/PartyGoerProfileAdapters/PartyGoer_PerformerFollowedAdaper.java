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
import com.example.enomfinal.models.PerfomerProfile_JoinedTable;
import com.example.enomfinal.storage.SharedPrefManager;

import java.util.List;

public class PartyGoer_PerformerFollowedAdaper extends RecyclerView.Adapter<PartyGoer_PerformerFollowedAdaper.MyViewHolder> {

    private Context mContext;
    private List<PerfomerProfile_JoinedTable> perfomerProfile_joinedTableList;

    public PartyGoer_PerformerFollowedAdaper(Context mContext, List<PerfomerProfile_JoinedTable> perfomerProfile_joinedTableList) {
        this.mContext = mContext;
        this.perfomerProfile_joinedTableList = perfomerProfile_joinedTableList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_partygoerprofile_perfomer_followed,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.performerName.setText(perfomerProfile_joinedTableList.get(position).getPerformer_name());
        holder.performerPhoto.setImageResource(perfomerProfile_joinedTableList.get(position).getPerformer_photo());


        String type = SharedPrefManager.getInstance(mContext).getUser().getE_type();
        String t = "Performer";

        if(type.equals(t)){
            holder.follow.setVisibility(View.GONE);
            holder.unfollow.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return perfomerProfile_joinedTableList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView performerPhoto;
        private TextView performerName;
        private Button follow,unfollow;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            follow = itemView.findViewById(R.id.partyGoerProfile_performerBTNfollow);
            unfollow = itemView.findViewById(R.id.partyGoerProfile_performerBTNunfollow);

            performerPhoto = itemView.findViewById(R.id.partyGoerProfile_performerfollowed_photo);
            performerName = itemView.findViewById(R.id.partyGoerProfile_performerName);
        }
    }
}
