package com.example.my_project003;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import adapters_custom.main_ui_card_adapter;
import cards.main_ui_card;

public class MainActivity extends AppCompatActivity {

    List<main_ui_card> list_titles;
    String title = "AITS Tirupati";
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        setTitle(title);


        list_titles = new ArrayList<>();

        list_titles.add(new main_ui_card("Circulars", R.drawable.circulars));
        list_titles.add(new main_ui_card("Time Tables", R.drawable.timetable));
        list_titles.add(new main_ui_card("Internal Marks", R.drawable.internals));
        list_titles.add(new main_ui_card("Attendance", R.drawable.attendance));
        list_titles.add(new main_ui_card("Library Data", R.drawable.library));
        list_titles.add(new main_ui_card("Bus Tracking", R.drawable.icon_bus_colored));
        list_titles.add(new main_ui_card("College Website", R.drawable.website));
        list_titles.add(new main_ui_card("Hostel Admission", R.drawable.hostel));
        list_titles.add(new main_ui_card("Queries", R.drawable.queries));

        RecyclerView mRecycle = findViewById(R.id.mRecyclerView);
        main_ui_card_adapter muAdapter = new main_ui_card_adapter(this,list_titles);
        mRecycle.setLayoutManager(new GridLayoutManager(this , 2));
        mRecycle.setAdapter(muAdapter);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Sharing Options will be triggered", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.icon,menu);
        return true;
    }
}
