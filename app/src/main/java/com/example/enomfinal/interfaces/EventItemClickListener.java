package com.example.enomfinal.interfaces;

import android.widget.ImageView;

import com.example.enomfinal.models.EventModel;
import com.example.enomfinal.models.Users;

public interface EventItemClickListener {
    void onPartyGoerClick(EventModel eventModel, ImageView EventImageView);
}
