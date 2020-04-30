package com.example.enomfinal.activities.PerformerLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.FileUtils;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.enomfinal.R;
import com.example.enomfinal.api.PerformerRetrofitClient;
import com.example.enomfinal.models.DefaultResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.BitSet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPost extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_PERMISSION = 1000;
    private static final int REQUEST_PICK_PHOTO = 1001;
    Button buttonadd;
    EditText desc;
    ImageView photo;
    Bitmap bitmap;
    ProgressDialog progressDialog;
    Uri selectedfileuri;
    String im= "";
    int enomerid;
    String posttype;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE
            },REQUEST_PERMISSION);
        }

        enomerid = getIntent().getExtras().getInt("enomerid");
        posttype = getIntent().getExtras().getString("posttype");

        buttonadd = findViewById(R.id.addpost_uploadbtn);
        desc = findViewById(R.id.addpost_desc);
        photo = findViewById(R.id.addpost_img);

        photo.setOnClickListener(this);
        buttonadd.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.addpost_img:
                chooseIMG();
                break;
            case R.id.addpost_uploadbtn:
                String d = desc.getText().toString().trim();


               uploadPhoto(enomerid,im,d,posttype);
//                Toast.makeText(AddPost.this,im,Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_PERMISSION:
            {
                if(grantResults.length > 0 && grantResults[0] ==  PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(AddPost.this,"permission granted",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(AddPost.this,"permission denied",Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() !=null){
            Uri filePath = data.getData();
            try{
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),filePath);
                photo.setImageBitmap(bitmap);
            }
            catch (IOException e){
                e.printStackTrace();
            }
                  im = getStringImage(bitmap);

        }
    }

    private void uploadPhoto(int enomerid, String stringImage,String des,String type) {

        final ProgressDialog progressDialog = new ProgressDialog(AddPost.this);
        progressDialog.setMessage("Uploading...");
        progressDialog.show();

        Call<DefaultResponse> call = PerformerRetrofitClient.getInstance().getApi().addpostTemp(enomerid,stringImage,des,type);
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if(response.code() == 201){
                    progressDialog.dismiss();
                    Intent intent = new Intent(AddPost.this,PerformerActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {

            }
        });
    }

    private void chooseIMG() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Pictures"),1);
    }
    public String getStringImage(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50,byteArrayOutputStream);

        byte[] imageByteArray = byteArrayOutputStream.toByteArray();
        String encodedImage =Base64.encodeToString(imageByteArray,Base64.DEFAULT);

        return encodedImage;
    }
}
