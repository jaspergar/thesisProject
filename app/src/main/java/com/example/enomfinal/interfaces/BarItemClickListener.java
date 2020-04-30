package com.example.enomfinal.interfaces;

import android.widget.ImageView;

import com.example.enomfinal.models.BARSFINAL;
import com.example.enomfinal.models.Bars;

public interface BarItemClickListener {
    void onBarClick(BARSFINAL barsfinal, ImageView barImageView);
}
