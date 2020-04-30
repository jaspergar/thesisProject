package com.example.enomfinal.adapters.BarProfileAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.enomfinal.R;
import com.example.enomfinal.api.StaticIP;
import com.example.enomfinal.models.Offer;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BarPOfferSliderAdapter extends PagerAdapter {

    Context mContext;
    List<Offer> offerlist;

    public BarPOfferSliderAdapter(Context mContext, List<Offer> offerlist) {
        this.mContext = mContext;
        this.offerlist = offerlist;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View slideLayout = inflater.inflate(R.layout.barprofile_offer_slide_item,null);

        ImageView offerSlideimg = slideLayout.findViewById(R.id.barp_slide_item_photo);
        TextView offerprice = slideLayout.findViewById(R.id.barp_slide_price);
        TextView offername = slideLayout.findViewById(R.id.barp_slide_offername);

        int price = offerlist.get(position).getOffer_price();
        String p = Integer.toString(price);
        offerprice.setText(p);
        offername.setText(offerlist.get(position).getOffer_name());

        if(offerlist.get(position).getPhoto() != null && offerlist.get(position).getPhoto().length() > 0){
            Picasso.get().load(StaticIP.getWifiIP() +offerlist.get(position).getPhoto()).placeholder(R.drawable.imgplaceholder).into(offerSlideimg);
        }else{

            Picasso.get().load(R.drawable.imgplaceholder).into(offerSlideimg);
        }

        container.addView(slideLayout);
        return slideLayout;
    }

    @Override
    public int getCount() {
        return offerlist.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
