package com.example.enomfinal.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.enomfinal.R;
import com.example.enomfinal.activities.PerformerLayout.AddPost;
import com.example.enomfinal.activities.PerformerLayout.PerformerActivity;
import com.example.enomfinal.activities.PerformerLayout.SelectCategoryPerformer;
import com.example.enomfinal.api.EnomerRetrofitClient;
import com.example.enomfinal.api.PerformerRetrofitClient;
import com.example.enomfinal.models.LoginResponse;
import com.example.enomfinal.models.PerformerProfile;
import com.example.enomfinal.storage.SharedPrefManager;
import com.example.enomfinal.storage.SharedPrefManagerPProfile;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WelcomeScreen extends AppCompatActivity implements View.OnClickListener{
    EditText e_email,e_password;
    TextView forgotp;
     ProgressBar loginProgressBar;
     Button logbtn;
     String Performer = "Performer";
     String PartyGoer = "Party-Goer";

     PerformerProfile performerProfile;
    int pp_id = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        forgotp = findViewById(R.id.welcomeS_forgotpass);
        loginProgressBar  = findViewById(R.id.progressbar_login);
        logbtn = findViewById(R.id.welocomS_button_login);
        e_email = findViewById(R.id.e_welcomeS_email);
        e_password =findViewById(R.id.e_welcomeS_password);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            boolean isNew = extras.getBoolean("reg_email", true);
            if (isNew) {
                String email = getIntent().getExtras().getString("reg_email");
                e_email.setText(email);
            }
        }

        findViewById(R.id.welocomS_button_login).setOnClickListener(this);
        findViewById(R.id.welcomeS_register_id).setOnClickListener(this);



    }

    @Override
    protected void onStart() {
        super.onStart();
        if(SharedPrefManager.getInstance(this).isLoggedin()){
            if(SharedPrefManager.getInstance(this).getUser().getE_type().equals(PartyGoer)){
                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }else if(SharedPrefManager.getInstance(this).getUser().getE_type().equals(Performer)) {
                if (SharedPrefManagerPProfile.getInstance(this).isFilledup()) {
                    Intent intent = new Intent(WelcomeScreen.this, PerformerActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else if (!SharedPrefManagerPProfile.getInstance(this).isFilledup()) {
                    Intent intent = new Intent(this, SelectCategoryPerformer.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }

        }


    }


private boolean uservalidation(String Email,String Password){
    //email validation
    if(Email.isEmpty()){
        e_email.setError("Email is required");
        e_email.requestFocus();
        return true;
    }

    if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
        e_email.setError("Proper email is required");
        e_email.requestFocus();
        return true;
    }
    //password validation
    if(Password.isEmpty()){
        e_password.setError("Password is required");
        e_password.requestFocus();
        return true;
    }
    if(Password.length() < 6){
        e_password.setError("Password must contain atleast 6 characters");
        e_password.requestFocus();
        return true;
    }
  return false;
}



    private void userLogin(String Email,String Password){

        final ProgressDialog progressDialog = new ProgressDialog(WelcomeScreen.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        Call<LoginResponse> call = EnomerRetrofitClient
                .getInstance()
                .getApi()
                .loginUser(Email,Password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
//                loginProgressBar.setVisibility(View.GONE);
                LoginResponse loginResponse = response.body();

                progressDialog.dismiss();
                if(!loginResponse.isError()){
                    SharedPrefManager.getInstance(WelcomeScreen.this).saveuser(loginResponse.getUser());
                    if(response.code() == 201){

                        Intent intent = new Intent(WelcomeScreen.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }else if(response.code() == 202){
                        int e_id = SharedPrefManager.getInstance(WelcomeScreen.this).getUser().getE_id();
//                        Toast.makeText(WelcomeScreen.this,e_id+" ",Toast.LENGTH_LONG).show();
                        Call<PerformerProfile> call2 = PerformerRetrofitClient.getInstance().getApi().getPerformerProfile(e_id);
                        call2.enqueue(new Callback<PerformerProfile>() {
                            @Override
                            public void onResponse(Call<PerformerProfile> call, Response<PerformerProfile> response) {
                                 performerProfile = response.body();
                                SharedPrefManagerPProfile.getInstance(WelcomeScreen.this).savepprofile(performerProfile);
                                int e_id = SharedPrefManager.getInstance(WelcomeScreen.this).getUser().getE_id();

                                 pp_id = performerProfile.getEnomer_id();

//                                Toast.makeText(WelcomeScreen.this,pp_id+"",Toast.LENGTH_LONG).show();
                                if(pp_id == e_id){
                                    Intent intent = new Intent(WelcomeScreen.this, PerformerActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                }else if (pp_id != e_id){
                                    Intent intent = new Intent(WelcomeScreen.this, SelectCategoryPerformer.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                }
                            }
                            @Override
                            public void onFailure(Call<PerformerProfile> call, Throwable t) {

                            }
                        });
                    }

                }else{
                    Toast.makeText(WelcomeScreen.this,loginResponse.getMessage(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });


    }
    @Override
    public void onClick(View view) {
        String Email = e_email.getText().toString().trim();
        String Password = e_password.getText().toString().trim();
        switch (view.getId()){
            case R.id.welocomS_button_login:
                if(uservalidation(Email,Password)){
                    loginProgressBar.setVisibility(View.GONE);
                }else if(!uservalidation(Email,Password)){
//                    loginProgressBar.setIndeterminate(true);
//                    loginProgressBar.setVisibility(View.VISIBLE);

                    userLogin(Email,Password);
                }

                break;
            case R.id.welcomeS_register_id:
                startActivity(new Intent(this,UserRegistrationForm.class));
                break;
        }
    }





}
