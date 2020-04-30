package com.example.enomfinal.fragments.SearchFragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.enomfinal.R;
import com.example.enomfinal.activities.BarProfile;
import com.example.enomfinal.activities.PerformerProfile;
import com.example.enomfinal.adapters.BarFragmentAdapters.SuggestedBarAdapter;
import com.example.enomfinal.adapters.MainActivitySearchTopRatedAdapter.PeopleYouMayKnowAdapter;
import com.example.enomfinal.adapters.PerformerFragmentAdapters.Singer.TopRatedSingerPerformerAdapter;
import com.example.enomfinal.api.BarRetrofitClient;
import com.example.enomfinal.api.PerformerRetrofitClient;
import com.example.enomfinal.interfaces.BarItemClickListener;
import com.example.enomfinal.interfaces.PerformerItemClickListener;
import com.example.enomfinal.models.BARSFINAL;
import com.example.enomfinal.models.PERFORMERFINAL;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Search_layout_perfomerfragment extends Fragment {
    RecyclerView barRv,PerformerRv,PeopleRv;
    SuggestedBarAdapter suggestedBarAdapter;
    TopRatedSingerPerformerAdapter topRatedSingerPerformerAdapter;
    PeopleYouMayKnowAdapter peopleYouMayKnowAdapter;
    List<BARSFINAL> barsfinalList;
    List<PERFORMERFINAL> performerfinalList;

    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.search_layout_performerfragment,container,false);
        return view;
    }


}
