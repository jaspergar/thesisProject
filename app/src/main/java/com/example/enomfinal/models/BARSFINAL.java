package com.example.enomfinal.models;


//import java.sql.Time;
public class BARSFINAL {

    private String bar_photo,bar_name,bar_type,bar_location,bar_description;
//    private Time bar_hrs_open,bar_hrs_close;
    private int bar_id,enomer_id,feedback,bar_contact;
    private float score,bar_latitude,bar_longitude;

    public BARSFINAL(String bar_photo, String bar_name, String bar_type, String bar_location, String bar_description, int bar_id, int enomer_id, int feedback, int bar_contact, float score, float bar_latitude, float bar_longitude) {
        this.bar_photo = bar_photo;
        this.bar_name = bar_name;
        this.bar_type = bar_type;
        this.bar_location = bar_location;
        this.bar_description = bar_description;
        this.bar_id = bar_id;
        this.enomer_id = enomer_id;
        this.feedback = feedback;
        this.bar_contact = bar_contact;
        this.score = score;
        this.bar_latitude = bar_latitude;
        this.bar_longitude = bar_longitude;
    }

//    public BARSFINAL(String bar_photo, String bar_name, String bar_type, int bar_id, int enomer_id, int bar_contact, float score) {
//        this.bar_photo = bar_photo;
//        this.bar_name = bar_name;
//        this.bar_type = bar_type;
//        this.bar_id = bar_id;
//        this.enomer_id = enomer_id;
//        this.bar_contact = bar_contact;
//        this.score = score;
//    }


    public String getBar_description() {
        return bar_description;
    }

    public void setBar_description(String bar_description) {
        this.bar_description = bar_description;
    }

    public String getBar_photo() {
        return bar_photo;
    }

    public void setBar_photo(String bar_photo) {
        this.bar_photo = bar_photo;
    }

    public String getBar_name() {
        return bar_name;
    }

    public void setBar_name(String bar_name) {
        this.bar_name = bar_name;
    }

    public String getBar_type() {
        return bar_type;
    }

    public void setBar_type(String bar_type) {
        this.bar_type = bar_type;
    }

    public String getBar_location() {
        return bar_location;
    }

    public void setBar_location(String bar_location) {
        this.bar_location = bar_location;
    }



    public int getBar_id() {
        return bar_id;
    }

    public void setBar_id(int bar_id) {
        this.bar_id = bar_id;
    }

    public int getEnomer_id() {
        return enomer_id;
    }

    public void setEnomer_id(int enomer_id) {
        this.enomer_id = enomer_id;
    }

    public int getFeedback() {
        return feedback;
    }

    public void setFeedback(int feedback) {
        this.feedback = feedback;
    }

    public int getBar_contact() {
        return bar_contact;
    }

    public void setBar_contact(int bar_contact) {
        this.bar_contact = bar_contact;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public float getBar_latitude() {
        return bar_latitude;
    }

    public void setBar_latitude(float bar_latitude) {
        this.bar_latitude = bar_latitude;
    }

    public float getBar_longitude() {
        return bar_longitude;
    }

    public void setBar_longitude(float bar_longitude) {
        this.bar_longitude = bar_longitude;
    }
}
