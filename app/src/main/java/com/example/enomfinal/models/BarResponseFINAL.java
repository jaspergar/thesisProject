package com.example.enomfinal.models;

import java.util.List;

public class BarResponseFINAL {
    private boolean error;
    private List<BARSFINAL> barsfinallist;

    public BarResponseFINAL(boolean error, List<BARSFINAL> barsfinallist) {
        this.error = error;
        this.barsfinallist = barsfinallist;
    }



    public void setError(boolean error) {
        this.error = error;
    }

    public List<BARSFINAL> getBarsfinallist() {
        return barsfinallist;
    }

}
