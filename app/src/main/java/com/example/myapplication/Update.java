package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

public class Update extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "Update";
    EditText mEdtUpdateFirstName, mEdtUpdateLastName, mEdtUpdateEmail;
    Button mBtnUpdateUser;
    AppDataBase db;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Update New User ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mEdtUpdateFirstName = findViewById(R.id.first_name_update);
        mEdtUpdateLastName = findViewById(R.id.last_name_update);
        mEdtUpdateEmail = findViewById(R.id.email_update);
        mBtnUpdateUser = findViewById(R.id.btn_updateuser);
        mBtnUpdateUser.setOnClickListener(this);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        Log.d(TAG, "onCreate: "+ id);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        supportFinishAfterTransition();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();

    }

    @Override
    public void onClick(View view) {
        db = Room.databaseBuilder(getApplicationContext(),
                AppDataBase.class, "production")
                .allowMainThreadQueries()
                .build();
        if (view.getId() == R.id.btn_updateuser) {
            //Log.d(TAG, "onClick: add " + mEdtEmail.getText().toString());
            User user = db.userDAO().fetch(id);
            user.setEmail(mEdtUpdateEmail.getText().toString());
            user.setFirstName(mEdtUpdateFirstName.getText().toString());
            user.setLastName(mEdtUpdateLastName.getText().toString());
            db.userDAO().updateUser(user);
            startActivity(new Intent(Update.this, MainActivity.class));
        }
    }
}
