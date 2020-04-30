package com.example.enomfinal.fragments.BottomNavFragments;


import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.enomfinal.R;
import com.example.enomfinal.activities.BarProfile;
import com.example.enomfinal.activities.ViewPost;
import com.example.enomfinal.adapters.EnomerPostAdapter;
import com.example.enomfinal.api.EnomerRetrofitClient;
import com.example.enomfinal.fragments.SearchFragments.Bar_Fragment;
import com.example.enomfinal.interfaces.BarItemClickListener;
import com.example.enomfinal.interfaces.PostItemClickLisener;
import com.example.enomfinal.models.BAR_PERFORMER_Post;
import com.example.enomfinal.models.Bars;
import com.example.enomfinal.storage.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private RecyclerView homeRv;
    private EnomerPostAdapter enomerPostAdapter;
    private List<BAR_PERFORMER_Post> bar_performer_postList;
    private PostItemClickLisener postItemClickLisener;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        homeRv = view.findViewById(R.id.home_rv);

        int user = SharedPrefManager.getInstance(getActivity()).getUser().getE_id();

        Call<List<BAR_PERFORMER_Post>> call = EnomerRetrofitClient.getInstance().getApi().getPost(user);
        call.enqueue(new Callback<List<BAR_PERFORMER_Post>>() {
            @Override
            public void onResponse(Call<List<BAR_PERFORMER_Post>> call, Response<List<BAR_PERFORMER_Post>> response) {
                bar_performer_postList = response.body();

                enomerPostAdapter = new EnomerPostAdapter(getActivity(),bar_performer_postList, new PostItemClickLisener() {
                    @Override
                    public void onPostClick(BAR_PERFORMER_Post bar_performer_post, ImageView PostImageView) {
                        Intent intent = new Intent(getActivity(), ViewPost.class);
                        intent.putExtra("intent_PostImage",bar_performer_post.getPost_media());
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(HomeFragment.this.getActivity(),PostImageView,"sharedName");
                        getActivity().startActivityFromFragment(HomeFragment.this,intent,1,options.toBundle());
                    }
                });

                homeRv.setAdapter(enomerPostAdapter);
                homeRv.setLayoutManager(new LinearLayoutManager(getActivity()));
            }

            @Override
            public void onFailure(Call<List<BAR_PERFORMER_Post>> call, Throwable t) {

            }
        });




    }
}
