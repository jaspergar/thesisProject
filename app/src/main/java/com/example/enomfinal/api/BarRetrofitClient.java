package com.example.enomfinal.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BarRetrofitClient {

    private static final String BASE_URL = StaticIP.getWifiIP()+"/Caps_enom/public/bar.php/v1/bar/";
    private static BarRetrofitClient myInstance;
    private Retrofit retrofit;

    private BarRetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized BarRetrofitClient getInstance(){
        if(myInstance == null){
            myInstance = new BarRetrofitClient();
        }
        return myInstance;
    }

    public Api getApi(){
        return retrofit.create(Api.class);
    }

}
