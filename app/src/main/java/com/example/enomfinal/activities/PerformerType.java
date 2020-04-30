package com.example.enomfinal.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.enomfinal.R;
import com.example.enomfinal.activities.PerformerBrowse.PerformerLayout;

public class PerformerType extends AppCompatActivity implements View.OnClickListener {

    ImageView singer,dancer,spoken,magician,dj,musician;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performer_type);

        String category = getIntent().getExtras().getString("category");
        Toast.makeText(PerformerType.this,category+"",Toast.LENGTH_LONG).show();

        singer = findViewById(R.id.singer);
        dancer = findViewById(R.id.dancer);
        spoken = findViewById(R.id.spoken_word);
        magician = findViewById(R.id.magician);
        dj = findViewById(R.id.dj);
        musician = findViewById(R.id.musician);

        singer.setOnClickListener(this);
        dancer.setOnClickListener(this);
        spoken.setOnClickListener(this);
        magician.setOnClickListener(this);
        dj.setOnClickListener(this);
        musician.setOnClickListener(this);




    }


    @Override
    public void onClick(View view) {
        Intent intent;
        String category = getIntent().getExtras().getString("category");
        switch (view.getId()){
            case R.id.singer:
                intent = new Intent(this, PerformerLayout.class);
                intent.putExtra("category",category);
                intent.putExtra("type","SINGER");
                startActivity(intent);
                break;
            case R.id.dancer:
                intent = new Intent(this, PerformerLayout.class);
                intent.putExtra("category",category);
                intent.putExtra("type","DANCER");
                startActivity(intent);
                break;
            case R.id.spoken_word:
                intent = new Intent(this, PerformerLayout.class);
                intent.putExtra("category",category);
                intent.putExtra("type","SPOKEN WORD POETRY");
                startActivity(intent);
                break;
            case R.id.magician:
                intent = new Intent(this, PerformerLayout.class);
                intent.putExtra("category",category);
                intent.putExtra("type","MAGICIAN");
                startActivity(intent);
                break;
            case R.id.dj:
                intent = new Intent(this, PerformerLayout.class);
                intent.putExtra("category",category);
                intent.putExtra("type","DJ");
                startActivity(intent);
                break;
            case R.id.musician:
                intent = new Intent(this, PerformerLayout.class);
                intent.putExtra("category",category);
                intent.putExtra("type","MUSICIAN");
                startActivity(intent);
                break;
        }
    }
}
