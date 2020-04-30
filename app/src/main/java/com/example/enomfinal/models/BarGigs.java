package com.example.enomfinal.models;

import java.sql.Time;
import java.util.Date;

public class BarGigs {
    private int enomer_id,sched_date_id,bar_id,sched_time_id,performer_id;
    private String sched_type,sched_name,sched_description,status,bar_photo,bar_name,start_time,end_time,once_schedule,performance_type,weekly_schedule;


    public BarGigs(int enomer_id, int sched_date_id, int bar_id, int sched_time_id, int performer_id, String sched_type, String sched_name, String sched_description, String status, String bar_photo, String bar_name, String start_time, String end_time, String once_schedule, String performance_type, String weekly_schedule) {
        this.enomer_id = enomer_id;
        this.sched_date_id = sched_date_id;
        this.bar_id = bar_id;
        this.sched_time_id = sched_time_id;
        this.performer_id = performer_id;
        this.sched_type = sched_type;
        this.sched_name = sched_name;
        this.sched_description = sched_description;
        this.status = status;
        this.bar_photo = bar_photo;
        this.bar_name = bar_name;
        this.start_time = start_time;
        this.end_time = end_time;
        this.once_schedule = once_schedule;
        this.performance_type = performance_type;
        this.weekly_schedule = weekly_schedule;
    }

    public String getWeekly_schedule() {
        return weekly_schedule;
    }

    public int getPerformer_id() {
        return performer_id;
    }

    public String getPerformance_type() {
        return performance_type;
    }

    public int getEnomer_id() {
        return enomer_id;
    }

    public int getSched_date_id() {
        return sched_date_id;
    }

    public int getBar_id() {
        return bar_id;
    }

    public int getSched_time_id() {
        return sched_time_id;
    }

    public String getSched_type() {
        return sched_type;
    }

    public String getSched_name() {
        return sched_name;
    }

    public String getSched_description() {
        return sched_description;
    }

    public String getStatus() {
        return status;
    }

    public String getBar_photo() {
        return bar_photo;
    }

    public String getBar_name() {
        return bar_name;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public String getOnce_schedule() {
        return once_schedule;
    }
}
