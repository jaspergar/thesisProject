package com.example.enomfinal.models;

public class FinalBarSchedNotif {

    private String performer_name,performer_category,performer_type,bar_name,bar_type,bar_photo,once_schedule,weekly_schedule,start_time,end_time,sched_name,e_photo;
    private float bar_score,performer_rating;
    private int performer_id;

    public FinalBarSchedNotif(String performer_name, String performer_category, String performer_type, String bar_name, String bar_type, String bar_photo, String once_schedule, String weekly_schedule, String start_time, String end_time, String sched_name, String e_photo, float bar_score, float performer_rating, int performer_id) {
        this.performer_name = performer_name;
        this.performer_category = performer_category;
        this.performer_type = performer_type;
        this.bar_name = bar_name;
        this.bar_type = bar_type;
        this.bar_photo = bar_photo;
        this.once_schedule = once_schedule;
        this.weekly_schedule = weekly_schedule;
        this.start_time = start_time;
        this.end_time = end_time;
        this.sched_name = sched_name;
        this.e_photo = e_photo;
        this.bar_score = bar_score;
        this.performer_rating = performer_rating;
        this.performer_id = performer_id;
    }

    public String getOnce_schedule() {
        return once_schedule;
    }

    public String getPerformer_name() {
        return performer_name;
    }

    public String getPerformer_category() {
        return performer_category;
    }

    public String getPerformer_type() {
        return performer_type;
    }

    public String getBar_name() {
        return bar_name;
    }

    public String getBar_type() {
        return bar_type;
    }

    public String getBar_photo() {
        return bar_photo;
    }

    public String getWeekly_schedule() {
        return weekly_schedule;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public String getSched_name() {
        return sched_name;
    }

    public String getE_photo() {
        return e_photo;
    }

    public float getBar_score() {
        return bar_score;
    }

    public float getPerformer_rating() {
        return performer_rating;
    }

    public int getPerformer_id() {
        return performer_id;
    }
}
