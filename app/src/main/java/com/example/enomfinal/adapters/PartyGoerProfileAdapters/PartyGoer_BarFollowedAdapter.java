package com.example.enomfinal.adapters.PartyGoerProfileAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.enomfinal.R;
import com.example.enomfinal.interfaces.BarItemClickListener;
import com.example.enomfinal.models.Bars;

import java.util.List;

public class PartyGoer_BarFollowedAdapter extends RecyclerView.Adapter<PartyGoer_BarFollowedAdapter.MyViewHolder> {

    private Context mContext;
    private List<Bars> barsList;
    BarItemClickListener barItemClickListener;

    public PartyGoer_BarFollowedAdapter(Context mContext, List<Bars> barsList) {
        this.mContext = mContext;
        this.barsList = barsList;
    }

    public PartyGoer_BarFollowedAdapter(Context mContext, List<Bars> barsList, BarItemClickListener barItemClickListener) {
        this.mContext = mContext;
        this.barsList = barsList;
        this.barItemClickListener = barItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_bar,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    holder.barName.setText(barsList.get(position).getBar_name());
    holder.barPhoto.setImageResource(barsList.get(position).getPgprofile_bfollowedPhoto());
    }

    @Override
    public int getItemCount() {
        return barsList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
      private ImageView barPhoto;
      private TextView barName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            barPhoto = itemView.findViewById(R.id.suggested_barImage_id);
            barName = itemView.findViewById(R.id.suggested_barName_id);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    barItemClickListener.onBarClick(barsList.get(getAdapterPosition()),barPhoto);
//                }
//            });
        }
    }
}
