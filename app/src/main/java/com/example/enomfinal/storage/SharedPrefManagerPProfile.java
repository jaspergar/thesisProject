package com.example.enomfinal.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.enomfinal.models.PerformerProfile;

public class SharedPrefManagerPProfile {

    private static final String SHARED_PREF_NAME = "my_shared_preff_pprofile";

    private static SharedPrefManagerPProfile mInstance;
    private Context mCtxt;

    private SharedPrefManagerPProfile(Context mCtxt){
        this.mCtxt = mCtxt;
    }

    public  static synchronized  SharedPrefManagerPProfile getInstance(Context mCtxt){
        if(mInstance == null){
            mInstance = new SharedPrefManagerPProfile(mCtxt);
        }
        return mInstance;
    }

    public void savepprofile(PerformerProfile performerProfile){
        SharedPreferences sharedPreferencesprofile = mCtxt.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferencesprofile.edit();

        editor.putInt("enomer_id", performerProfile.getEnomer_id());
        editor.putString("performer_name", performerProfile.getPerformer_name());
        editor.putString("performer_bio", performerProfile.getPerformer_bio());
        editor.putString("performer_category", performerProfile.getPerformer_category());
        editor.putString("performer_type", performerProfile.getPerformer_type());


        editor.apply();

    }

    public boolean isFilledup(){
        SharedPreferences sharedPreferencesprofile = mCtxt.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferencesprofile.getInt("enomer_id", -1) != -1;
    }
    public PerformerProfile getPProfile(){
        SharedPreferences sharedPreferencesprofile = mCtxt.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new PerformerProfile(
                sharedPreferencesprofile.getInt("enomer_id", -1),
                sharedPreferencesprofile.getString("performer_name",null),
                sharedPreferencesprofile.getString("performer_bio",null),
                sharedPreferencesprofile.getString("performer_category",null),
                sharedPreferencesprofile.getString("performer_type",null)

        );
    }

    public void clear(){
        SharedPreferences sharedPreferencesprofile = mCtxt.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferencesprofile.edit();
        editor.clear();
        editor.apply();
    }
}
