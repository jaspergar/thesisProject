package com.example.enomfinal.fragments.BottomNavPerformerFragments;

import android.app.ActivityOptions;
import android.app.SearchManager;
import android.app.UiAutomation;
import android.content.Context;
import android.content.Intent;
import android.icu.text.Transliterator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.enomfinal.R;
import com.example.enomfinal.activities.BarProfile;
import com.example.enomfinal.adapters.BarFragmentAdapters.NotsuggestedBarAdapter;
import com.example.enomfinal.adapters.BarFragmentAdapters.SuggestedBarAdapter;
import com.example.enomfinal.api.BarRetrofitClient;
import com.example.enomfinal.api.PerformerRetrofitClient;
import com.example.enomfinal.fragments.SearchFragments.Bar_Fragment;
import com.example.enomfinal.interfaces.BarItemClickListener;
import com.example.enomfinal.models.BARSFINAL;
import com.example.enomfinal.models.BarResponse;
import com.example.enomfinal.models.BarResponseFINAL;
import com.example.enomfinal.models.Bars;
import com.example.enomfinal.storage.SharedPrefManager;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Performer_BrowseFragment extends Fragment {
    private List<Bars> barList;
    private List<BARSFINAL> barsfinalList;
    private SuggestedBarAdapter adapter;
    private NotsuggestedBarAdapter notadapter;
    private RecyclerView suggestedbarRecyclerV,notsuggestedbarRecyclerV,searchrv;
    private TextView suggested,notsuggested,suggestedsearch;


    int  thefollowed;
    int theuser;
    String thefollower;

    Button sugfollow,sugunfollow,notsugfollow,notsugunfollow;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.performer_act_browse_fragment,container,false);

        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        theuser = SharedPrefManager.getInstance(getActivity()).getUser().getE_id();
        thefollower = SharedPrefManager.getInstance(getActivity()).getUser().getE_type();

//        sugfollow = view.findViewById(R.id.suggested_barfollow_id);
//        sugunfollow = view.findViewById(R.id.suggested_barunfollow_id);
//
//        notsugfollow = view.findViewById(R.id.notsuggested_barFollow_id);
//        notsugunfollow = view.findViewById(R.id.notsuggested_barunFollow_id);


//        Toolbar toolbar = view.findViewById(R.id.performeract_side_toolbar_id);
//        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);




//        searchrv = view.findViewById(R.id.performeract_searchrv);
        suggestedbarRecyclerV = view.findViewById(R.id.performeract_browse_sug_rv);
        notsuggestedbarRecyclerV = view.findViewById(R.id.performeract_notsuggestedBarRecyclerView_id);

        notsuggested = view.findViewById(R.id.performeract_tv_notsug_id);
        suggested = view.findViewById(R.id.performeract_tv_sug_id);
//        suggestedsearch = view.findViewById(R.id.perfomeract_suggestedTVsearch);
        final ProgressBar suggestedbarProgressBar = view.findViewById(R.id.performeract_progressbar_suggestedBar);
        suggestedbarProgressBar.setIndeterminate(true);
        suggestedbarProgressBar.setVisibility(View.VISIBLE);

//suggested bar
        Call<List<BARSFINAL>> call = BarRetrofitClient.getInstance().getApi().getBarsSuggestedFinal();
        call.enqueue(new Callback<List<BARSFINAL>>() {
            @Override
            public void onResponse(Call<List<BARSFINAL>> call, Response<List<BARSFINAL>> response) {
                final String enomerType = SharedPrefManager.getInstance(getActivity()).getUser().getE_type();
                suggestedbarProgressBar.setVisibility(View.GONE);
                LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(getActivity(),R.anim.layout_anim_falldown);
                suggestedbarRecyclerV.setLayoutAnimation(layoutAnimationController);
                barsfinalList = response.body();


                adapter = new SuggestedBarAdapter(getActivity(), barsfinalList, new BarItemClickListener() {
                    @Override
                    public void onBarClick(BARSFINAL barsfinal, ImageView barImageView) {
                        Intent intent = new Intent(getActivity(), BarProfile.class);
                        intent.putExtra("intent_enomerType",enomerType);
                        intent.putExtra("intent_barName",barsfinal.getBar_name());
                        intent.putExtra("intent_barImage",barsfinal.getBar_photo());
                        intent.putExtra("intent_barId",barsfinal.getBar_id());
//                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Bar_Fragment.this.getActivity(),barImageView,"sharedName");
//                        getActivity().startActivityFromFragment(Bar_Fragment.this,intent,1,options.toBundle());
                        getActivity().startActivityFromFragment(Performer_BrowseFragment.this,intent,1);
                    }


                });
                suggestedbarRecyclerV.setAdapter(adapter);
                suggestedbarRecyclerV.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));




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
                 final String enomerType = SharedPrefManager.getInstance(getActivity()).getUser().getE_type();
                //  suggestedbarProgressBar.setVisibility(View.GONE);
                LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(getActivity(),R.anim.layout_anim_falldown);
                notsuggestedbarRecyclerV.setLayoutAnimation(layoutAnimationController);
                barsfinalList = response.body();
                notadapter = new NotsuggestedBarAdapter(getActivity(), barsfinalList, new BarItemClickListener() {
                    @Override
                    public void onBarClick(BARSFINAL barsfinal, ImageView barImageView) {
                        Intent intent = new Intent(getActivity(), BarProfile.class);
                        intent.putExtra("intent_enomerType",enomerType);
                        intent.putExtra("intent_barName",barsfinal.getBar_name());
                        intent.putExtra("intent_barImage",barsfinal.getBar_photo());
                        intent.putExtra("intent_barId",barsfinal.getBar_id());
//                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Performer_BrowseFragment.this.getActivity(),barImageView,"sharedName");
//                        getActivity().startActivityFromFragment(Performer_BrowseFragment.this,intent,1,options.toBundle());
                        getActivity().startActivityFromFragment(Performer_BrowseFragment.this,intent,1);
                    }
                });
                notsuggestedbarRecyclerV.setAdapter(notadapter);
                notsuggestedbarRecyclerV.setLayoutManager(new LinearLayoutManager(getActivity()));

            }
            @Override
            public void onFailure(Call<List<BARSFINAL>> call, Throwable t) {
                suggestedbarProgressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });


//        searchBars("");

    }
    //search function
        public void searchBars(String key){
            Call<List<BARSFINAL>> call3 = PerformerRetrofitClient.getInstance().getApi().performersearchBar(key);
            call3.enqueue(new Callback<List<BARSFINAL>>() {
                @Override
                public void onResponse(Call<List<BARSFINAL>> call, Response<List<BARSFINAL>> response) {
                  barsfinalList = response.body();

                    notadapter = new NotsuggestedBarAdapter(getActivity(),barsfinalList , new BarItemClickListener() {
                        @Override
                        public void onBarClick(BARSFINAL barsfinal, ImageView barImageView) {
                            Intent intent = new Intent(getActivity(), BarProfile.class);
                            intent.putExtra("intent_barName",barsfinal.getBar_name());
                            intent.putExtra("intent_barImage",barsfinal.getBar_photo());
                            intent.putExtra("intent_barId",barsfinal.getBar_id());
//                            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Performer_BrowseFragment.this.getActivity(),barImageView,"sharedName");
//                            getActivity().startActivityFromFragment(Performer_BrowseFragment.this,intent,1,options.toBundle());
                        getActivity().startActivityFromFragment(Performer_BrowseFragment.this,intent,1);
                        }
                    });
                    searchrv.setAdapter(notadapter);
                    searchrv.setLayoutManager(new LinearLayoutManager(getActivity()));
                    notadapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<List<BARSFINAL>> call, Throwable t) {

                }
            });
        }


    //not suggested -- when the data on recycler view change the animation will re run
    private void rerunAnimation(RecyclerView notsuggestedbarRecyclerV){
        Context context = notsuggestedbarRecyclerV.getContext();

        LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(getActivity(),R.anim.layout_anim_falldown);
        notsuggestedbarRecyclerV.setLayoutAnimation(layoutAnimationController);
        notsuggestedbarRecyclerV.getAdapter().notifyDataSetChanged();
        notsuggestedbarRecyclerV.scheduleLayoutAnimation();
    }




//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.search_menu, menu);
//
//        MenuItem menuItem = menu.findItem(R.id.action_search);
//        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
//
//        searchView.setSearchableInfo(
//                searchManager.getSearchableInfo(getActivity().getComponentName())
//        );
//        searchView.setIconifiedByDefault(false);
//        searchView.setOnSearchClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                searchrv.setVisibility(View.VISIBLE);
//                suggestedsearch.setVisibility(View.VISIBLE);
//                notsuggestedbarRecyclerV.setVisibility(View.GONE);
//                suggestedbarRecyclerV.setVisibility(View.GONE);
//                suggested.setVisibility(View.GONE);
//                notsuggested.setVisibility(View.GONE);
//            }
//        });
//
//     menuItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
//         @Override
//         public boolean onMenuItemActionExpand(MenuItem menuItem) {
//             return true;
//         }
//
//         @Override
//         public boolean onMenuItemActionCollapse(MenuItem menuItem) {
//             searchrv.setVisibility(View.GONE);
//             suggestedsearch.setVisibility(View.GONE);
//             notsuggestedbarRecyclerV.setVisibility(View.VISIBLE);
//             suggestedbarRecyclerV.setVisibility(View.VISIBLE);
//             suggested.setVisibility(View.VISIBLE);
//             notsuggested.setVisibility(View.VISIBLE);
//             return true;
//         }
//     });
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                searchBars(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                searchBars(newText);
//                return false;
//            }
//        });
//
//
////        materialSearchView.setMenuItem(item);
//
//    }

}
