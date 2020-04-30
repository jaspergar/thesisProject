package com.example.enomfinal.interfaces;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.enomfinal.models.Bars;
import com.example.enomfinal.models.PERFORMERFINAL;
import com.example.enomfinal.models.PerfomerProfile_JoinedTable;

public interface PerformerItemClickListener {
    void onPerfomerClick(PERFORMERFINAL performerfinal, ImageView performerImageView, TextView performerName);
}
