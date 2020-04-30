package com.example.enomfinal.activities.PerformerLayout;

import androidx.appcompat.app.AppCompatActivity;
import com.example.enomfinal.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


public class SelectTypePerformer extends AppCompatActivity implements View.OnClickListener {

    ImageView singer,dancer,spoken,magician,dj,musician;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_type_performer);


        String  performerCategory = getIntent().getExtras().getString("category");
         Toast.makeText(this,performerCategory,Toast.LENGTH_LONG).show();

        singer = findViewById(R.id.singer_ptype);
        dancer = findViewById(R.id.dancer_ptype);
        spoken = findViewById(R.id.spoken_ptype);
        magician = findViewById(R.id.magician_ptype);
        dj = findViewById(R.id.dj_ptype);
        musician = findViewById(R.id.musician_ptype);

        singer.setOnClickListener(this);
        dancer.setOnClickListener(this);
        spoken.setOnClickListener(this);
        magician.setOnClickListener(this);
        dj.setOnClickListener(this);
        musician.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String  performerCategory = getIntent().getExtras().getString("category");
        switch (view.getId()){
            case R.id.singer_ptype:
                Intent intent = new Intent(this, NameBioPerformer.class);
                intent.putExtra("Category",performerCategory);
                intent.putExtra("Type","Singer");
                startActivity(intent);
                break;
            case R.id.dancer_ptype:
                Intent inten = new Intent(this,NameBioPerformer.class);
                inten.putExtra("Category",performerCategory);
                inten.putExtra("Type","Dancer");
                startActivity(inten);
                break;
            case R.id.spoken_ptype:
                Intent inte = new Intent(this,NameBioPerformer.class);
                inte.putExtra("Category",performerCategory);
                inte.putExtra("Type","Spoken Word Poetry");
                startActivity(inte);
                break;
            case R.id.magician_ptype:
                Intent in = new Intent(this,NameBioPerformer.class);
                in.putExtra("Category",performerCategory);
                in.putExtra("Type","Magician");
                startActivity(in);
                break;
            case R.id.dj_ptype:
                Intent i = new Intent(this,NameBioPerformer.class);
                i.putExtra("Category",performerCategory);
                i.putExtra("Type","Dj");
                startActivity(i);
                break;
            case R.id.musician_ptype:
                Intent intentt = new Intent(this,NameBioPerformer.class);
                intentt.putExtra("Category",performerCategory);
                intentt.putExtra("Type","Musician");
                startActivity(intentt);
                break;
        }
    }
}
