package com.example.tripbus;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface mapAPIservice {
    public static final String API_URL = "http://qkddmswl1234.cafe24.com/";
    @Headers({"Accept: application/json"})

    @FormUrlEncoded
    @POST("Recommend_bus_stop.php")
    Call<Bus> getBus(@Field("bus_line") String bus_num, @Field("stop") String[] stop,
                     @Field("stmt_lat") String[] lat, @Field("stmt_lng") String[] lng);



}