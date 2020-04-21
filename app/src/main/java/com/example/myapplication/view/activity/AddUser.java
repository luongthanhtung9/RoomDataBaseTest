package com.example.myapplication.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.manager.AppDataBase;
import com.example.myapplication.R;
import com.example.myapplication.model.User;

public class AddUser extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "AddUser";
    EditText mEdtFirstName, mEdtLastName, mEdtEmail;
    Button mBtnAddUser;
    AppDataBase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_user);
        initView();
    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add New User ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mEdtFirstName = findViewById(R.id.first_name);
        mEdtLastName = findViewById(R.id.last_name);
        mEdtEmail = findViewById(R.id.email);
        mBtnAddUser = findViewById(R.id.btn_adduser);
        mBtnAddUser.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        supportFinishAfterTransition();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View view) {
         db = AppDataBase.getInstance(getApplicationContext());
        if (view.getId() == R.id.btn_adduser) {
            Log.d(TAG, "onClick: add " + mEdtEmail.getText().toString());
            db.userDAO().insertAll(new User(mEdtFirstName.getText().toString(),
                    mEdtLastName.getText().toString(),
                    mEdtEmail.getText().toString()));
            startActivity(new Intent(AddUser.this, MainActivity.class));
        }
    }
}
