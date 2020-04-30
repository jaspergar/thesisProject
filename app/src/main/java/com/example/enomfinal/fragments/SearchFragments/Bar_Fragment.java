package com.example.enomfinal.fragments.SearchFragments;

import android.app.ActivityOptions;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.enomfinal.R;
import com.example.enomfinal.activities.BarProfile;
import com.example.enomfinal.adapters.BarFragmentAdapters.NotsuggestedBarAdapter;
import com.example.enomfinal.adapters.BarFragmentAdapters.SuggestedBarAdapter;
import com.example.enomfinal.api.BarRetrofitClient;
import com.example.enomfinal.api.PerformerRetrofitClient;
import com.example.enomfinal.fragments.BottomNavPerformerFragments.Performer_BrowseFragment;
import com.example.enomfinal.interfaces.BarItemClickListener;
//import com.example.enomfinal.models.Bar;
import com.example.enomfinal.models.BARSFINAL;
import com.example.enomfinal.models.BarResponse;
import com.example.enomfinal.models.BarResponseFINAL;
import com.example.enomfinal.models.Bars;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Bar_Fragment extends Fragment {

    private List<Bars> barList;
    private List<BARSFINAL> barsfinalList;
    private SuggestedBarAdapter adapter;
    private NotsuggestedBarAdapter notadapter;
    private RecyclerView suggestedbarRecyclerV,notsuggestedbarRecyclerV,searchrv;
    private TextView suggested,notsuggested,suggestedsearch;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.bar_fragment,container,false);
    return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        suggestedbarRecyclerV = view.findViewById(R.id.SuggestedBarRecyclerView_id);
        notsuggestedbarRecyclerV = view.findViewById(R.id.SearchBarRecyclerView_id);
//        searchrv = view.findViewById(R.id.barfragment_searchrv);

//        notsuggested = view.findViewById(R.id.barfragment_suggestedTVsearch);
//        suggested = view.findViewById(R.id.tv_sug_id);
//        suggestedsearch = view.findViewById(R.id.tv_notsug_id);

        final ProgressBar suggestedbarProgressBar = view.findViewById(R.id.progressbar_suggestedBar);
        suggestedbarProgressBar.setIndeterminate(true);
        suggestedbarProgressBar.setVisibility(View.VISIBLE);

//suggested bar
        Call<List<BARSFINAL>> call = BarRetrofitClient.getInstance().getApi().getBarsSuggestedFinal();
        call.enqueue(new Callback<List<BARSFINAL>>() {
            @Override
            public void onResponse(Call<List<BARSFINAL>> call, Response<List<BARSFINAL>> response) {

                suggestedbarProgressBar.setVisibility(View.GONE);
                LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(getActivity(),R.anim.layout_anim_falldown);
                suggestedbarRecyclerV.setLayoutAnimation(layoutAnimationController);
                barsfinalList = response.body();
                      adapter = new SuggestedBarAdapter(getActivity(), barsfinalList, new BarItemClickListener() {
                    @Override
                    public void onBarClick(BARSFINAL barsfinal, ImageView barImageView) {
                        Intent intent = new Intent(getActivity(), BarProfile.class);
                        intent.putExtra("intent_barName",barsfinal.getBar_name());
                        intent.putExtra("intent_barImage",barsfinal.getBar_photo());
                        intent.putExtra("intent_barId",barsfinal.getBar_id());
                        intent.putExtra("intent_barDesc",barsfinal.getBar_description());
                        intent.putExtra("intent_barType",barsfinal.getBar_type());
                        intent.putExtra("intent_barscore",barsfinal.getScore());
                        intent.putExtra("intent_barReviewsCount",barsfinal.getFeedback());


//                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Bar_Fragment.this.getActivity(),barImageView,"sharedName");
//                        getActivity().startActivityFromFragment(Bar_Fragment.this,intent,1,options.toBundle());
                        getActivity().startActivityFromFragment(Bar_Fragment.this,intent,1);
                    }
                });
                suggestedbarRecyclerV.setAdapter(adapter);
                suggestedbarRecyclerV.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
                   adapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<BARSFINAL>> call, Throwable t) {
                suggestedbarProgressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });



// not suggested bar
        Call<List<BARSFINAL>> call2 = BarRetrofitClient.getInstance().getApi().getBarsFinal();
        call2.enqueue(new Callback<List<BARSFINAL>>() {
            @Override
            public void onResponse(Call<List<BARSFINAL>> call2, Response<List<BARSFINAL>> response) {

              //  suggestedbarProgressBar.setVisibility(View.GONE);
                LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(getActivity(),R.anim.layout_anim_falldown);
                notsuggestedbarRecyclerV.setLayoutAnimation(layoutAnimationController);
                barsfinalList = response.body();
                notadapter = new NotsuggestedBarAdapter(getActivity(), barsfinalList, new BarItemClickListener() {
                    @Override
                    public void onBarClick(BARSFINAL barsfinal, ImageView barImageView) {
//                        Toast.makeText(getActivity(),bars.getBar_id()+"",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getActivity(), BarProfile.class);
                        intent.putExtra("intent_barName",barsfinal.getBar_name());
                        intent.putExtra("intent_barId",barsfinal.getBar_id());
                        intent.putExtra("intent_barImage",barsfinal.getBar_photo());
                        intent.putExtra("intent_barDesc",barsfinal.getBar_description());
                        intent.putExtra("intent_barType",barsfinal.getBar_type());
                        intent.putExtra("intent_barscore",barsfinal.getScore());
                        intent.putExtra("intent_barReviewsCount",barsfinal.getFeedback());
                        getActivity().startActivityFromFragment(Bar_Fragment.this,intent,1);
                    }
                });
                notsuggestedbarRecyclerV.setAdapter(notadapter);
                notsuggestedbarRecyclerV.setLayoutManager(new LinearLayoutManager(getActivity()));
                notadapter.notifyDataSetChanged();

            }
            @Override
            public void onFailure(Call<List<BARSFINAL>> call, Throwable t) {
                suggestedbarProgressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

//        searchBars("");
    }


//not suggested -- when the data on recycler view change the animation will re run
    private void rerunAnimation(RecyclerView notsuggestedbarRecyclerV){
        Context context = notsuggestedbarRecyclerV.getContext();

        LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(getActivity(),R.anim.layout_anim_falldown);
        notsuggestedbarRecyclerV.setLayoutAnimation(layoutAnimationController);
        notsuggestedbarRecyclerV.getAdapter().notifyDataSetChanged();
        notsuggestedbarRecyclerV.scheduleLayoutAnimation();
    }








}
