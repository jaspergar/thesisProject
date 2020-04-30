package com.example.enomfinal.adapters.PerformerActivityAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.enomfinal.R;
import com.example.enomfinal.api.PerformerRetrofitClient;
import com.example.enomfinal.models.BarGigs;
import com.example.enomfinal.models.DefaultResponse;
import com.example.enomfinal.storage.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeeklyGigsBarTimeAdapter extends RecyclerView.Adapter<WeeklyGigsBarTimeAdapter.MyViewHolder>{
    private Context mContext;
    private List<BarGigs> barGigs;

    public WeeklyGigsBarTimeAdapter(Context mContext, List<BarGigs> barGigs) {
        this.mContext = mContext;
        this.barGigs = barGigs;
    }
View view;
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         view = LayoutInflater.from(mContext).inflate(R.layout.gigs_weekly_time_schedule_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final int barid = barGigs.get(position).getBar_id();
        final int performerid = SharedPrefManager.getInstance(mContext).getUser().getE_id();
        final String performertype = SharedPrefManager.getInstance(mContext).getUser().getE_type();
        final int scheddateid = barGigs.get(position).getSched_date_id();

        final int performer_id = barGigs.get(position).getPerformer_id();


               holder.starttime.setText(barGigs.get(position).getStart_time());
               holder.endtime.setText(barGigs.get(position).getEnd_time());
               holder.shotype.setText(barGigs.get(position).getPerformance_type());
        final String vacant = "vacant";
        final String pending = "pending";
        String denied = "denied";
        String occupied = "occupied";
        String accepted = "accept";
        String status = barGigs.get(position).getStatus();
        if(status.equals(vacant)){
            holder.vacant.setVisibility(View.VISIBLE);
        }else if(status.equals(pending)){
            if(performer_id == performerid){
                holder.pending.setVisibility(View.VISIBLE);
//            cancel.setVisibility(View.VISIBLE);
            }else if(performer_id != performerid){
                holder.pending.setVisibility(View.VISIBLE);
                holder.pending.setEnabled(false);
            }
        }else if(status.equals(denied)){
            if(performer_id == performerid){
                holder.denied.setVisibility(View.VISIBLE);
            }else if(performer_id != performerid){
                holder.vacant.setVisibility(View.VISIBLE);
            }
        }else if(status.equals(accepted)){
            if(performer_id == performerid){
                holder.accepted.setVisibility(View.VISIBLE);
            }else if(performer_id != performerid){
                holder.occupied.setVisibility(View.VISIBLE);
            }
        }





        holder.vacant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<DefaultResponse> call = PerformerRetrofitClient.getInstance().getApi().apply(barid,performerid,"ns","Applied to Perform","apply",performertype,"bar",scheddateid);
                call.enqueue(new Callback<DefaultResponse>() {
                    @Override
                    public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                        if(response.code() == 201){
                           holder.vacant.setVisibility(View.GONE);
                            holder.pending.setVisibility(View.VISIBLE);
//                            cancel.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onFailure(Call<DefaultResponse> call, Throwable t) {

                    }
                });
            }
        });




        holder.pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<DefaultResponse> call2 = PerformerRetrofitClient.getInstance().getApi().cancelApply(barid,performerid,"ns","Canceled Application","apply",performertype,"bar",scheddateid);
                call2.enqueue(new Callback<DefaultResponse>() {
                    @Override
                    public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                        if(response.code() == 201){
                            holder.pending.setVisibility(View.GONE);
                            holder.vacant.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onFailure(Call<DefaultResponse> call, Throwable t) {

                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        int data = 0;
        if(barGigs != null) {
            return data = barGigs.size();
        }else if(barGigs == null){
            return data = 0;
        }
        return data;

    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView starttime,endtime,shotype;
        private Button vacant,pending,denied,occupied,accepted;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            starttime = itemView.findViewById(R.id.weeklyStartTime);
            endtime = itemView.findViewById(R.id.weeklyEndTime);
            shotype = itemView.findViewById(R.id.weeklyshowtype);

            vacant = itemView.findViewById(R.id.weeklybtnApply);
            pending = itemView.findViewById(R.id.weeklybtnPending);
            denied = itemView.findViewById(R.id.weeklybtnDenied);
            occupied = itemView.findViewById(R.id.weeklybtnOccupied);
            accepted = itemView.findViewById(R.id.weeklybtnAccepted);


        }
    }
}
