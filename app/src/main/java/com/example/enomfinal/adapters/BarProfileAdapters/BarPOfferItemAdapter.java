package com.example.enomfinal.adapters.BarProfileAdapters;

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
import com.example.enomfinal.models.Offer;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class BarPOfferItemAdapter extends RecyclerView.Adapter<BarPOfferItemAdapter.MyViewHolder> {

    Context mContext;
    List <Offer> offerList;

    public BarPOfferItemAdapter(Context mContext, List<Offer> offerList) {
        this.mContext = mContext;
        this.offerList = offerList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.barp_offer_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int price = offerList.get(position).getOffer_price();
        String p = Integer.toString(price);
        holder.baritemprice.setText(p);
        holder.baritemname.setText(offerList.get(position).getOffer_name());
        if(offerList.get(position).getPhoto() != null && offerList.get(position).getPhoto().length() > 0){
            Picasso.get().load(StaticIP.getWifiIP() +offerList.get(position).getPhoto()).placeholder(R.drawable.imgplaceholder).into(holder.itemphoto);
        }else{

            Picasso.get().load(R.drawable.imgplaceholder).into(holder.itemphoto);
        }
    }

    @Override
    public int getItemCount() {
        return offerList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView itemphoto;
        private TextView baritemname,baritemprice;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemphoto = itemView.findViewById(R.id.offers_item_img_id);
            baritemname = itemView.findViewById(R.id.offers_item_name_id);
            baritemprice = itemView.findViewById(R.id.offer_item_price_id);

        }
    }
}
