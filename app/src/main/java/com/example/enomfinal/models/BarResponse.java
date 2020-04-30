package com.example.enomfinal.models;

import java.util.List;

public class BarResponse {

    private boolean error;
    private List<Bars> bars;

    public BarResponse(boolean error, List<Bars> bars) {
        this.error = error;
        this.bars = bars;
    }

    public boolean isError() {
        return error;
    }

    public List<Bars> getBars() {
        return bars;
    }
}

