package com.example.enomfinal.activities.PerformerLayout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.enomfinal.R;
import com.example.enomfinal.activities.ViewPost;
import com.example.enomfinal.api.PerformerRetrofitClient;
import com.example.enomfinal.models.PerformerProfile;
import com.example.enomfinal.storage.SharedPrefManager;
import com.example.enomfinal.storage.SharedPrefManagerPProfile;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMember extends AppCompatActivity implements View.OnClickListener {
  private TextInputEditText memberFname,memberLname,memberRole;
    private RadioButton R_member_gender;
    private RadioGroup RG_member_gender;
    private Button addBtn,finishBtn,SkipBtn;

    PerformerProfile performerProfile;
    int pp_id,e_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);

      memberFname = findViewById(R.id.member_fname);
      memberLname = findViewById(R.id.member_lname);
      memberRole = findViewById(R.id.member_role);

      RG_member_gender = findViewById(R.id.member_radiogroup);

      addBtn = findViewById(R.id.member_addbtn);
      finishBtn = findViewById(R.id.member_finishbtn);
      SkipBtn = findViewById(R.id.member_skipbtn);

      addBtn.setOnClickListener(this);
      finishBtn.setOnClickListener(this);
      SkipBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final String category = getIntent().getExtras().getString("category");
        int enomer_id = SharedPrefManager.getInstance(this).getUser().getE_id();
        String fname= memberFname.getText().toString();
        String lname = memberLname.getText().toString();
        String role = memberRole.getText().toString();

        int radioGroupId = RG_member_gender.getCheckedRadioButtonId();
        R_member_gender = findViewById(radioGroupId);
        String gender = R_member_gender.getText().toString();

        switch(view.getId()){
            case R.id.member_addbtn:
                if(category.equals("DUO")){
                   addmemDuo(enomer_id,fname,lname,gender,role);
                }else if(category.equals("GROUP")){
                    addmemGroup(enomer_id,fname,lname,gender,role);
                }

                break;
            case R.id.member_finishbtn:
                 e_id = SharedPrefManager.getInstance(AddMember.this).getUser().getE_id();
                Call<PerformerProfile> call2 = PerformerRetrofitClient.getInstance().getApi().getPerformerProfile(e_id);
                call2.enqueue(new Callback<PerformerProfile>() {
                    @Override
                    public void onResponse(Call<PerformerProfile> call, Response<PerformerProfile> response) {
                        performerProfile = response.body();
                        SharedPrefManagerPProfile.getInstance(AddMember.this).savepprofile(performerProfile);
                        int e_id = SharedPrefManager.getInstance(AddMember.this).getUser().getE_id();

                        pp_id = performerProfile.getEnomer_id();

//                                Toast.makeText(WelcomeScreen.this,pp_id+"",Toast.LENGTH_LONG).show();
                        if(pp_id == e_id) {
                            Intent intent = new Intent(AddMember.this, PerformerActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }
                    }
                    @Override
                    public void onFailure(Call<PerformerProfile> call, Throwable t) {

                    }
                });
//                Intent intent = new Intent(AddMember.this,PerformerActivity.class);
//                startActivity(intent);
                break;
            case R.id.member_skipbtn:
                 e_id = SharedPrefManager.getInstance(AddMember.this).getUser().getE_id();
                Call<PerformerProfile> call3 = PerformerRetrofitClient.getInstance().getApi().getPerformerProfile(e_id);
                call3.enqueue(new Callback<PerformerProfile>() {
                    @Override
                    public void onResponse(Call<PerformerProfile> call, Response<PerformerProfile> response) {
                        performerProfile = response.body();
                        SharedPrefManagerPProfile.getInstance(AddMember.this).savepprofile(performerProfile);
                        int e_id = SharedPrefManager.getInstance(AddMember.this).getUser().getE_id();

                        pp_id = performerProfile.getEnomer_id();

//                                Toast.makeText(WelcomeScreen.this,pp_id+"",Toast.LENGTH_LONG).show();
                        if(pp_id == e_id) {
                            Intent intent = new Intent(AddMember.this, PerformerActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }
                    }
                    @Override
                    public void onFailure(Call<PerformerProfile> call, Throwable t) {

                    }
                });
                break;
        }
    }

    private void addmemGroup(int enomer_id,String fname,String lname,String gender,String role){
        Call<PerformerProfile> call = PerformerRetrofitClient.getInstance().getApi().addMember(enomer_id,fname,lname,gender,role);
        call.enqueue(new Callback<PerformerProfile>() {
            @Override
            public void onResponse(Call<PerformerProfile> call, Response<PerformerProfile> response) {
                memberFname.setText(" ");
                memberLname.setText(" ");
                memberRole.setText(" ");
                finishBtn.setEnabled(true);
                Toast.makeText(AddMember.this, "A member was Added!", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<PerformerProfile> call, Throwable t) {

            }
        });
    }

    private void addmemDuo(int enomer_id,String fname,String lname,String gender,String role){
        Call<PerformerProfile> call = PerformerRetrofitClient.getInstance().getApi().addMember(enomer_id,fname,lname,gender,role);

        call.enqueue(new Callback<PerformerProfile>() {
            @Override
            public void onResponse(Call<PerformerProfile> call, Response<PerformerProfile> response) {
                addBtn.setVisibility(View.GONE);
                findViewById(R.id.editText).setVisibility(View.GONE);
                findViewById(R.id.textInputLayout).setVisibility(View.GONE);
                findViewById(R.id.textInputLayout2).setVisibility(View.GONE);
                memberFname.setVisibility(View.GONE);
                memberLname.setVisibility(View.GONE);
                memberRole.setVisibility(View.GONE);
                RG_member_gender.setVisibility(View.GONE);
                finishBtn.setEnabled(true);
                Toast.makeText(AddMember.this,"A member was Added!",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<PerformerProfile> call, Throwable t) {

            }
        });
    }
}
