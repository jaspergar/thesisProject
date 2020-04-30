package com.example.enomfinal.models;


import java.util.Date;

public class Reviews {
    private float score;
    private String feedback,e_fname,e_lname,e_photo;
    private Date date_rated;

//    public Reviews(float score, String feedback, String e_fname, String e_lname, String e_photo) {
//        this.score = score;
//        this.feedback = feedback;
//        this.e_fname = e_fname;
//        this.e_lname = e_lname;
//        this.e_photo = e_photo;
//    }


    public Reviews(float score, String feedback, String e_fname, String e_lname, String e_photo, Date date_rated) {
        this.score = score;
        this.feedback = feedback;
        this.e_fname = e_fname;
        this.e_lname = e_lname;
        this.e_photo = e_photo;
        this.date_rated = date_rated;
    }

    public float getScore() {
        return score;
    }

    public String getFeedback() {
        return feedback;
    }

    public String getE_fname() {
        return e_fname;
    }

    public String getE_lname() {
        return e_lname;
    }

    public String getE_photo() {
        return e_photo;
    }

    public Date getDate_rated() {
        return date_rated;
    }
}
