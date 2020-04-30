package com.example.enomfinal.models;

public class Offer {
    //temporary values
    private String offer_name,photo;
    private int offer_price;

    public Offer(String offer_name, String photo, int offer_price) {
        this.offer_name = offer_name;
        this.photo = photo;
        this.offer_price = offer_price;
    }

    public String getOffer_name() {
        return offer_name;
    }

    public String getPhoto() {
        return photo;
    }

    public int getOffer_price() {
        return offer_price;
    }
}
