package com.example.tripbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity_navi extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    static Button login;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("");
        getSupportActionBar().setElevation(0);

        session = new SessionManager(getApplicationContext());


        int type;
        ImageButton btn1 = (ImageButton)findViewById(R.id.rcmnd_nature);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PathNature.class);
                intent.putExtra("type", 3);
                startActivity(intent);
            }
        });
        ImageButton btn2 = (ImageButton)findViewById(R.id.rcmnd_culture);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PathNature.class);
                intent.putExtra("type", 4);
                startActivity(intent);
            }
        });
        ImageButton btn3 = (ImageButton)findViewById(R.id.rcmnd_downtown);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PathNature.class);
                intent.putExtra("type", 2);
                startActivity(intent);
            }
        });
        ImageButton btn4 = (ImageButton)findViewById(R.id.rcmnd_night);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PathNature.class);
                intent.putExtra("type", 1);
                startActivity(intent);
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        View headerview = navigationView.getHeaderView(0);
        login = (Button) headerview.findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);

            }
        });

    }


    public static void changeBtn(SessionManager session){
        if (session.isLoggedIn()) {
                login.setVisibility(View.INVISIBLE);
        }
    }



//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.myPage) {
            if(!session.isLoggedIn()) {
                Toast.makeText(getApplicationContext(), "로그인부터 해주세요~", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(getApplicationContext(), "출력할 문자열", Toast.LENGTH_LONG).show();
//            drawer.closeDrawer(GravityCompat.START);
                Intent intent = new Intent(getApplicationContext(), MypageActivity.class);
                startActivity(intent);
            }

        } else if (id == R.id.exit) {
            if(!session.isLoggedIn()) {
                Toast.makeText(getApplicationContext(), "로그인부터 해주세요~", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(getApplicationContext(), "출력할 문자열", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), GoodbyeActivity.class);
                startActivity(intent);
            drawer.closeDrawer(GravityCompat.START);
            }


        }



        return true;
    }
}
