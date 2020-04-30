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
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.enomfinal.R;
import com.example.enomfinal.activities.BarProfile;
import com.example.enomfinal.activities.PerformerBrowse.PerformerLayout;
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

public class Search_layout_barFragment extends Fragment {
    RecyclerView barRv,PerformerRv,PeopleRv;
    SuggestedBarAdapter suggestedBarAdapter;
    TopRatedSingerPerformerAdapter topRatedSingerPerformerAdapter;
    PeopleYouMayKnowAdapter peopleYouMayKnowAdapter;
    List<BARSFINAL> barsfinalList;
    List<PERFORMERFINAL> performerfinalList;
    TextView topratedbar,topratedperformer;

    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.search_layout_barfragment,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        topratedbar = view.findViewById(R.id.topratedtv);
        topratedperformer = view.findViewById(R.id.topratedpertv);

         barRv = view.findViewById(R.id.mainAct_topbarsRV);
         PerformerRv = view.findViewById(R.id.mainAct_topperformersRV);
//          PeopleRv = view.findViewById(R.id.mainAct_peopleRV);

          Call<List<BARSFINAL>> call = BarRetrofitClient.getInstance().getApi().getBarsSuggestedFinal();
          call.enqueue(new Callback<List<BARSFINAL>>() {
              @Override
              public void onResponse(Call<List<BARSFINAL>> call, Response<List<BARSFINAL>> response) {
                  LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(getActivity(),R.anim.layout_anim_falldown);
                  barRv.setLayoutAnimation(layoutAnimationController);
                 barsfinalList  = response.body();
                 if(barsfinalList.size() != 0) {
                     suggestedBarAdapter = new SuggestedBarAdapter(getActivity(), barsfinalList, new BarItemClickListener() {
                         @Override
                         public void onBarClick(BARSFINAL barsfinal, ImageView barImageView) {
                             Intent intent = new Intent(getActivity(), BarProfile.class);
                             intent.putExtra("intent_barName", barsfinal.getBar_name());
                             intent.putExtra("intent_barImage", barsfinal.getBar_photo());
                             intent.putExtra("intent_barId", barsfinal.getBar_id());
                             intent.putExtra("intent_barDesc", barsfinal.getBar_description());
                             intent.putExtra("intent_barType", barsfinal.getBar_type());
                             intent.putExtra("intent_barscore", barsfinal.getScore());
                             intent.putExtra("intent_barReviewsCount", barsfinal.getFeedback());
//                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Bar_Fragment.this.getActivity(),barImageView,"sharedName");
//                        getActivity().startActivityFromFragment(Bar_Fragment.this,intent,1,options.toBundle());
                             getActivity().startActivityFromFragment(Search_layout_barFragment.this, intent, 1);
                         }
                     });
                     topratedbar.setVisibility(View.VISIBLE);
                     barRv.setAdapter(suggestedBarAdapter);
                     barRv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                 }
              }

              @Override
              public void onFailure(Call<List<BARSFINAL>> call, Throwable t) {
                  Toast.makeText(getActivity(),t.getMessage()+"",Toast.LENGTH_LONG).show();
              }
          });


           Call<List<PERFORMERFINAL>> call2 = PerformerRetrofitClient.getInstance().getApi().getallperformersuggestedwithoutkey();
           call2.enqueue(new Callback<List<PERFORMERFINAL>>() {
               @Override
               public void onResponse(Call<List<PERFORMERFINAL>> call, Response<List<PERFORMERFINAL>> response) {

                   performerfinalList = response.body();
                   if(barsfinalList.size() != 0) {
                       topRatedSingerPerformerAdapter = new TopRatedSingerPerformerAdapter(getActivity(), performerfinalList, new PerformerItemClickListener() {
                           @Override
                           public void onPerfomerClick(PERFORMERFINAL performerfinal, ImageView performerImageView, TextView performerName) {
                               Intent inten = new Intent(getActivity(), PerformerProfile.class);
                               inten.putExtra("intent_performerName", performerfinal.getPerformer_name());
                               inten.putExtra("intent_performerImage", performerfinal.getE_photo());
                               inten.putExtra("intent_performerID", performerfinal.getPerformer_id());
                               inten.putExtra("intent_performerCategory", performerfinal.getPerformer_category());
                               inten.putExtra("intent_performerType", performerfinal.getPerformer_type());
                               inten.putExtra("intent_performerScore", performerfinal.getScore());
                               inten.putExtra("intent_performerRCount", performerfinal.getFeedback());
                               inten.putExtra("intent_performerBio", performerfinal.getPerformer_bio());
//                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(PerformerLayout.this,performerImageView,"sharedName");
//                startActivityForResult(intent,1,options.toBundle());
                               startActivityForResult(inten, 1);
                           }
                       });
                       topratedperformer.setVisibility(View.VISIBLE);
                       PerformerRv.setAdapter(topRatedSingerPerformerAdapter);
                       PerformerRv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                   }
               }
               @Override
               public void onFailure(Call<List<PERFORMERFINAL>> call, Throwable t) {

               }
           });
    }
}
