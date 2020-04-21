package com.example.myapplication.manager;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapplication.model.User;

@Database(entities = {User.class}, version = 1)
public  abstract class AppDataBase extends RoomDatabase {
    private static final String TAG = "AppDataBase";
    public static  AppDataBase INSTANCE = null;

    public abstract UserDAO userDAO();

    public static AppDataBase getInstance(Context context) {
        if(INSTANCE == null) {
            Log.d(TAG, "getInstance: ");
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDataBase.class, "production")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
