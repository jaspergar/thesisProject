package com.example.enomfinal.adapters.PerformerActivityAdapters;

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
import com.example.enomfinal.interfaces.BarItemClickListener;
import com.example.enomfinal.interfaces.GigsItemClickListener;
import com.example.enomfinal.models.BarGigs;
import com.example.enomfinal.storage.SharedPrefManager;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GigsAdapter extends RecyclerView.Adapter<GigsAdapter.MyViewHolder> {

    private Context mContex;
    private List<BarGigs> barGigslist;
    private GigsItemClickListener gigsItemClickListener;

    public GigsAdapter(Context mContex, List<BarGigs> barGigslist, GigsItemClickListener gigsItemClickListener) {
        this.mContex = mContex;
        this.barGigslist = barGigslist;
        this.gigsItemClickListener = gigsItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContex).inflate(R.layout.gigs_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.barname.setText(barGigslist.get(position).getBar_name());
        holder.scheduleType.setText(barGigslist.get(position).getPerformance_type());
        holder.date.setText(barGigslist.get(position).getOnce_schedule());
        holder.timeStart.setText(barGigslist.get(position).getStart_time());
        holder.endTime.setText(barGigslist.get(position).getEnd_time());

        if(barGigslist.get(position).getBar_photo() != null && barGigslist.get(position).getBar_photo().length() > 0){
            Picasso.get().load(StaticIP.getWifiIP() +barGigslist.get(position).getBar_photo()).placeholder(R.drawable.imgplaceholder).into(holder.barimage);
        }else{

            Picasso.get().load(R.drawable.imgplaceholder).into(holder.barimage);
        }

        int Theuser = SharedPrefManager.getInstance(mContex).getUser().getE_id();
        int theAcceptedPerformer = barGigslist.get(position).getPerformer_id();

        int theUserwhoNotify = barGigslist.get(position).getEnomer_id();

        String vacant = "vacant";
        String pending = "pending";
        String denied = "denied";
        String occupied = "occupied";
        String accepted = "accept";
       String status = barGigslist.get(position).getStatus();
        if(status.equals(vacant)){
            holder.v.setVisibility(View.VISIBLE);
        }else if(status.equals(pending)){
                holder.p.setVisibility(View.VISIBLE);

        }else if(status.equals(denied)){
            if(Theuser != theAcceptedPerformer) {
                holder.v.setVisibility(View.VISIBLE);
            }else if(Theuser == theAcceptedPerformer){
                holder.d.setVisibility(View.VISIBLE);
            }
        }else if(status.equals(accepted)){
            if(Theuser != theAcceptedPerformer){
            holder.o.setVisibility(View.VISIBLE);
            }else if(Theuser == theAcceptedPerformer){
                holder.a.setVisibility(View.VISIBLE);
            }
        }else if(status.equals("set")){
            if(Theuser != theAcceptedPerformer){
                holder.o.setVisibility(View.VISIBLE);
            }else if(Theuser == theAcceptedPerformer){
                holder.s.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return barGigslist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView barimage;
        private TextView barname,scheduleType,date,timeStart,endTime;
        private CardView v,p,d,o,a,i,s;

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
            s = itemView.findViewById(R.id.gigs_oncesched_set);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gigsItemClickListener.onBarClick(barGigslist.get(getAdapterPosition()),barimage);
                }
            });
        }
    }
}
