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
import com.example.enomfinal.interfaces.GigsItemClickListener;
import com.example.enomfinal.models.BarGigs;
import com.squareup.picasso.Picasso;

import java.util.List;

public class WeeklyGigsAdapter extends RecyclerView.Adapter<WeeklyGigsAdapter.MyViewHolder> {
    private Context mContext;
    private List<BarGigs> barGigs;
    private GigsItemClickListener gigsItemClickListener;




    public WeeklyGigsAdapter(Context mContext, List<BarGigs> barGigs, GigsItemClickListener gigsItemClickListener) {
        this.mContext = mContext;
        this.barGigs = barGigs;
        this.gigsItemClickListener = gigsItemClickListener;
    }

    View view;
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(mContext).inflate(R.layout.gigs_weekly_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.barname.setText(barGigs.get(position).getBar_name());
        if(barGigs.get(position).getBar_photo() != null && barGigs.get(position).getBar_photo().length() > 0){
            Picasso.get().load(StaticIP.getWifiIP() +barGigs.get(position).getBar_photo()).placeholder(R.drawable.imgplaceholder).into(holder.barimage);
        }else{

            Picasso.get().load(R.drawable.imgplaceholder).into(holder.barimage);
        }
//        holder.barimage.setImageResource(barGigs.get(position).getBarImage());
    }

    @Override
    public int getItemCount() {
        return barGigs.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView barimage;
        private TextView barname;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);



            barname = itemView.findViewById(R.id.performeract_weeklysched_name);

            barimage = itemView.findViewById(R.id.performeract_weeklysched_profilepic);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gigsItemClickListener.onBarClick(barGigs.get(getAdapterPosition()),barimage);
                }
            });
        }
    }
}
