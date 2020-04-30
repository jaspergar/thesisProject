package com.example.enomfinal.models;

import java.util.Date;

public class BAR_PERFORMER_Post {

    private String performer_name,e_photo,e_type,post_description,post_type,post_media,event_name,event_performer,event_start_time,event_end_time;
    private int post_id,enomer_id;
    private Date post_date,event_date;





    public BAR_PERFORMER_Post(String performer_name, String e_photo, String e_type, String post_description, String post_type, String post_media, String event_name, String event_performer, String event_start_time, String event_end_time, int post_id, int enomer_id, Date post_date, Date event_date) {
        this.performer_name = performer_name;
        this.e_photo = e_photo;
        this.e_type = e_type;
        this.post_description = post_description;
        this.post_type = post_type;
        this.post_media = post_media;
        this.event_name = event_name;
        this.event_performer = event_performer;
        this.event_start_time = event_start_time;
        this.event_end_time = event_end_time;
        this.post_id = post_id;
        this.enomer_id = enomer_id;
        this.post_date = post_date;
        this.event_date = event_date;
    }

    public String getPerformer_name() {
        return performer_name;
    }

    public String getE_photo() {
        return e_photo;
    }

    public String getE_type() {
        return e_type;
    }

    public String getPost_description() {
        return post_description;
    }

    public String getPost_type() {
        return post_type;
    }

    public String getPost_media() {
        return post_media;
    }

    public String getEvent_name() {
        return event_name;
    }

    public String getEvent_performer() {
        return event_performer;
    }

    public String getEvent_start_time() {
        return event_start_time;
    }

    public String getEvent_end_time() {
        return event_end_time;
    }

    public int getPost_id() {
        return post_id;
    }

    public int getEnomer_id() {
        return enomer_id;
    }

    public Date getPost_date() {
        return post_date;
    }

    public Date getEvent_date() {
        return event_date;
    }
}
