package com.example.enomfinal.interfaces;

import android.widget.ImageView;

import com.example.enomfinal.models.BarGigs;
import com.example.enomfinal.models.MyInvites;

public interface InviteItemClickListener {
    void onBarClick(MyInvites myInvites, ImageView barImageView);
}
