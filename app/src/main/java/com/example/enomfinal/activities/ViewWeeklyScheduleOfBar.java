package com.example.enomfinal.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.enomfinal.R;
import com.example.enomfinal.adapters.PerformerActivityAdapters.WeeklyGigsAdapter;
import com.example.enomfinal.adapters.PerformerActivityAdapters.WeeklyGigsBarTimeAdapter;
import com.example.enomfinal.api.PerformerRetrofitClient;
import com.example.enomfinal.models.BarGigs;
import com.example.enomfinal.storage.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewWeeklyScheduleOfBar extends AppCompatActivity {
WeeklyGigsBarTimeAdapter weeklyGigsAdapter;
List<BarGigs> barGigs;
RecyclerView monday,tuesday,wedsnday,thursday,friday,saturday,sunday;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_weekly_schedule_of_bar);

        monday = findViewById(R.id.viewWeeklySchedMonday_RV);
        tuesday = findViewById(R.id.viewWeeklySchedTuesday_RV);
        wedsnday = findViewById(R.id.viewWeeklySchedWedsnday_RV);
        thursday = findViewById(R.id.viewWeeklySchedThursday_RV);
        friday = findViewById(R.id.viewWeeklySchedFriday_RV);
        saturday = findViewById(R.id.viewWeeklySchedSaturday_RV);
        sunday = findViewById(R.id.viewWeeklySchedSunday_RV);

        String ws = getIntent().getExtras().getString("weeklysched");
        int pid = SharedPrefManager.getInstance(ViewWeeklyScheduleOfBar.this).getUser().getE_id();

        Call<List<BarGigs>> call = PerformerRetrofitClient.getInstance().getApi().getWeeklyScheduleBarTime(pid);
        call.enqueue(new Callback<List<BarGigs>>() {
            @Override
            public void onResponse(Call<List<BarGigs>> call, Response<List<BarGigs>> response) {
                barGigs = response.body();
                weeklyGigsAdapter = new WeeklyGigsBarTimeAdapter(ViewWeeklyScheduleOfBar.this,barGigs);
                monday.setAdapter(weeklyGigsAdapter);
                monday.setLayoutManager(new LinearLayoutManager(ViewWeeklyScheduleOfBar.this));
            }

            @Override
            public void onFailure(Call<List<BarGigs>> call, Throwable t) {

            }
        });

        Call<List<BarGigs>> call1 = PerformerRetrofitClient.getInstance().getApi().getWeeklyScheduleBarTimeT(pid);
        call1.enqueue(new Callback<List<BarGigs>>() {
            @Override
            public void onResponse(Call<List<BarGigs>> call, Response<List<BarGigs>> response) {
                barGigs = response.body();
                weeklyGigsAdapter = new WeeklyGigsBarTimeAdapter(ViewWeeklyScheduleOfBar.this,barGigs);
                tuesday.setAdapter(weeklyGigsAdapter);
                tuesday.setLayoutManager(new LinearLayoutManager(ViewWeeklyScheduleOfBar.this));
            }

            @Override
            public void onFailure(Call<List<BarGigs>> call, Throwable t) {

            }
        });

        Call<List<BarGigs>> call2 = PerformerRetrofitClient.getInstance().getApi().getWeeklyScheduleBarTimeW(pid);
        call2.enqueue(new Callback<List<BarGigs>>() {
            @Override
            public void onResponse(Call<List<BarGigs>> call, Response<List<BarGigs>> response) {
                barGigs = response.body();
                weeklyGigsAdapter = new WeeklyGigsBarTimeAdapter(ViewWeeklyScheduleOfBar.this,barGigs);
                wedsnday.setAdapter(weeklyGigsAdapter);
                wedsnday.setLayoutManager(new LinearLayoutManager(ViewWeeklyScheduleOfBar.this));
            }

            @Override
            public void onFailure(Call<List<BarGigs>> call, Throwable t) {

            }
        });

        Call<List<BarGigs>> call3 = PerformerRetrofitClient.getInstance().getApi().getWeeklyScheduleBarTimeTH(pid);
        call3.enqueue(new Callback<List<BarGigs>>() {
            @Override
            public void onResponse(Call<List<BarGigs>> call, Response<List<BarGigs>> response) {
                barGigs = response.body();
                weeklyGigsAdapter = new WeeklyGigsBarTimeAdapter(ViewWeeklyScheduleOfBar.this,barGigs);
                thursday.setAdapter(weeklyGigsAdapter);
                thursday.setLayoutManager(new LinearLayoutManager(ViewWeeklyScheduleOfBar.this));
            }

            @Override
            public void onFailure(Call<List<BarGigs>> call, Throwable t) {

            }
        });

        Call<List<BarGigs>> call4 = PerformerRetrofitClient.getInstance().getApi().getWeeklyScheduleBarTimeF(pid);
        call4.enqueue(new Callback<List<BarGigs>>() {
            @Override
            public void onResponse(Call<List<BarGigs>> call, Response<List<BarGigs>> response) {
                barGigs = response.body();
                weeklyGigsAdapter = new WeeklyGigsBarTimeAdapter(ViewWeeklyScheduleOfBar.this,barGigs);
                friday.setAdapter(weeklyGigsAdapter);
                friday.setLayoutManager(new LinearLayoutManager(ViewWeeklyScheduleOfBar.this));
            }

            @Override
            public void onFailure(Call<List<BarGigs>> call, Throwable t) {

            }
        });

        Call<List<BarGigs>> call5 = PerformerRetrofitClient.getInstance().getApi().getWeeklyScheduleBarTimeSA(pid);
        call5.enqueue(new Callback<List<BarGigs>>() {
            @Override
            public void onResponse(Call<List<BarGigs>> call, Response<List<BarGigs>> response) {
                barGigs = response.body();
                weeklyGigsAdapter = new WeeklyGigsBarTimeAdapter(ViewWeeklyScheduleOfBar.this,barGigs);
                saturday.setAdapter(weeklyGigsAdapter);
                saturday.setLayoutManager(new LinearLayoutManager(ViewWeeklyScheduleOfBar.this));
            }

            @Override
            public void onFailure(Call<List<BarGigs>> call, Throwable t) {

            }
        });

        Call<List<BarGigs>> call6 = PerformerRetrofitClient.getInstance().getApi().getWeeklyScheduleBarTimeSU(pid);
        call6.enqueue(new Callback<List<BarGigs>>() {
            @Override
            public void onResponse(Call<List<BarGigs>> call, Response<List<BarGigs>> response) {
                barGigs = response.body();
                weeklyGigsAdapter = new WeeklyGigsBarTimeAdapter(ViewWeeklyScheduleOfBar.this,barGigs);
                sunday.setAdapter(weeklyGigsAdapter);
                sunday.setLayoutManager(new LinearLayoutManager(ViewWeeklyScheduleOfBar.this));
            }

            @Override
            public void onFailure(Call<List<BarGigs>> call, Throwable t) {

            }
        });


    }
}
