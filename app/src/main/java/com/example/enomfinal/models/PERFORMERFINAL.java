package com.example.enomfinal.models;

public class PERFORMERFINAL {

    private String e_photo,performer_type,performer_name,performer_category,performer_bio;
    private int performer_id,feedback;
    private float score;

    public PERFORMERFINAL(String e_photo, String performer_type, String performer_name, String performer_category, String performer_bio, int performer_id, int feedback, float score) {
        this.e_photo = e_photo;
        this.performer_type = performer_type;
        this.performer_name = performer_name;
        this.performer_category = performer_category;
        this.performer_bio = performer_bio;
        this.performer_id = performer_id;
        this.feedback = feedback;
        this.score = score;
    }

    public String getE_photo() {
        return e_photo;
    }

    public void setE_photo(String e_photo) {
        this.e_photo = e_photo;
    }

    public String getPerformer_type() {
        return performer_type;
    }

    public void setPerformer_type(String performer_type) {
        this.performer_type = performer_type;
    }

    public String getPerformer_name() {
        return performer_name;
    }

    public void setPerformer_name(String performer_name) {
        this.performer_name = performer_name;
    }

    public String getPerformer_category() {
        return performer_category;
    }

    public void setPerformer_category(String performer_category) {
        this.performer_category = performer_category;
    }

    public String getPerformer_bio() {
        return performer_bio;
    }

    public void setPerformer_bio(String performer_bio) {
        this.performer_bio = performer_bio;
    }

    public int getPerformer_id() {
        return performer_id;
    }

    public void setPerformer_id(int performer_id) {
        this.performer_id = performer_id;
    }

    public int getFeedback() {
        return feedback;
    }

    public void setFeedback(int feedback) {
        this.feedback = feedback;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
