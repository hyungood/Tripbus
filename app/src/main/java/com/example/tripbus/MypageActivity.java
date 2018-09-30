package com.example.tripbus;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MypageActivity extends AppCompatActivity {
    Retrofit retrofit;
    APIservice apiService;
    private SessionManager session;
    TextView userId;
    GridView myBus, myPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        String id = user.get(SessionManager.IS_ID);

        if (Build.VERSION.SDK_INT >= 21) {
            // 21 버전 이상일 때
            getWindow().setStatusBarColor(Color.rgb(209, 239, 255));
        }

        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder().baseUrl(apiService.API_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        apiService = retrofit.create(APIservice.class);

        userId = (TextView) findViewById(R.id.mypage_id);
        userId.setText(id);
        myBus = (GridView) findViewById(R.id.mypage_bus);
        myPlace = (GridView) findViewById(R.id.mypage_place);
    }
}
