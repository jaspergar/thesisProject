package com.example.enomfinal.storage;


import android.content.Context;
import android.content.SharedPreferences;

import com.example.enomfinal.models.User;

public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "my_shared_preff";

    private static SharedPrefManager mInstance;
    private Context mCtxt;

    private SharedPrefManager(Context mCtxt){
        this.mCtxt = mCtxt;
    }

    public  static synchronized  SharedPrefManager getInstance(Context mCtxt){
        if(mInstance == null){
            mInstance = new SharedPrefManager(mCtxt);
        }
        return mInstance;
    }

    public void saveuser(User user){
        SharedPreferences sharedPreferences = mCtxt.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();

        editor.putInt("e_id", user.getE_id());
        editor.putString("e_email", user.getE_email());
        editor.putString("e_password", user.getE_password());
        editor.putString("e_fname", user.getE_fname());
        editor.putString("e_lname", user.getE_lname());
        editor.putString("e_dob", user.getE_dob());
        editor.putString("e_cnumber", user.getE_cnumber());
        editor.putString("e_type", user.getE_type());
        editor.putString("e_gender", user.getE_gender());
        editor.putString("e_photo", user.getE_photo());

        editor.apply();

    }

    public boolean isLoggedin(){
        SharedPreferences sharedPreferences = mCtxt.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("e_id", -1) != -1;
    }
    public User getUser(){
        SharedPreferences sharedPreferences = mCtxt.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getInt("e_id", -1),
                sharedPreferences.getString("e_email",null),
                sharedPreferences.getString("e_password",null),
                sharedPreferences.getString("e_fname",null),
                sharedPreferences.getString("e_lname",null),
                sharedPreferences.getString("e_dob",null),
                sharedPreferences.getString("e_cnumber",null),
                sharedPreferences.getString("e_type",null),
                sharedPreferences.getString("e_gender",null),
                sharedPreferences.getString("e_photo",null)
        );
    }

    public void clear(){
        SharedPreferences sharedPreferences = mCtxt.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
