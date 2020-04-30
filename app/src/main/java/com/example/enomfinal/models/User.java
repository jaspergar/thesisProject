package com.example.enomfinal.models;


public class User {
    private int e_id;
    private String e_email,e_password,e_fname,e_lname,e_gender,e_dob,e_type,e_cnumber,e_photo;


    public User(int e_id) {
        this.e_id = e_id;
    }

    public User(int e_id, String e_email, String e_password, String e_fname, String e_lname, String e_gender, String e_dob, String e_type, String e_cnumber, String e_photo) {
        this.e_id = e_id;
        this.e_email = e_email;
        this.e_password = e_password;
        this.e_fname = e_fname;
        this.e_lname = e_lname;
        this.e_gender = e_gender;
        this.e_dob = e_dob;
        this.e_type = e_type;
        this.e_cnumber = e_cnumber;
        this.e_photo = e_photo;
    }

    public int getE_id() {
        return e_id;
    }

    public String getE_email() {
        return e_email;
    }

    public String getE_password() {
        return e_password;
    }

    public String getE_fname() {
        return e_fname;
    }

    public String getE_lname() {
        return e_lname;
    }

    public String getE_gender() {
        return e_gender;
    }

    public String getE_dob() {
        return e_dob;
    }

    public String getE_type() {
        return e_type;
    }

    public String getE_cnumber() {
        return e_cnumber;
    }

    public String getE_photo() {
        return e_photo;
    }
}

