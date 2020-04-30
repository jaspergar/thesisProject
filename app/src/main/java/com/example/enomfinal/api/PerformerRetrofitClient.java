package com.example.enomfinal.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PerformerRetrofitClient {

    private static final String BASE_URL = StaticIP.getWifiIP()+"/Caps_enom/public/performer.php/v1/performer/";



    private static PerformerRetrofitClient mInstance;
    private Retrofit retrofit;

    private PerformerRetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized PerformerRetrofitClient getInstance(){
        if(mInstance == null){
            mInstance = new PerformerRetrofitClient();
        }
        return mInstance;
    }

    public Api getApi(){
        return retrofit.create(Api.class);
    }
}
