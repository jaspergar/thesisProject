package com.example.enomfinal.models;

import android.widget.ImageView;

public class Notification {


//    private int notifphoto;
    private String e_photo,e_fname,e_lname,status,content,notif_type;
    private int notif_id,enomer_id,notif_to;


    public Notification(String e_photo, String e_fname, String e_lname, String status, String content, String notif_type, int notif_id, int enomer_id, int notif_to) {
        this.e_photo = e_photo;
        this.e_fname = e_fname;
        this.e_lname = e_lname;
        this.status = status;
        this.content = content;
        this.notif_type = notif_type;
        this.notif_id = notif_id;
        this.enomer_id = enomer_id;
        this.notif_to = notif_to;
    }


    public String getE_photo() {
        return e_photo;
    }

    public String getE_fname() {
        return e_fname;
    }

    public String getE_lname() {
        return e_lname;
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

    public int getNotif_id() {
        return notif_id;
    }

    public int getEnomer_id() {
        return enomer_id;
    }

    public int getNotif_to() {
        return notif_to;
    }
}
