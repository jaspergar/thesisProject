package com.example.enomfinal.models;

public class FinalPerformerSchedNotif {

    private String bar_name,e_photo,once_schedule,weekly_schedule,start_time,end_time,sched_name,bar_photo,performer_name,performer_type,performer_category;
    private int performer_id;

    public FinalPerformerSchedNotif(String bar_name, String e_photo, String once_schedule, String weekly_schedule, String start_time, String end_time, String sched_name, String bar_photo, String performer_name, String performer_type, String performer_category, int performer_id) {
        this.bar_name = bar_name;
        this.e_photo = e_photo;
        this.once_schedule = once_schedule;
        this.weekly_schedule = weekly_schedule;
        this.start_time = start_time;
        this.end_time = end_time;
        this.sched_name = sched_name;
        this.bar_photo = bar_photo;
        this.performer_name = performer_name;
        this.performer_type = performer_type;
        this.performer_category = performer_category;
        this.performer_id = performer_id;
    }

    public String getBar_photo() {
        return bar_photo;
    }

    public String getPerformer_name() {
        return performer_name;
    }

    public String getPerformer_type() {
        return performer_type;
    }

    public String getPerformer_category() {
        return performer_category;
    }

    public String getEvent_name() {
        return sched_name;
    }

    public String getBar_name() {
        return bar_name;
    }

    public String getE_photo() {
        return e_photo;
    }

    public String getOnce_schedule() {
        return once_schedule;
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

    public int getPerformer_id() {
        return performer_id;
    }
}
