package com.example.enomfinal.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EnomerRetrofitClient {

    private static final String BASE_URL = StaticIP.getWifiIP()+"/Caps_enom/public/v1/index/";



    private static EnomerRetrofitClient mInstance;
    private Retrofit retrofit;

    private EnomerRetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized EnomerRetrofitClient getInstance(){
        if(mInstance == null){
            mInstance = new EnomerRetrofitClient();
        }
        return mInstance;
    }

    public Api getApi(){
        return retrofit.create(Api.class);
    }
}
