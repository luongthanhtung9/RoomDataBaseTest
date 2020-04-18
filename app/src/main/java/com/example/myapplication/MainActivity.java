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

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    FloatingActionButton mFab;
    RecyclerView mRecyclerView;
    Adapter adapter;
    List<User> users;
    AppDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFab = findViewById(R.id.fab);
        mRecyclerView = findViewById(R.id.recycler_view);
        db = Room.databaseBuilder(getApplicationContext(),
                AppDataBase.class, "production")
                .allowMainThreadQueries()
                .build();


        updateUI();
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Pressed");
                startActivity(new Intent(MainActivity.this, AddUser.class));
            }
        });

        adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void viewOnclick(View v, int position) {
                User user = users.get(position);
                Intent intent = new Intent(MainActivity.this, Update.class);
                intent.putExtra("id", user.getId());
                startActivity(intent);

            }
        });

        adapter.setLongClickListener(new Adapter.OnItemLongClickListener() {
            @Override
            public void viewLongClick(View v, int position) {
                User user = users.get(position);
                db.userDAO().deleteUser(user);
                updateUI();
            }
        });


    }

    public void updateUI() {
        users = db.userDAO().getAllUser();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(users, MainActivity.this);
        mRecyclerView.setAdapter(adapter);
    }
}
