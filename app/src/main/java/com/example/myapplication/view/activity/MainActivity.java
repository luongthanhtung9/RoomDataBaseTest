package com.example.myapplication.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.view.adapter.Adapter;
import com.example.myapplication.manager.AppDataBase;
import com.example.myapplication.R;
import com.example.myapplication.model.User;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements Adapter.OnItemLongClickListener, Adapter.OnItemClickListener {
    private static final String TAG = "MainActivity";
    FloatingActionButton mFab;
    RecyclerView mRecyclerView;
    Adapter adapter;
    List<User> users = new ArrayList<>();
    private AppDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFab = findViewById(R.id.fab);
        mRecyclerView = findViewById(R.id.recycler_view);
//        db.getI = Room.databaseBuilder(getApplicationContext(),
//                AppDataBase.class, "production")
//                .allowMainThreadQueries()
//                .build();
        db = AppDataBase.getInstance(getApplicationContext());
        if(db != null) {
            users = db.userDAO().getAllUser();
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(users, MainActivity.this);
        mRecyclerView.setAdapter(adapter);
        //updateUI();

        adapter.setLongClickListener(this);
        adapter.setOnItemClickListener(this);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Pressed");
                startActivity(new Intent(MainActivity.this, AddUser.class));
            }
        });

    }

    public void updateUI() {
        users.clear();
        users = db.userDAO().getAllUser();
        adapter = new Adapter(users, MainActivity.this);
        mRecyclerView.setAdapter(adapter);
        adapter.setLongClickListener(this);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void viewLongClick(View v, int position) {
        User user = users.get(position);
        db.userDAO().deleteUser(user);
        updateUI();
    }

    @Override
    public void viewOnclick(View v, int position) {
        User user = users.get(position);
        Intent intent = new Intent(MainActivity.this, Update.class);
        intent.putExtra("id", user.getId());
        startActivity(intent);
    }
}
