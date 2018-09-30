package com.example.tripbus;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GoodbyeActivity extends AppCompatActivity {
    Button yes, no;
    private SessionManager session;
    Retrofit retrofit;
    APIservice apiService;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodbye);

        session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        id = user.get(SessionManager.IS_ID);

        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder().baseUrl(apiService.API_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        apiService = retrofit.create(APIservice.class);

        yes = (Button) findViewById(R.id.button_yes);
        no = (Button) findViewById(R.id.button_no);

        yes.setOnClickListener(new View.OnClickListener() {
                    public void onClick( View v ) {
                        Call<userConnect> comment2 = apiService.goodbye(id);
                        comment2.enqueue(new Callback<userConnect>() {
                            @Override
                            public void onResponse(Call<userConnect> call, Response<userConnect> response) {
                                //Log.v("Test", response.body().string());
                                userConnect u = response.body();

                                if(u.getSuccess()) {
                                    session.logoutUser();
                                    Intent intent = new Intent(GoodbyeActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }

                            @Override
                            public void onFailure(Call<userConnect> call, Throwable t) {
                                Log.d("userRetrofit", "서버 통신에 오류가 있음");
                            }


                        });
                    }
                }
        );

        no.setOnClickListener(new View.OnClickListener() {
            public void onClick( View v ) {
                finish();
            }
            }
        );

    }
}
