package com.example.enomfinal.models;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.enomfinal.storage.SharedPrefManagerPProfile;

public class PerformerProfile {
    private boolean error;
    private int enomer_id;
    private String performer_type,performer_name,performer_bio,performer_category;
    private PerformerProfile performerProfile;

//    public PerformerProfile(boolean error, int enomer_id, String performer_type, String performer_name, String performer_bio, String performer_category, PerformerProfile performerProfile) {
//        this.error = error;
//        this.enomer_id = enomer_id;
//        this.performer_type = performer_type;
//        this.performer_name = performer_name;
//        this.performer_bio = performer_bio;
//        this.performer_category = performer_category;
//        this.performerProfile = performerProfile;
//    }

//    public PerformerProfile(boolean error, int enomer_id, String performer_type, String performer_name, String performer_bio, String performer_category) {
//        this.error = error;
//        this.enomer_id = enomer_id;
//        this.performer_type = performer_type;
//        this.performer_name = performer_name;
//        this.performer_bio = performer_bio;
//        this.performer_category = performer_category;
//    }

    public PerformerProfile(int enomer_id, String performer_type, String performer_name, String performer_bio, String performer_category) {
        this.enomer_id = enomer_id;
        this.performer_type = performer_type;
        this.performer_name = performer_name;
        this.performer_bio = performer_bio;
        this.performer_category = performer_category;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public int getEnomer_id() {
        return enomer_id;
    }


    public void setEnomer_id(int enomer_id) {
        this.enomer_id = enomer_id;
    }

    public String getPerformer_type() {
        return performer_type;
    }

    public String getPerformer_name() {
        return performer_name;
    }

    public String getPerformer_bio() {
        return performer_bio;
    }

    public String getPerformer_category() {
        return performer_category;
    }


    public PerformerProfile getPerformerProfile() {
        return performerProfile;
    }

    public void setPerformerProfile(PerformerProfile performerProfile) {
        this.performerProfile = performerProfile;
    }
}
