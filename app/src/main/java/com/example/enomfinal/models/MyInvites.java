package com.example.enomfinal.models;

public class MyInvites {

    private int notif_id,bar_id,notif_to,sched_date_id,sched_time_id;
    private String status,content,notif_type,bar_photo,bar_name,start_time,end_time,once_schedule,weekly_schedule,performance_type,sched_description;

    public MyInvites(int notif_id, int bar_id, int notif_to, int sched_date_id, int sched_time_id, String status, String content, String notif_type, String bar_photo, String bar_name, String start_time, String end_time, String once_schedule, String weekly_schedule, String performance_type, String sched_description) {
        this.notif_id = notif_id;
        this.bar_id = bar_id;
        this.notif_to = notif_to;
        this.sched_date_id = sched_date_id;
        this.sched_time_id = sched_time_id;
        this.status = status;
        this.content = content;
        this.notif_type = notif_type;
        this.bar_photo = bar_photo;
        this.bar_name = bar_name;
        this.start_time = start_time;
        this.end_time = end_time;
        this.once_schedule = once_schedule;
        this.weekly_schedule = weekly_schedule;
        this.performance_type = performance_type;
        this.sched_description = sched_description;
    }

    public String getWeekly_schedule() {
        return weekly_schedule;
    }

    public int getNotif_id() {
        return notif_id;
    }

    public int getBar_id() {
        return bar_id;
    }

    public int getNotif_to() {
        return notif_to;
    }

    public int getSched_date_id() {
        return sched_date_id;
    }

    public int getSched_time_id() {
        return sched_time_id;
    }

    public String getStatus() {
        return status;
    }

    public String getContent() {
        return content;
    }

    public String getNotif_type() {
        return notif_type;
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

    public String getPerformance_type() {
        return performance_type;
    }

    public String getSched_description() {
        return sched_description;
    }
}
