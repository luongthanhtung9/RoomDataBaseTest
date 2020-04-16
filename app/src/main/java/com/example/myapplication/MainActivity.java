package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    FloatingActionButton mFab;
    RecyclerView mRecyclerView;
    Adapter adapter;
    List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        mFab = findViewById(R.id.fab);
        mRecyclerView = findViewById(R.id.recycler_view);
       // users = new ArrayList<>();
        AppDataBase db = Room.databaseBuilder(getApplicationContext(),
                AppDataBase.class, "production")
    .allowMainThreadQueries()
                .build();

        users =  db.userDAO().getAllUser();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(users, MainActivity.this);
        mRecyclerView.setAdapter(adapter);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Pressed");
                startActivity(new Intent(MainActivity.this, AddUser.class));
            }
        });

    }
}
