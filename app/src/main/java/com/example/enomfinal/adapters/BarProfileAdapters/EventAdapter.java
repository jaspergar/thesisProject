package com.example.enomfinal.adapters.BarProfileAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.enomfinal.R;
import com.example.enomfinal.interfaces.EventItemClickListener;
import com.example.enomfinal.models.EventModel;
import com.example.enomfinal.storage.SharedPrefManager;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {

   private Context mContext;
    private List<EventModel> eventModelList;
    private EventItemClickListener eventItemClickListener;
    private String enomerType;

    View view;

//    public EventAdapter(Context mContext, List<EventModel> eventModelList, EventItemClickListener eventItemClickListener, String enomerType) {
//        this.mContext = mContext;
//        this.eventModelList = eventModelList;
//        this.eventItemClickListener = eventItemClickListener;
//        this.enomerType = enomerType;
//    }

    public EventAdapter(Context mContext, List<EventModel> eventModelList, EventItemClickListener eventItemClickListener) {
        this.mContext = mContext;
        this.eventModelList = eventModelList;
        this.eventItemClickListener = eventItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        String typePerformer = "Performer";
        String typePartyGoer = "Party-Goer";
        enomerType = SharedPrefManager.getInstance(mContext).getUser().getE_type();
        if(enomerType.equals(typePartyGoer)){
             view = LayoutInflater.from(mContext).inflate(R.layout.barp_event_item,parent,false);
//            return new MyViewHolder(view);
        }else if(enomerType.equals(typePerformer)){
             view = LayoutInflater.from(mContext).inflate(R.layout.barp_event_item_ifperformer,parent,false);
//            return new MyViewHolder(view);
        }
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

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

            String typePerformer = "Performer";
            String typePartyGoer = "Party-Goer";
            enomerType = SharedPrefManager.getInstance(mContext).getUser().getE_type();
if(enomerType.equals(typePartyGoer)){
    event_photo = itemView.findViewById(R.id.barp_event_photo_id);

    itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            eventItemClickListener.onPartyGoerClick(eventModelList.get(getAdapterPosition()),event_photo);
        }
    });
}else if(enomerType.equals(typePerformer)){
    event_photo = itemView.findViewById(R.id.barp_event_photo_id_ifperformer);
}

        }
    }
}
