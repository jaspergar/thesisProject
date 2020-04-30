package com.example.enomfinal.models;

public class PerfomerProfile_JoinedTable {
    //joined table of enomer and performer_profile

    //temporary
    private int performer_photo;
    private String performer_name;

    public PerfomerProfile_JoinedTable(int performer_photo, String performer_name) {
        this.performer_photo = performer_photo;
        this.performer_name = performer_name;
    }

    public int getPerformer_photo() {
        return performer_photo;
    }

    public void setPerformer_photo(int performer_photo) {
        this.performer_photo = performer_photo;
    }

    public String getPerformer_name() {
        return performer_name;
    }

    public void setPerformer_name(String performer_name) {
        this.performer_name = performer_name;
    }
}
