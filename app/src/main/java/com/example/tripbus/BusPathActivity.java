package com.example.tripbus;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

public class BusPathActivity extends AppCompatActivity {
    SessionManager session;
    TextView viewMap;
    ImageButton scrap;
    ListView pathList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_path);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.path_toolbar);
        setSupportActionBar(mToolbar);
        TextView seeinmap = (TextView)findViewById(R.id.seeinmap);

        seeinmap.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), MapMainActivity.class);
                startActivity(intent);


            }


        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setElevation(0);

        session = new SessionManager(getApplicationContext());




    }
}
