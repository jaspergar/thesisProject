package com.example.enomfinal.interfaces;

import android.widget.ImageView;

import com.example.enomfinal.models.BARSFINAL;
import com.example.enomfinal.models.Notification;

public interface NotificationItemClickListener {

    void onNotificationClick(Notification notification, ImageView notificationImageView);
}
