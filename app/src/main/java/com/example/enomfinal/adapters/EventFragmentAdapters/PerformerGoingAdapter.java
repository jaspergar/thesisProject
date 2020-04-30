package com.example.enomfinal.adapters.EventFragmentAdapters;

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
import com.example.enomfinal.models.EventModel;
import com.example.enomfinal.models.FinalPerformerSchedNotif;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PerformerGoingAdapter extends RecyclerView.Adapter<PerformerGoingAdapter.MyViewHolder> {
    private Context mContext;
    private List<FinalPerformerSchedNotif> eventModelList;


    public PerformerGoingAdapter(Context mContext, List<FinalPerformerSchedNotif> eventModelList) {
        this.mContext = mContext;
        this.eventModelList = eventModelList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.performerevent_going_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.eventendtime.setText(eventModelList.get(position).getEnd_time());
            holder.eventstartt.setText(eventModelList.get(position).getStart_time());
            holder.ptype.setText(eventModelList.get(position).getPerformer_type());
            holder.pname.setText(eventModelList.get(position).getPerformer_name());
            holder.pcategory.setText(eventModelList.get(position).getPerformer_category());

            String once_schedule = eventModelList.get(position).getOnce_schedule();
            String weekly_schedule = eventModelList.get(position).getWeekly_schedule();

            if(once_schedule != null) {
                holder.eventdate.setText(eventModelList.get(position).getOnce_schedule());
            }else if(weekly_schedule != null){
                holder.eventdate.setText("Every"+" "+eventModelList.get(position).getWeekly_schedule());
            }else{
                holder.eventdate.setText("No Date");
            }

            holder.eventBarname.setText("@"+" "+eventModelList.get(position).getBar_name());
            holder.eventname.setText(eventModelList.get(position).getEvent_name());

        if(eventModelList.get(position).getE_photo() != null && eventModelList.get(position).getE_photo().length() > 0){
            Picasso.get().load(StaticIP.getWifiIP() +eventModelList.get(position).getE_photo()).placeholder(R.drawable.imgplaceholder).into(holder.performereventgoingphoto);
        }else{

            Picasso.get().load(R.drawable.imgplaceholder).into(holder.performereventgoingphoto);
        }

        if(eventModelList.get(position).getBar_photo() != null && eventModelList.get(position).getBar_photo().length() > 0){
            Picasso.get().load(StaticIP.getWifiIP() +eventModelList.get(position).getBar_photo()).placeholder(R.drawable.imgplaceholder).into(holder.barphoto);
        }else{

            Picasso.get().load(R.drawable.imgplaceholder).into(holder.barphoto);
        }

    }

    @Override
    public int getItemCount() {
        return eventModelList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView performereventgoingphoto,barphoto;
        private TextView eventname,eventdate,eventstartt,eventendtime,eventBarname,pname,pcategory,ptype;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            barphoto = itemView.findViewById(R.id.performerevent_bphoto_id);
            pname = itemView.findViewById(R.id.performerevent_pname_id);
            pcategory= itemView.findViewById(R.id.performerevent_pcategory_id);
            ptype = itemView.findViewById(R.id.performerevent_ptype_id);
            performereventgoingphoto = itemView.findViewById(R.id.performerevent_photo_id);
            eventname = itemView.findViewById(R.id.performerevent_name_id);
            eventBarname = itemView.findViewById(R.id.performerevent_barlocation);
            eventdate = itemView.findViewById(R.id.performerevent_date_id);
            eventstartt = itemView.findViewById(R.id.performerevent_startTime_id);
            eventendtime = itemView.findViewById(R.id.performerevent_endTime);



        }
    }
}
