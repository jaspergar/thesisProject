package com.example.enomfinal.activities.PerformerLayout;

import androidx.appcompat.app.AppCompatActivity;
import com.example.enomfinal.R;
import com.example.enomfinal.storage.SharedPrefManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectCategoryPerformer extends AppCompatActivity implements View.OnClickListener {

    Button solo,duo,group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_category_performer);

        solo = findViewById(R.id.button_solo_category);
        duo = findViewById(R.id.button_duo_category);
        group = findViewById(R.id.button_group_category);

        solo.setOnClickListener(this);
        duo.setOnClickListener(this);
        group.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!SharedPrefManager.getInstance(this).isLoggedin()) {
            Intent intent = new Intent(this, SelectCategoryPerformer.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button_solo_category:
                Intent intent = new Intent(this,SelectTypePerformer.class);
                intent.putExtra("category","SOLO");
                startActivity(intent);
                break;
            case R.id.button_duo_category:
                Intent inte = new Intent(this,SelectTypePerformer.class);
                inte.putExtra("category","DUO");
                startActivity(inte);
                break;
            case R.id.button_group_category:
                Intent inten = new Intent(this,SelectTypePerformer.class);
                inten.putExtra("category","GROUP");
                startActivity(inten);
                break;
        }
    }
}
