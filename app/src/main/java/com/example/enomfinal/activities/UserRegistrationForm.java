package com.example.enomfinal.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.enomfinal.R;
import com.example.enomfinal.api.EnomerRetrofitClient;
import com.example.enomfinal.fragments.DialogFragment.DatePickerFragment;
import com.example.enomfinal.fragments.DialogFragment.UserTypeFragment;
import com.example.enomfinal.models.DefaultResponse;
import com.example.enomfinal.storage.SharedPrefManager;

import java.text.DateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRegistrationForm extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, View.OnClickListener, UserTypeFragment.UserTypeListener {

   private TextView tv_dob,tv_usertype;
   private EditText E_fname,E_lname,E_email,E_password,E_cnumber;
    private RadioButton R_gender;
    private RadioGroup RG_gender;
    ProgressBar registerProgressBar;
    Button registerB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration_form);

        registerB = findViewById(R.id.B_register);
        registerProgressBar = findViewById(R.id.progressbar_register);
        E_fname = findViewById(R.id.e_fname);
        E_lname = findViewById(R.id.e_lname);
        E_email = findViewById(R.id.e_email);
        E_password = findViewById(R.id.e_password);
        E_cnumber = findViewById(R.id.e_contact_number);
        RG_gender = findViewById(R.id.rg_gender);
        tv_dob = findViewById(R.id.e_tv_dob);
        tv_usertype = findViewById(R.id.e_tv_usertype);

        //date
        findViewById(R.id.e_button_dob).setOnClickListener(this);
        //usertype
        findViewById(R.id.e_button_usertype).setOnClickListener(this);
        //register
        findViewById(R.id.B_register).setOnClickListener(this);
        //tologin
        findViewById(R.id.T_toLogin).setOnClickListener(this);

    }
    @Override
    protected void onStart() {
        super.onStart();
        if(SharedPrefManager.getInstance(this).isLoggedin()){
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }


    //     register user
    private void register() {
        final String email = E_email.getText().toString().trim();
        final String password = E_password.getText().toString().trim();
        String fname = E_fname.getText().toString().trim();
        final String lname = E_lname.getText().toString().trim();
        String number = E_cnumber.getText().toString().trim();


        //email validation
        if (email.isEmpty()) {
            E_email.setError("Email is required");
            E_email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            E_email.setError("Proper email is required");
            E_email.requestFocus();
            return;
        }
        //password validation
        if (password.isEmpty()) {
            E_password.setError("Password is required");
            E_password.requestFocus();
            return;
        }
        if (password.length() < 6) {
            E_password.setError("Password must contain atleast 6 characters");
            E_password.requestFocus();
            return;
        }
        //fname validation
        if (fname.isEmpty()) {
            E_fname.setError("First Name is required");
            E_fname.requestFocus();
            return;
        }
        //lname validation
        if (lname.isEmpty()) {
            E_lname.setError("Last Name is required");
            E_lname.requestFocus();
            return;
        }
        //cnumber validation
        if (number.isEmpty()) {
            E_cnumber.setError("Name is required");
            E_cnumber.requestFocus();
            return;
        }

        int cnumber = Integer.parseInt(number);
        //gender
        int radioGroupId = RG_gender.getCheckedRadioButtonId();
        R_gender = findViewById(radioGroupId);
        String gender = R_gender.getText().toString();

        String date = tv_dob.getText().toString().trim();
        String type = tv_usertype.getText().toString().trim();

        final ProgressBar registerProgressBar = findViewById(R.id.progressbar_register);
        registerProgressBar.setIndeterminate(true);
        registerProgressBar.setVisibility(View.VISIBLE);
        // registration
        Call<DefaultResponse> call = EnomerRetrofitClient.getInstance().getApi().createUser(email,password,fname,lname,cnumber,gender,date,type);


        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                registerProgressBar.setVisibility(View.GONE);
                if(response.code() == 201){
                    DefaultResponse dr = response.body();



                    Intent intent = new Intent(getApplicationContext(), WelcomeScreen.class);
                    intent.putExtra("reg_email",email);
                    startActivityForResult(intent,1);
                    Toast.makeText(UserRegistrationForm.this,dr.getMsg(),Toast.LENGTH_LONG).show();
 
                }else if(response.code() == 422){
                    Toast.makeText(UserRegistrationForm.this,"User already Exist",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {

            }
        });
    }


    @Override
    public void onClick(View view) {


        switch (view.getId()){
            case R.id.e_button_dob :
                //date of birth
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(),"Date of Birth");
                break;
            case R.id.e_button_usertype :
                DialogFragment usertype = new UserTypeFragment();
                usertype.setCancelable(false);
                usertype.show(getSupportFragmentManager(),"User Type");
                break;
            case R.id.B_register:
                registerB.setVisibility(View.GONE);
                registerProgressBar.setIndeterminate(true);
                registerProgressBar.setVisibility(View.VISIBLE);
                register();
                break;
            case R.id.T_toLogin:
                startActivity(new Intent(this,WelcomeScreen.class));
                break;
        }

    }

    //date of birth
    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayofMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayofMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        tv_dob.setText(currentDateString);
    }

    //usertype
    @Override
    public void onPositiveButtonClicked(String[] list, int position) {
        tv_usertype.setText(list[position]);
    }

    @Override
    public void onNegativeButtonClicked() {

    }


}
