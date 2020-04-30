package com.example.enomfinal.adapters.performerprofileAdpters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.enomfinal.R;
import com.example.enomfinal.adapters.BarProfileAdapters.EventAdapter;
import com.example.enomfinal.models.EventModel;

import java.util.List;

public class PerformerProfile_EventAdapter extends RecyclerView.Adapter<PerformerProfile_EventAdapter.MyViewHolder>{
    private Context mContext;
    private List<EventModel> eventModelList;

    public PerformerProfile_EventAdapter(Context mContext, List<EventModel> eventModelList) {
        this.mContext = mContext;
        this.eventModelList = eventModelList;
    }

    @NonNull
    @Override
    public PerformerProfile_EventAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.performerp_event_item,parent,false);
        return new PerformerProfile_EventAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PerformerProfile_EventAdapter.MyViewHolder holder, int position) {
        holder.event_photo.setImageResource(eventModelList.get(position).getEvent_photo());

    }

    @Override
    public int getItemCount() {
        return eventModelList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView event_photo;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            event_photo = itemView.findViewById(R.id.performerp_event_photo_id);
        }
    }
}
