package com.example.enomfinal.fragments.SearchFragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.enomfinal.R;
import com.example.enomfinal.activities.PerformerType;

public class Performer_Fragment extends Fragment implements View.OnClickListener {

    Activity context;
      ImageView solo,duo,group;

    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.performer_fragment,container,false);
     return view;
     }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context=getActivity();

        solo = view.findViewById(R.id.solo_category_img);
        duo = view.findViewById(R.id.duo_category_img);
        group = view.findViewById(R.id.group_category_img);

        solo.setOnClickListener(this);
        duo.setOnClickListener(this);
        group.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.solo_category_img:
                Bundle bundle = new Bundle();
                bundle.putString("category","SOLO");
                Intent in=new Intent(getActivity(),PerformerType.class);
//                in.putExtra("category",bundle);
                in.putExtras(bundle);
                startActivity(in);
                break;
            case R.id.duo_category_img:
                Bundle bundl = new Bundle();
                bundl.putString("category","DUO");
                Intent inten = new Intent(getActivity(), PerformerType.class);
                inten.putExtras(bundl);
                startActivity(inten);
                break;
            case R.id.group_category_img:
                Bundle bund = new Bundle();
                bund.putString("category","GROUP");
                Intent inte = new Intent(getActivity(), PerformerType.class);
                inte.putExtras(bund);
                startActivity(inte);
                break;
        }
    }
}
