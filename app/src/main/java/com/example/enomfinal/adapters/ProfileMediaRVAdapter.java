package com.example.enomfinal.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.enomfinal.R;
import com.example.enomfinal.api.StaticIP;
import com.example.enomfinal.interfaces.PostItemClickLisener;
import com.example.enomfinal.models.BAR_PERFORMER_Post;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProfileMediaRVAdapter extends RecyclerView.Adapter<ProfileMediaRVAdapter.ImageViewHolder> {

    Context mContext;
    List<BAR_PERFORMER_Post> BARPERFORMERPostListMedia;
    PostItemClickLisener postItemClickLisener;


    public ProfileMediaRVAdapter(Context mContext, List<BAR_PERFORMER_Post> BARPERFORMERPostListMedia, PostItemClickLisener postItemClickLisener) {
        this.mContext = mContext;
        this.BARPERFORMERPostListMedia = BARPERFORMERPostListMedia;
        this.postItemClickLisener = postItemClickLisener;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.barprofile_media_item_rv,parent,false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        if(BARPERFORMERPostListMedia.get(position).getPost_media() != null && BARPERFORMERPostListMedia.get(position).getPost_media().length() > 0){
            Picasso.get().load(StaticIP.getWifiIP() +BARPERFORMERPostListMedia.get(position).getPost_media()).placeholder(R.drawable.imgplaceholder).into(holder.postPhoto);
        }else{

            Picasso.get().load(R.drawable.imgplaceholder).into(holder.postPhoto);
        }
    }

    @Override
    public int getItemCount() {
        return BARPERFORMERPostListMedia.size();
    }


    public class ImageViewHolder extends RecyclerView.ViewHolder {

        ImageView postPhoto;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            postPhoto = itemView.findViewById(R.id.barprofile_media_item_row_id);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    postItemClickLisener.onPostClick(BARPERFORMERPostListMedia.get(getAdapterPosition()),postPhoto);
                }
            });
        }
    }
}
