package com.example.enomfinal.adapters.EventFragmentAdapters;

import android.content.Context;
import android.media.Rating;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.enomfinal.R;
import com.example.enomfinal.api.StaticIP;
import com.example.enomfinal.models.EventModel;
import com.example.enomfinal.models.FinalBarSchedNotif;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BarGoingAdapter extends RecyclerView.Adapter<BarGoingAdapter.MyViewHolder> {
    private Context mContext;
    private List<FinalBarSchedNotif> eventModelList;

    public BarGoingAdapter(Context mContext, List<FinalBarSchedNotif> eventModelList) {
        this.mContext = mContext;
        this.eventModelList = eventModelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.barevent_going_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
      holder.pname.setText(eventModelList.get(position).getPerformer_name());
      holder.pcategory.setText(eventModelList.get(position).getPerformer_category());
      holder.ptype.setText(eventModelList.get(position).getPerformer_type());
      holder.pscore.setRating(eventModelList.get(position).getPerformer_rating());
      holder.barname.setText("@ "+eventModelList.get(position).getBar_name());
      holder.eventname.setText(eventModelList.get(position).getSched_name());

        String once_schedule = eventModelList.get(position).getOnce_schedule();
        String weekly_schedule = eventModelList.get(position).getWeekly_schedule();

        if(once_schedule != null) {
            holder.eventdate.setText(eventModelList.get(position).getOnce_schedule());
        }else if(weekly_schedule != null){
            holder.eventdate.setText("Every"+" "+eventModelList.get(position).getWeekly_schedule());
        }else{
            holder.eventdate.setText("No Date");
        }

        holder.eventstime.setText(eventModelList.get(position).getStart_time());
        holder.eventetime.setText(eventModelList.get(position).getEnd_time());

        if(eventModelList.get(position).getE_photo() != null && eventModelList.get(position).getE_photo().length() > 0){
            Picasso.get().load(StaticIP.getWifiIP() +eventModelList.get(position).getE_photo()).placeholder(R.drawable.imgplaceholder).into(holder.pphoto);
        }else{

            Picasso.get().load(R.drawable.imgplaceholder).into(holder.pphoto);
        }

        if(eventModelList.get(position).getBar_photo() != null && eventModelList.get(position).getBar_photo().length() > 0){
            Picasso.get().load(StaticIP.getWifiIP() +eventModelList.get(position).getBar_photo()).placeholder(R.drawable.imgplaceholder).into(holder.bareventgoingphoto);
        }else{

            Picasso.get().load(R.drawable.imgplaceholder).into(holder.bareventgoingphoto);
        }



    }

    @Override
    public int getItemCount() {
        int data = 0;
        if(eventModelList != null) {
            return data = eventModelList.size();
        }else if(eventModelList == null){
            return data = 0;
        }
        return data;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView bareventgoingphoto,pphoto;
        private TextView eventname,eventdate,eventstime,eventetime,barname,pname,ptype,pcategory;
        private RatingBar pscore;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bareventgoingphoto = itemView.findViewById(R.id.barevent_photo_id);
            pphoto = itemView.findViewById(R.id.barevent_pphoto_id);
            eventname = itemView.findViewById(R.id.barevent_name_id);
            eventdate = itemView.findViewById(R.id.barevent_date_id);
            eventstime = itemView.findViewById(R.id.barevent_startTime_id);
            eventetime = itemView.findViewById(R.id.barevent_endTime);
            barname = itemView.findViewById(R.id.barevent_barname_id);
            pname = itemView.findViewById(R.id.barevent_pname_id);
            ptype = itemView.findViewById(R.id.barevent_ptype_id);
            pcategory = itemView.findViewById(R.id.barevent_pcategory_id);
            pscore = itemView.findViewById(R.id.barevent_prating_id);

        }
    }
}
