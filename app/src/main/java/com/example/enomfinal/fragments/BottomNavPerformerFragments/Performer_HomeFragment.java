package com.example.enomfinal.fragments.BottomNavPerformerFragments;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.enomfinal.R;
import com.example.enomfinal.activities.PerformerLayout.AddPost;
import com.example.enomfinal.activities.ViewPost;
import com.example.enomfinal.adapters.EnomerPostAdapter;
import com.example.enomfinal.api.EnomerRetrofitClient;
import com.example.enomfinal.fragments.BottomNavFragments.HomeFragment;
import com.example.enomfinal.interfaces.PostItemClickLisener;
import com.example.enomfinal.models.BAR_PERFORMER_Post;
import com.example.enomfinal.storage.SharedPrefManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.internal.NavigationMenu;

import java.util.ArrayList;
import java.util.List;

import io.github.yavski.fabspeeddial.FabSpeedDial;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//public class Performer_HomeFragment extends Fragment implements View.OnClickListener {
public class Performer_HomeFragment extends Fragment {
    private RecyclerView performerhomeRV;
    private EnomerPostAdapter enomerPostAdapter;
    private List<BAR_PERFORMER_Post> bar_performer_postList;
    private PostItemClickLisener postItemClickLisener;

    private FloatingActionButton floatingActionButton;
    private FabSpeedDial fabSpeedDial;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.performer_act_home_fragment,container,false);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        floatingActionButton = view.findViewById(R.id.performeract_home_addpostbtn);

//        floatingActionButton.setOnClickListener(this);

        fabSpeedDial = view.findViewById(R.id.performeract_home_addpostbtn);
        fabSpeedDial.setMenuListener(new FabSpeedDial.MenuListener() {
            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
                return true;
            }

            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                String image = "With Image Post";
                String video = "With Video Post";

                if(menuItem.getTitle().equals(image)){
                    int enomerid = SharedPrefManager.getInstance(getActivity()).getUser().getE_id();
                    Intent intent= new Intent(getActivity(), AddPost.class);
                    intent.putExtra("enomerid",enomerid);
                    intent.putExtra("posttype","Status");
                    startActivity(intent);
                }else if(menuItem.getTitle().equals(video)){
                   Toast.makeText(getActivity(),menuItem.getTitle()+"",Toast.LENGTH_LONG).show();
                }
                return true;
            }

            @Override
            public void onMenuClosed() {

            }
        });


        performerhomeRV = view.findViewById(R.id.performerhomeRV);

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
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Performer_HomeFragment.this.getActivity(),PostImageView,"sharedName");
                        getActivity().startActivityFromFragment(Performer_HomeFragment.this,intent,1,options.toBundle());
                    }
                });

                performerhomeRV.setAdapter(enomerPostAdapter);
                performerhomeRV.setLayoutManager(new LinearLayoutManager(getActivity()));
            }

            @Override
            public void onFailure(Call<List<BAR_PERFORMER_Post>> call, Throwable t) {

            }
        });



    }

//    @Override
//    public void onClick(View view) {
//        int enomerid = SharedPrefManager.getInstance(getActivity()).getUser().getE_id();
//        if(view.getId() == R.id.performeract_home_addpostbtn){
//            Intent intent= new Intent(getActivity(), AddPost.class);
//            intent.putExtra("enomerid",enomerid);
//            intent.putExtra("posttype","Status");
//            startActivity(intent);
//        }
//    }

}
