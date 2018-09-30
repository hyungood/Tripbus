package com.example.tripbus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.tripbus.MainActivity_navi.changeBtn;
import static com.facebook.FacebookSdk.getApplicationContext;

public class PathNature extends AppCompatActivity {
//이미지 배열 선언

//텍스트 배열 선언
Retrofit retrofit;
    APIservice apiService;

    ArrayList<String> busArr = new ArrayList<String>();

    ArrayList<Bitmap> picArr = new ArrayList<Bitmap>();
    ArrayList<String> textArr = new ArrayList<String>();


    GridView gridView1;
    GridView gridView2;
    ImageView icon;
    TextView typeName;

    Activity act = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_nature);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.path_toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setElevation(0);

        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder().baseUrl(apiService.API_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        apiService = retrofit.create(APIservice.class);

        Intent intent = getIntent();
        int type = intent.getIntExtra("type", 0);

        icon = (ImageView) findViewById(R.id.icon);
        typeName = (TextView) findViewById(R.id.typename);

        Drawable drawable = null;
        String tn = " ";
        switch (type) {
            case 1:
                drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.night2);
                tn = "야경 노선";
                break;
            case 2:
                drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.street2);
                tn = "번화가 노선";
                break;
            case 3:
                drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.nature2);
                tn = "자연적 노선";
                break;
            case 4:
                drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.history2);
                tn = "유적지 노선";
                break;
        }
        icon.setImageDrawable(drawable);
        typeName.setText(tn);


        for (int i = 0 ; i < 4 ; i++) {
            busArr.add("숫자" + Integer.toString(i));
            textArr.add("숫자" + Integer.toString(i));
        }

        Bitmap bm1 = BitmapFactory.decodeResource(getResources(), R.drawable.image_sample);
        Bitmap bm2 = BitmapFactory.decodeResource(getResources(), R.drawable.image_sample);
        Bitmap bm3 = BitmapFactory.decodeResource(getResources(), R.drawable.image_sample);
        Bitmap bm4 = BitmapFactory.decodeResource(getResources(), R.drawable.image_sample);


        picArr.add(bm1);
        picArr.add(bm2);
        picArr.add(bm3);
        picArr.add(bm4);

        getBusNum(type);

        gridView1 = (GridView) findViewById(R.id.gridView1);
        gridView1.setAdapter(new gridAdapter());

        gridView2 = (GridView) findViewById(R.id.gridView2);
        gridView2.setAdapter(new gridAdapter2());


    }


    public void getBusNum(final int type) {
        Call<List<busNum>> comment2 = apiService.busNum(type);
        comment2.enqueue(new Callback<List<busNum>>() {
            @Override
            public void onResponse(Call<List<busNum>> call, Response<List<busNum>> response) {
                Log.d("Type", type + " ");
                Log.d("Test", response.body().toString());
                List<busNum> bs = response.body();

                ArrayList<String> bus = new ArrayList<String>();

               for(busNum b : bs) {
                   //bus.add(b.getBusNum());
                   busArr.add(b.getBusNum());
                 //Log.d("busnum", b.getBusNum());
            }


            }

            @Override
            public void onFailure(Call<List<busNum>> call, Throwable t) {
                Log.d("userRetrofit", "서버 통신에 오류가 있음");
            }


        });

    }



    public class gridAdapter extends BaseAdapter {

        LayoutInflater inflater;



        public gridAdapter() {

            inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }



        @Override

        public int getCount() {

// TODO Auto-generated method stub

            return busArr.size();    //그리드뷰에 출력할 목록 수

        }



        @Override

        public Object getItem(int position) {

// TODO Auto-generated method stub

            return busArr.get(position);    //아이템을 호출할 때 사용하는 메소드

        }



        @Override

        public long getItemId(int position) {

// TODO Auto-generated method stub

            return position;    //아이템의 아이디를 구할 때 사용하는 메소드

        }



        @Override

        public View getView(int position, View convertView, ViewGroup parent) {

// TODO Auto-generated method stub

            if(convertView == null) {

                convertView = inflater.inflate(R.layout.bus_num_item, parent, false);

            }



            Button btn= (Button) convertView.findViewById(R.id.btn1);



            btn.setText(busArr.get(position));


            btn.setOnClickListener(new View.OnClickListener() {


                @Override

                public void onClick(View v) {
// TODO Auto-generated method stub
                    Intent intent = new Intent(getApplicationContext(), BusPathActivity.class);
                    startActivity(intent);

                }

            });


            return convertView;

        }



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                /*
                finish();
                return true;
                */
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public class gridAdapter2 extends BaseAdapter {

        LayoutInflater inflater;



        public gridAdapter2() {

            inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }



        @Override

        public int getCount() {

// TODO Auto-generated method stub

            return picArr.size();    //그리드뷰에 출력할 목록 수

        }



        @Override

        public Object getItem(int position) {

// TODO Auto-generated method stub

            return picArr.get(position);    //아이템을 호출할 때 사용하는 메소드

        }



        @Override

        public long getItemId(int position) {

// TODO Auto-generated method stub

            return position;    //아이템의 아이디를 구할 때 사용하는 메소드

        }



        @Override

        public View getView(int position, View convertView, ViewGroup parent) {

// TODO Auto-generated method stub

            if(convertView == null) {

                convertView = inflater.inflate(R.layout.rcmd_place_item, parent, false);

            }


            ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView1);

            TextView textView = (TextView) convertView.findViewById(R.id.textView1);


            imageView.setImageBitmap(picArr.get(position));

            textView.setText(textArr.get(position));


            imageView.setOnClickListener(new View.OnClickListener() {


                @Override

                public void onClick(View v) {

// TODO Auto-generated method stub

//이미지를 터치했을때 동작하는 곳

                }

            });


            return convertView;

        }



    }

}
