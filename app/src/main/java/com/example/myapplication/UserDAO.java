package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.IGNORE;

@Dao
public interface UserDAO {
    @Query("Select * from user")
    List<User> getAllUser();

    @Insert(onConflict = IGNORE)
    void insertAll(User... users);
}
