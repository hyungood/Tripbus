package com.example.tripbus;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIservice {
    public static final String API_URL = "http://qkddmswl1234.cafe24.com/";
    @Headers({"Accept: application/json"})

    @FormUrlEncoded
    @POST("RegisterMember.php")
    Call<userConnect> register(@Field("user_id") String postId, @Field("user_pw") String postPw);

    @FormUrlEncoded //로그인
    @POST("RequestLogin.php")
    Call<userConnect> login(@Field("user_id") String postId, @Field("user_pw") String postPw);

    @FormUrlEncoded //탈퇴
    @POST("RequestLogin.php")
    Call<userConnect> goodbye(@Field("user_id") String postId);

    @FormUrlEncoded //유적지 타입
    @POST("recommendBus.php")
    Call<List<busNum>> busNum(@Field("dept") int busNum);

    @FormUrlEncoded
    @POST("tour.php") //장소정보
    Call<place> place(@Field("tour_idx") int tourIdx);

    @FormUrlEncoded
    @POST("review_list.php") //장소에 대한 리뷰정보
    Call<reviewList> review(@Field("tour_idx") int tourIdx);

    @FormUrlEncoded
    @POST("favor.php") //스크랩하기
    Call<userConnect> scrap(@Field("user_id") String postId);

}