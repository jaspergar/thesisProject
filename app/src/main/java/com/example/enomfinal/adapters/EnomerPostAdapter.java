package com.example.enomfinal.adapters;

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
import com.example.enomfinal.interfaces.BarItemClickListener;
import com.example.enomfinal.interfaces.PostItemClickLisener;
import com.example.enomfinal.models.BAR_PERFORMER_Post;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EnomerPostAdapter extends RecyclerView.Adapter<EnomerPostAdapter.MyViewHolder> {
   private Context mContext;
   private List<BAR_PERFORMER_Post> bar_performer_postList;
   PostItemClickLisener postItemClickLisener;


    public EnomerPostAdapter(Context mContext, List<BAR_PERFORMER_Post> bar_performer_postList, PostItemClickLisener postItemClickLisener) {
        this.mContext = mContext;
        this.bar_performer_postList = bar_performer_postList;
        this.postItemClickLisener = postItemClickLisener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.home_fragment_post_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.poserName.setText(bar_performer_postList.get(position).getPerformer_name());
        holder.postDesc.setText(bar_performer_postList.get(position).getPost_description());
        holder.poserType.setText(bar_performer_postList.get(position).getE_type());
        if(bar_performer_postList.get(position).getE_photo() != null && bar_performer_postList.get(position).getE_photo().length() > 0){
            Picasso.get().load(StaticIP.getWifiIP() +bar_performer_postList.get(position).getE_photo()).placeholder(R.drawable.imgplaceholder).into(holder.poserPhoto);
        }else{

            Picasso.get().load(R.drawable.imgplaceholder).into(holder.poserPhoto);
        }

        if(bar_performer_postList.get(position).getPost_media() != null && bar_performer_postList.get(position).getPost_media().length() > 0){
            Picasso.get().load(StaticIP.getWifiIP() +bar_performer_postList.get(position).getPost_media()).placeholder(R.drawable.imgplaceholder).into(holder.postPhoto);
        }else{

            Picasso.get().load(R.drawable.imgplaceholder).into(holder.postPhoto);
        }

        Date d = bar_performer_postList.get(position).getPost_date();
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        String date = df.format(d);
        holder.postDate.setText(date);
    }

    @Override
    public int getItemCount() {
        return bar_performer_postList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView postPhoto,poserPhoto;
        private TextView postDesc,poserName,poserType,postDate;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            postPhoto = itemView.findViewById(R.id.post_image);
            poserPhoto = itemView.findViewById(R.id.post_profile_pic);
            poserName = itemView.findViewById(R.id.post_name);
            postDesc = itemView.findViewById(R.id.post_description);
            poserType =itemView.findViewById(R.id.post_usertype);
            postDate = itemView.findViewById(R.id.post_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    postItemClickLisener.onPostClick(bar_performer_postList.get(getAdapterPosition()),postPhoto);
                }
            });
        }
    }
}
