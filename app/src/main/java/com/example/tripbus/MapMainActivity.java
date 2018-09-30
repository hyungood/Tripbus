package com.example.tripbus;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.tripbus.APIservice.API_URL;

public class MapMainActivity extends AppCompatActivity {


    private DrawerLayout drawerLayout;//drawerLayout은 navigationView를 감싸는 큰 뷰
    private Toolbar toolbar;
    private NavigationView navigationView;
    private Button btnShowNavigationDrawer;//버튼 누르면 navigationView가 나옴
    MainFragment mainFragment;

    //ActionBarDrawerToggle 요녀석 android.support.v7꺼 써야한다.
    //android.support.v4는 이제 사용 안하니까... deprecated대써...
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_main);

        if (savedInstanceState == null) {

            mainFragment = new MainFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mainFragment, mainFragment, "main")
                    .commit();
        }//fragment에 구글지도 넣기위한 단계.



        //Style에서 NoActionBar로 ActionBar를 Disable 시켰으니
        //우리가 ActionBar처럼 사용 할 toolbar를 ActionBar처럼
        //사용하기위해 setSupportActionBar에 설정해준다.
        //주의 할 점은 xml에 <include>의 id를 가져와서 설정하는것에 유의
        toolbar = (Toolbar) findViewById(R.id.toolbarInclude);
        setSupportActionBar(toolbar);

        //여기서 setContentView로 설정되어있는건 activity_main이므로
        //toolbar에 구현해둔 컴포넌트를 findViewById로 가져오기위해
        //toolbar.findViewById로 찾아준다
        btnShowNavigationDrawer = (Button)findViewById(R.id.btnShowNavigationDrawer);
        btnShowNavigationDrawer.setOnClickListener(onClickListener);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = setUpActionBarToggle();
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        navigationView = (NavigationView) findViewById(R.id.navigationView);
        setUpDrawerContent(navigationView);
        navigationView.setItemIconTintList(null);
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }
 //클릭한 뷰가 >이면 네비게이션 뷰가 열림
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnShowNavigationDrawer:
                    drawerLayout.openDrawer(GravityCompat.START);
                    break;
            }
        }
    };
//setUpDrawerContent 는 네비게이션 바 내의 메뉴 항목을 클릭했을 시 반응하는 메소드
    private void setUpDrawerContent(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.first_navigation_item:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        mainFragment.mapMoveTo("37.5913145", "127.0199425");
//                        getSupportFragmentManager().beginTransaction()
//                                .replace(R.id.mainFragment, mapFrag, "main")
////                               .commit();
                        break;
                    case R.id.second_navigation_item:
//                       mainFragment.mapMoveTo(301, 4);
                        break;
                    case R.id.third_navigation_item:
                        Toast.makeText(MapMainActivity.this,"세번째 Navigation Item 입니다", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.four_navigation_item:
                        Toast.makeText(MapMainActivity.this,"세번째 Navigation Item 입니다", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.five_navigation_item:
                        Toast.makeText(MapMainActivity.this,"세번째 Navigation Item 입니다", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
    }

    private ActionBarDrawerToggle setUpActionBarToggle(){
        return new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    }



//    public void post() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(API_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        APIservice retrofitService = retrofit.create(APIservice.class);
//        Call<Bus> getBus = retrofitService.getBus();
//        getBus.enqueue(new Callback<Bus>() {
//            @Override
//            public void onResponse(Call<Bus> call, Response<Bus> response) {
//                Bus repo = response.body();
//                textViewPost.setText(repo.getOutput());
//            }
//
//            @Override
//            public void onFailure(Call<Bus> call, Throwable t) {
//
//            }
//        });
    }



