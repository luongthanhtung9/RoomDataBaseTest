package com.example.myapplication.manager;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.model.User;

import java.util.List;

import static androidx.room.OnConflictStrategy.IGNORE;

@Dao
public interface UserDAO {
    @Query("Select * from user")
    List<User> getAllUser();

    @Insert(onConflict = IGNORE)
    void insertAll(User... users);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);

    @Query("Select * from user where id like :id")
    User fetch(int id);


}
