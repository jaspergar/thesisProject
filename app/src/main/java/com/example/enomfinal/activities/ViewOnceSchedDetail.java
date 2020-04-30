package com.example.enomfinal.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.enomfinal.R;
import com.example.enomfinal.api.PerformerRetrofitClient;
import com.example.enomfinal.api.StaticIP;
import com.example.enomfinal.models.DefaultResponse;
import com.example.enomfinal.storage.SharedPrefManager;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewOnceSchedDetail extends AppCompatActivity {

    ImageView barphoto,eventphoto;
    TextView barName,ptype,date,starttime,endtime,description;
    Button apply,pending,occupied,denied,cancel,accepted,accept,reject,scheduled;

    String bname,pt,d,st,et,bp,desc,stat,performertype;
    int performerid,barid,scheddateid,performer_id,theUserWhoNotify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_once_sched_detail);

        barphoto = findViewById(R.id.oncesched_datail_img);
        eventphoto = findViewById(R.id.oncesched_detail_eventphoto);
        barName = findViewById(R.id.oncesched_datail_barname);
        ptype = findViewById(R.id.oncesched_datail_type);
        date = findViewById(R.id.oncesched_datail_date);
        starttime = findViewById(R.id.oncesched_datail_startTime);
        endtime = findViewById(R.id.oncesched_datail_endTime);
        description = findViewById(R.id.oncesched_detail_eventdescription);

        apply = findViewById(R.id.oncesched_detail_btnApply);
        pending = findViewById(R.id.oncesched_detail_btnPending);
        occupied = findViewById(R.id.oncesched_detail_btnOccupied);
        cancel = findViewById(R.id.oncesched_detail_cancelApply);
        accepted = findViewById(R.id.oncesched_detail_btnAccepted);
        accept = findViewById(R.id.oncesched_detail_cancelAccept);
        reject = findViewById(R.id.oncesched_detail_cancelReject);
        scheduled =findViewById(R.id.oncesched_detail_btnScheduled);


        bname = getIntent().getExtras().getString("barname");
        pt = getIntent().getExtras().getString("performancetype");
        d = getIntent().getExtras().getString("date");
        st = getIntent().getExtras().getString("stime");
        et = getIntent().getExtras().getString("etime");
        bp = getIntent().getExtras().getString("bphoto");
        desc = getIntent().getExtras().getString("description");
        stat = getIntent().getExtras().getString("status");
        barid = getIntent().getExtras().getInt("barid");
        scheddateid = getIntent().getExtras().getInt("schedDateId");
        performer_id = getIntent().getExtras().getInt("performerid");
        theUserWhoNotify =getIntent().getExtras().getInt("theUserWhoNotify");

        performerid = SharedPrefManager.getInstance(ViewOnceSchedDetail.this).getUser().getE_id();
        performertype = SharedPrefManager.getInstance(ViewOnceSchedDetail.this).getUser().getE_type();


        barName.setText(bname);
        ptype.setText(pt);
        date.setText(d);
        starttime.setText(st);
        endtime.setText(et);
        description.setText(desc);

        if(bp != null && bp.length() > 0){
            Picasso.get().load(StaticIP.getWifiIP() +bp).placeholder(R.drawable.imgplaceholder).into(barphoto);
        }else{

            Picasso.get().load(R.drawable.imgplaceholder).into(barphoto);
        }

        if(stat.equals("vacant")){
            apply.setVisibility(View.VISIBLE);
        }else if(stat.equals("pending")){
            if(performer_id == performerid){
                pending.setVisibility(View.VISIBLE);
                cancel.setVisibility(View.VISIBLE);
            }else if(performer_id != performerid){
                pending.setVisibility(View.VISIBLE);
                pending.setEnabled(false);
            }
        }else if(stat.equals("accept")){
            if(performer_id == performerid){
                accepted.setVisibility(View.VISIBLE);
            }else if(performer_id != performerid){
                occupied.setVisibility(View.VISIBLE);
            }
        }else if(stat.equals("invited")){
            accept.setVisibility(View.VISIBLE);
            reject.setVisibility(View.VISIBLE);
        }

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Call<DefaultResponse> call6 = PerformerRetrofitClient.getInstance().getApi().accept(barid,performerid,"ns","Accepted your invitation","invite","Performer","bar",scheddateid);
                call6.enqueue(new Callback<DefaultResponse>() {
                    @Override
                    public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                        if(response.code() == 201){
                            scheduled.setVisibility(View.VISIBLE);
                            accept.setVisibility(View.GONE);
                            reject.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<DefaultResponse> call, Throwable t) {

                    }
                });
            }
        });

        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ViewOnceSchedDetail.this,scheddateid+"",Toast.LENGTH_LONG).show();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<DefaultResponse> call2 = PerformerRetrofitClient.getInstance().getApi().cancelApply(barid,performerid,"ns","Canceled Application","apply",performertype,"bar",scheddateid);
                call2.enqueue(new Callback<DefaultResponse>() {
                    @Override
                    public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                        if(response.code() == 201){
                            cancel.setVisibility(View.GONE);
                            pending.setVisibility(View.GONE);
                            apply.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onFailure(Call<DefaultResponse> call, Throwable t) {

                    }
                });
            }
        });

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<DefaultResponse> call = PerformerRetrofitClient.getInstance().getApi().apply(barid,performerid,"ns","Applied to Perform","apply",performertype,"bar",scheddateid);
                call.enqueue(new Callback<DefaultResponse>() {
                    @Override
                    public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                        if(response.code() == 201){
                            apply.setVisibility(View.GONE);
                            pending.setVisibility(View.VISIBLE);
                            cancel.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onFailure(Call<DefaultResponse> call, Throwable t) {

                    }
                });
            }
        });


    }
}
