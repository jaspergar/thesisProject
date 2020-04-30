package com.example.enomfinal.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.enomfinal.R;
import com.example.enomfinal.api.EnomerRetrofitClient;
import com.example.enomfinal.api.StaticIP;
import com.example.enomfinal.interfaces.NotificationItemClickListener;
import com.example.enomfinal.models.DefaultResponse;
import com.example.enomfinal.models.Notification;
import com.example.enomfinal.storage.SharedPrefManager;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {
    private Context mContex;
    private List<Notification> notificationList;
    private NotificationItemClickListener notificationItemClickListener;

    View view;

    public NotificationAdapter(Context mContex, List<Notification> notificationList, NotificationItemClickListener notificationItemClickListener) {
        this.mContex = mContex;
        this.notificationList = notificationList;
        this.notificationItemClickListener = notificationItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        String follow = "follow";
        String added = "invite";
        String notif_type = notificationList.get(viewType).getNotif_type();

             view = LayoutInflater.from(mContex).inflate(R.layout.notification_fragment_item_follow,parent,false);



        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        String follow = "follow";
        String added = "invite";
        int TheUser = SharedPrefManager.getInstance(mContex).getUser().getE_id();
        int thefollower = notificationList.get(position).getEnomer_id();
        if(notificationList.get(position).getNotif_type().equals(follow)) {
            holder.fname.setText(notificationList.get(position).getE_fname()+" "+notificationList.get(position).getE_lname());
            holder.content.setText(notificationList.get(position).getContent());
            if (notificationList.get(position).getE_photo() != null && notificationList.get(position).getE_photo().length() > 0) {
                Picasso.get().load(StaticIP.getWifiIP() + notificationList.get(position).getE_photo()).placeholder(R.drawable.imgplaceholder).into(holder.notifPhoto);
            } else {

                Picasso.get().load(R.drawable.imgplaceholder).into(holder.notifPhoto);
            }

            Call<DefaultResponse> call = EnomerRetrofitClient.getInstance().getApi().isSeen(TheUser,thefollower);
            call.enqueue(new Callback<DefaultResponse>() {
                @Override
                public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                    if(response.code() == 201){
                        holder.constraintLayout_follow.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    }
                }

                @Override
                public void onFailure(Call<DefaultResponse> call, Throwable t) {

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        int data = 0 ;

        if(notificationList != null){
            return  data = notificationList.size();
        }else if(notificationList == null){
          return   data = 0;
        }

        return data;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView notifPhoto,barphoto_invited;
        private TextView fname,lname,content,barcontent_invited,barname_invited;
        private ConstraintLayout constraintLayout_follow,barconstraint_invited;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
           constraintLayout_follow = itemView.findViewById(R.id.notif_background);
           fname = itemView.findViewById(R.id.notif_fname);
           content = itemView.findViewById(R.id.notif_content);
            notifPhoto = itemView.findViewById(R.id.notif_photo);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    constraintLayout_follow.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    notificationItemClickListener.onNotificationClick(notificationList.get(getAdapterPosition()),notifPhoto);
                }
            });
        }
    }
}
