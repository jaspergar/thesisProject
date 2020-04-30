package com.example.enomfinal.activities.PerformerLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.enomfinal.R;
import com.example.enomfinal.activities.MainActivity;
import com.example.enomfinal.activities.WelcomeScreen;
import com.example.enomfinal.api.PerformerRetrofitClient;
import com.example.enomfinal.models.DefaultResponse;
import com.example.enomfinal.models.PerformerProfile;
import com.example.enomfinal.storage.SharedPrefManager;
import com.example.enomfinal.storage.SharedPrefManagerPProfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NameBioPerformer extends AppCompatActivity implements View.OnClickListener {

    EditText performer_name,performer_desc;
    Button ok;
    PerformerProfile performerProfile;
    int pp_id;
    String Performer = "Performer";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_bio_performer);



        performer_name = findViewById(R.id.performer_nickname);
        performer_desc = findViewById(R.id.performer_desc);
        ok = findViewById(R.id.btn_ok);

        ok.setOnClickListener(this);





    }




    @Override
    public void onClick(View view) {
      int id = view.getId();
      if(id == R.id.btn_ok){
           addPerformer_profile();
      }
    }

    private void addPerformer_profile() {
        int enomer_id = SharedPrefManager.getInstance(this).getUser().getE_id();
        final String category = getIntent().getExtras().getString("Category");
        String type =getIntent().getExtras().getString("Type");
        String p_name = performer_name.getText().toString().trim();
        String p_bio = performer_desc.getText().toString().trim();


        //email validation
        if (p_name.isEmpty()) {
            performer_name.setError("Please enter your performer name/nickname is required");
            performer_name.requestFocus();
            return;
        }
        if(p_bio.isEmpty()){
            performer_desc.setError("A short description is required");
        }

           final String solo = "SOLO";
            final String duo = "DUO";
            final String group ="GROUP";

//        final ProgressBar registerProgressBar = findViewById(R.id.progressbar_register);
//        registerProgressBar.setIndeterminate(true);
//        registerProgressBar.setVisibility(View.VISIBLE);
        // add performer profile

        Call<DefaultResponse> call = PerformerRetrofitClient.getInstance().getApi().addPerformerProfile(enomer_id,p_name,p_bio,category,type);


        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
//                registerProgressBar.setVisibility(View.GONE);
                if(response.code() == 201){
                    DefaultResponse dr = response.body();
                    if(category.equals(solo)){
                        int e_id = SharedPrefManager.getInstance(NameBioPerformer.this).getUser().getE_id();
                        Call<PerformerProfile> call2 = PerformerRetrofitClient.getInstance().getApi().getPerformerProfile(e_id);
                        call2.enqueue(new Callback<PerformerProfile>() {
                            @Override
                            public void onResponse(Call<PerformerProfile> call, Response<PerformerProfile> response) {
                                performerProfile = response.body();
                                SharedPrefManagerPProfile.getInstance(NameBioPerformer.this).savepprofile(performerProfile);
                                int e_id = SharedPrefManager.getInstance(NameBioPerformer.this).getUser().getE_id();

                                pp_id = performerProfile.getEnomer_id();

//                                Toast.makeText(WelcomeScreen.this,pp_id+"",Toast.LENGTH_LONG).show();
                                if(pp_id == e_id) {
                                    Intent intent = new Intent(NameBioPerformer.this, PerformerActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                }
                            }
                            @Override
                            public void onFailure(Call<PerformerProfile> call, Throwable t) {

                            }
                        });
//                        Intent intent = new Intent(getApplicationContext(), PerformerActivity.class);
//                        startActivity(intent);
                    }else if(category.equals(duo) || category.equals(group)){
                        Intent intent = new Intent(getApplicationContext(), AddMember.class);
                        intent.putExtra("category",category);
                        startActivity(intent);
                    }

                }else if(response.code() == 422){
                    Toast.makeText(NameBioPerformer.this,"Error occured while selecting category.",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {

            }
        });
    }



}
