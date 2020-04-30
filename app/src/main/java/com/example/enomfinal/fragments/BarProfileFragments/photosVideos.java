package com.example.enomfinal.fragments.BarProfileFragments;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.enomfinal.R;
import com.example.enomfinal.activities.ViewPost;
import com.example.enomfinal.adapters.ProfileMediaRVAdapter;
import com.example.enomfinal.api.EnomerRetrofitClient;
import com.example.enomfinal.fragments.BottomNavFragments.HomeFragment;
import com.example.enomfinal.fragments.PerformerProfileFragments.Performer_media;
import com.example.enomfinal.interfaces.PostItemClickLisener;
import com.example.enomfinal.models.BAR_PERFORMER_Post;
import com.example.enomfinal.storage.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class photosVideos extends Fragment {

    private RecyclerView staggeredRv;
    private ProfileMediaRVAdapter profileMediaRVAdapter;
    private StaggeredGridLayoutManager manager;
    private List<BAR_PERFORMER_Post> bar_performer_posts;


    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return view = inflater.inflate(R.layout.barprofile_photosvideos_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        staggeredRv = view.findViewById(R.id.barprofile_mediaRV);
        manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        staggeredRv.setLayoutManager(manager);

        int barid = getActivity().getIntent().getExtras().getInt("intent_barId");


        Call<List<BAR_PERFORMER_Post>> call = EnomerRetrofitClient.getInstance().getApi().getPost(barid);
        call.enqueue(new Callback<List<BAR_PERFORMER_Post>>() {
            @Override
            public void onResponse(Call<List<BAR_PERFORMER_Post>> call, Response<List<BAR_PERFORMER_Post>> response) {
                bar_performer_posts = response.body();
                profileMediaRVAdapter = new ProfileMediaRVAdapter(getActivity(), bar_performer_posts, new PostItemClickLisener() {
                    @Override
                    public void onPostClick(BAR_PERFORMER_Post bar_performer_post, ImageView PostImageView) {
                        Intent intent = new Intent(getActivity(), ViewPost.class);
                        intent.putExtra("intent_PostImage",bar_performer_post.getPost_media());
//                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(photosVideos.this.getActivity(),PostImageView,"sharedName");
//                getActivity().startActivityFromFragment(photosVideos.this,intent,1,options.toBundle());
                        getActivity().startActivityFromFragment(photosVideos.this,intent,1);
                    }
                });
                staggeredRv.setAdapter(profileMediaRVAdapter);
            }

            @Override
            public void onFailure(Call<List<BAR_PERFORMER_Post>> call, Throwable t) {

            }
        });


    }
}
