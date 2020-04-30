package com.example.enomfinal.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.enomfinal.R;
import com.example.enomfinal.api.StaticIP;
import com.example.enomfinal.interfaces.InviteItemClickListener;
import com.example.enomfinal.models.MyInvites;
import com.example.enomfinal.storage.SharedPrefManager;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyInvitesAdapter extends RecyclerView.Adapter<MyInvitesAdapter.MyViewHolder> {
    private Context mcontext;
    private List<MyInvites> myInvitesList;
    private InviteItemClickListener inviteItemClickListener;

    public MyInvitesAdapter(Context mcontext, List<MyInvites> myInvitesList, InviteItemClickListener inviteItemClickListener) {
        this.mcontext = mcontext;
        this.myInvitesList = myInvitesList;
        this.inviteItemClickListener = inviteItemClickListener;
    }

    View view;
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(mcontext).inflate(R.layout.gigs_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.barname.setText(myInvitesList.get(position).getBar_name());
            holder.scheduleType.setText(myInvitesList.get(position).getPerformance_type());

        String once_schedule = myInvitesList.get(position).getOnce_schedule();
        String weekly_schedule = myInvitesList.get(position).getWeekly_schedule();

        if(once_schedule != null) {
            holder.date.setText(myInvitesList.get(position).getOnce_schedule());
        }else if(weekly_schedule != null){
            holder.date.setText("Every"+" "+myInvitesList.get(position).getWeekly_schedule());
        }else{
            holder.date.setText("No Date");
        }
            holder.timeStart.setText(myInvitesList.get(position).getStart_time());
            holder.endTime.setText(myInvitesList.get(position).getEnd_time());

        if(myInvitesList.get(position).getBar_photo() != null && myInvitesList.get(position).getBar_photo().length() > 0){
            Picasso.get().load(StaticIP.getWifiIP() +myInvitesList.get(position).getBar_photo()).placeholder(R.drawable.imgplaceholder).into(holder.barimage);
        }else{

            Picasso.get().load(R.drawable.imgplaceholder).into(holder.barimage);
        }

        int TheUser = SharedPrefManager.getInstance(mcontext).getUser().getE_id();
        int TneInvited = myInvitesList.get(position).getNotif_to();

        if(myInvitesList.get(position).getNotif_type().equals("invite")){
            if(TheUser == TneInvited ) {
                holder.i.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return myInvitesList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView barimage;
        private TextView barname,scheduleType,date,timeStart,endTime;
        private CardView v,p,d,o,a,i;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            barimage = itemView.findViewById(R.id.performeract_oncesched_profilepic);
            barname = itemView.findViewById(R.id.performeract_oncesched_name);
            scheduleType = itemView.findViewById(R.id.performeract_onceshed_type);
            date = itemView.findViewById(R.id.performeract_oncesched_date);
            timeStart = itemView.findViewById(R.id.performeract_onceshed_starttime);
            endTime = itemView.findViewById(R.id.performeract_onceshed_endTime);

            v = itemView.findViewById(R.id.gigs_oncesched_vacant);
            p = itemView.findViewById(R.id.gigs_oncesched_pending);
            d = itemView.findViewById(R.id.gigs_oncesched_denied);
            o = itemView.findViewById(R.id.gigs_oncesched_occupied);
            a = itemView.findViewById(R.id.gigs_oncesched_accepted);
            i = itemView.findViewById(R.id.gigs_oncesched_invited);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    inviteItemClickListener.onBarClick(myInvitesList.get(getAdapterPosition()),barimage);
                }
            });
        }
    }
}
