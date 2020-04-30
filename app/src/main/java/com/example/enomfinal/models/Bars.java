package com.example.enomfinal.models;

public class Bars {

    //temporary
    private int pgprofile_bfollowedPhoto;
    //


    private String bar_name,bar_type,bar_photo;
    private int bar_id,enomer_id,bar_contact;

    //temporary


//    public Bars(int bar_id) {
//        this.bar_id = bar_id;
//    }

    public Bars(int pgprofile_bfollowedPhoto, String bar_name) {
        this.pgprofile_bfollowedPhoto = pgprofile_bfollowedPhoto;
        this.bar_name = bar_name;
    }

    public int getPgprofile_bfollowedPhoto() {
        return pgprofile_bfollowedPhoto;
    }

    public void setPgprofile_bfollowedPhoto(int pgprofile_bfollowedPhoto) {
        this.pgprofile_bfollowedPhoto = pgprofile_bfollowedPhoto;
    }
//



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

    public String getBar_photo() {
        return bar_photo;
    }

    public void setBar_photo(String bar_photo) {
        this.bar_photo = bar_photo;
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

    public int getBar_contact() {
        return bar_contact;
    }

    public void setBar_contact(int bar_contact) {
        this.bar_contact = bar_contact;
    }
}
