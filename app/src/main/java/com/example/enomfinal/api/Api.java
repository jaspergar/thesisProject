package com.example.enomfinal.api;

import com.example.enomfinal.models.BARSFINAL;
import com.example.enomfinal.models.BAR_PERFORMER_Post;
import com.example.enomfinal.models.BarGigs;
import com.example.enomfinal.models.BarId;
import com.example.enomfinal.models.BarResponse;
import com.example.enomfinal.models.BarResponseFINAL;
import com.example.enomfinal.models.Bars;
import com.example.enomfinal.models.DefaultResponse;
import com.example.enomfinal.models.EventModel;
import com.example.enomfinal.models.FinalBarSchedNotif;
import com.example.enomfinal.models.FinalPerformerSchedNotif;
import com.example.enomfinal.models.LoginResponse;
import com.example.enomfinal.models.MyInvites;
import com.example.enomfinal.models.Notification;
import com.example.enomfinal.models.Offer;
import com.example.enomfinal.models.PERFORMERFINAL;
import com.example.enomfinal.models.PerformerProfile;
import com.example.enomfinal.models.Reviews;
import com.example.enomfinal.models.UserResponse;
import com.example.enomfinal.models.Users;
//import com.example.enomfinal.models.UsersResponse;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.Call;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    @FormUrlEncoded
    @POST("createuser")
    Call<DefaultResponse> createUser(
            @Field("e_email") String e_email,
            @Field("e_password") String e_password,
            @Field("e_fname") String e_fname,
            @Field("e_lname") String e_lname,
            @Field("e_cnumber") int e_cnumber,
            @Field("e_gender") String e_gender,
            @Field("e_dob") String e_dob,
            @Field("e_type") String e_type


    );

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> loginUser(
            @Field("e_email") String e_email,
            @Field("e_password") String e_password
    );
//
//    @GET("getalluser")
//    Call<UsersResponse> getUsers();
//delitonon
    @GET("getallbar")
    Call<BarResponse> getBars();

     @GET("getallbars")
    Call<List<BARSFINAL>> getBarsFinal();

    @GET("getallbarswithId")
    Call<BARSFINAL> getBarsWithIdFinal(@Query("bar_id") int bar_id);

    @GET("getallbarsSuggested")
    Call<List<BARSFINAL>> getBarsSuggestedFinal();

    @GET("getalluserpartygoer")
    Call<UserResponse> getUsers(@Query("e_id") int e_id);

    @GET("getallpartygoerwithId")
    Call<Users> getPartygoerWithId( @Query("e_id") int e_id);

    @GET("getpprofileById")
    Call<PerformerProfile> getPerformerProfile(
            @Query("enomer_id") int enomer_id
//            @Path("enomer_id") int enomer_id
    );

    @GET("getallperformer")
    Call<List<PERFORMERFINAL>> getallperformer(@Query("category")String category,@Query("type")String type);
    @GET("getallperformersuggested")
    Call<List<PERFORMERFINAL>> getallperformersuggested(@Query("category")String category,@Query("type")String type);
    @GET("getperformerById")
    Call<PERFORMERFINAL> getperformerById(@Query("performer_id")int performer_id);

    @GET("getallperformersuggestedwithoutkey")
    Call<List<PERFORMERFINAL>> getallperformersuggestedwithoutkey();

    @FormUrlEncoded
    @POST("addPerformerProfile")
    Call<DefaultResponse> addPerformerProfile(
            @Field("enomer_id") int enomer_id,
            @Field("performer_name") String performer_name,
            @Field("performer_bio") String performer_bio,
            @Field("performer_category") String performer_category,
            @Field("performer_type") String performer_type
//            @Field("performer_member_id") int performer_member_id
    );

    @FormUrlEncoded
    @POST("addMember")
    Call<PerformerProfile> addMember(
            @Field("enomer_id") int enomer_id,
            @Field("member_fname") String member_fname,
            @Field("member_lname") String member_lname,
            @Field("member_gender") String member_gender,
            @Field("member_role") String member_role
    );

    @GET("performerbarsearch")
    Call<List<BARSFINAL>> performersearchBar(@Query("key")String key);


    @FormUrlEncoded
    @POST("addBarRating")
    Call<DefaultResponse> AddBarRating(
        @Field("bar_id") int bar_id,
        @Field("enomer_id") int enomer_id,
        @Field("score") float score,
        @Field("feedback") String feedback
    );

    @GET("isRated")
    Call<DefaultResponse> isRated(@Query("enomer_id")int enomer_id,@Query("bar_id")int bar_id);

    @FormUrlEncoded
    @POST("addPerformerRating")
    Call<DefaultResponse> AddPerformerRating(
            @Field("performer_id") int performer_id,
            @Field("enomer_id") int enomer_id,
            @Field("score") float score,
            @Field("feedback") String feedback
    );

    @GET("isRatedPerformer")
    Call<DefaultResponse> isRatedPerformer(@Query("enomer_id")int enomer_id,@Query("performer_id")int performer_id);

    @GET("isFollowed")
    Call<DefaultResponse> isFollowed(@Query("thefollowed")int thefollowed,@Query("TheUser")int TheUser);



    @FormUrlEncoded
    @POST("follow")
    Call<DefaultResponse> follow(
            @Field("thefollowed") int thefollowed_id,
            @Field("followedtype") String thefollowed_type,
            @Field("followertype") String thefollower_type,
            @Field("TheUser") int theuser,
            @Field("status") String status,
            @Field("content") String content,
            @Field("notif_type") String notif_type,
            @Field("notif_from_type") String notif_from_type,
            @Field("notif_to_type") String notif_to_type

    );

    @FormUrlEncoded
    @POST("unfollow")
    Call<DefaultResponse> unfollow(
            @Field("thefollowed") int thefollowed_id,
            @Field("TheUser") int theuser
    );

    @Multipart
    @POST("addpost")
    Call<DefaultResponse> addpost(@Part MultipartBody.Part file, @Part("post_media") RequestBody name);

    @FormUrlEncoded
    @POST("addpostTemp")
    Call<DefaultResponse> addpostTemp(@Field("id") int id, @Field("photo") String photo,@Field("post_description") String post_desc, @Field("post_type") String post_type);

    @GET("ratingReviews")
    Call<List<Reviews>> getReviewsRating(@Query("bar_id")int bar_id);

    @GET("performerratingReviews")
    Call<List<Reviews>> getPerformerReviewsRating(@Query("performer_id")int performer_id);

    @GET("retrievenotifFollow")
    Call<List<Notification>> retrievenotifFollow(@Query("enomer_id")int enomer_id);

    @GET("isSeen")
    Call<DefaultResponse> isSeen(@Query("TheUser")int TheUser , @Query("thefollower") int thefollower);

    @PUT("notifUpdateStatus")
    Call<DefaultResponse> notifUpdateStatus(@Query("TheUser")int TheUser , @Query("thefollower") int thefollower);


    @GET("getPost")
    Call<List<BAR_PERFORMER_Post>> getPost(@Query("enomer_id")int enomer_id);

    @GET("getOnceSchedule")
    Call<List<BarGigs>> getOnceSched(@Query("performer_id")int performer_id);


    @PUT("apply")
    Call<DefaultResponse> apply(@Query("bar_id")int bar_id , @Query("enomer_id") int enomer_id,
                                @Query("status")String status , @Query("content") String content,
                                @Query("notif_type")String notif_type , @Query("notif_from_type") String notif_from_type,
                                @Query("notif_to_type")String notif_to_type , @Query("sched_date_id") int sched_date_id
                                );

    @PUT("accept")
    Call<DefaultResponse> accept(@Query("bar_id")int bar_id , @Query("enomer_id") int enomer_id,
                                @Query("status")String status , @Query("content") String content,
                                @Query("notif_type")String notif_type , @Query("notif_from_type") String notif_from_type,
                                @Query("notif_to_type")String notif_to_type , @Query("sched_date_id") int sched_date_id
    );

    @GET("isYouApplied")
    Call<DefaultResponse> isYouApplied(@Query("bar_id")int bar_id , @Query("enomer_id") int enomer_id);

    @PUT("cancelApply")
    Call<DefaultResponse> cancelApply(@Query("bar_id")int bar_id , @Query("enomer_id") int enomer_id,
                                @Query("status")String status , @Query("content") String content,
                                @Query("notif_type")String notif_type , @Query("notif_from_type") String notif_from_type,
                                @Query("notif_to_type")String notif_to_type , @Query("sched_date_id") int sched_date_id
    );

    @GET("getalloffer")
    Call<List<Offer>> getOffers(@Query("bar_id")int bar_id);

    @GET("getWeeklySchedule")
    Call<List<BarGigs>> getWeeklySched(@Query("performer_id")int performer_id);

    @GET("getallweeklyScheduleBarTimeMonday")
    Call<List<BarGigs>> getWeeklyScheduleBarTime(@Query("performer_id")int performer_id);
    @GET("getallweeklyScheduleBarTimeTuesday")
    Call<List<BarGigs>> getWeeklyScheduleBarTimeT(@Query("performer_id")int performer_id);
    @GET("getallweeklyScheduleBarTimeWednsday")
    Call<List<BarGigs>> getWeeklyScheduleBarTimeW(@Query("performer_id")int performer_id);
    @GET("getallweeklyScheduleBarTimeThursday")
    Call<List<BarGigs>> getWeeklyScheduleBarTimeTH(@Query("performer_id")int performer_id);
    @GET("getallweeklyScheduleBarTimeFriday")
    Call<List<BarGigs>> getWeeklyScheduleBarTimeF(@Query("performer_id")int performer_id);
    @GET("getallweeklyScheduleBarTimeSaturday")
    Call<List<BarGigs>> getWeeklyScheduleBarTimeSA(@Query("performer_id")int performer_id);
    @GET("getallweeklyScheduleBarTimeSunday")
    Call<List<BarGigs>> getWeeklyScheduleBarTimeSU(@Query("performer_id")int performer_id);

    @GET("retrievenotifPerformerSched")
    Call<List<FinalPerformerSchedNotif>> getnotifPerformerSched(@Query("enomer_id")int enomer_id);

    @GET("retrievenotifBarSched")
    Call<List<FinalBarSchedNotif>> getnotifBarrSched(@Query("enomer_id")int enomer_id);

    @GET("getInvitePerformer")
    Call<List<MyInvites>> getInvitePerformer(@Query("performer_id")int performer_id);

}

