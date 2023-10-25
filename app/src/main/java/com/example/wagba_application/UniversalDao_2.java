package com.example.wagba_application;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
interface UniversalDao_2 {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertUser(User user);
    @Insert
    long insertLog(UserLog userLog);
    @Query("SELECT * FROM user WHERE userId=:userId")
    User getUserById(long userId);
    @Query("SELECT * FROM log WHERE userId=:userId")
    List<UserLog> getUserLogs(long userId);
    @Query("SELECT userId FROM user WHERE userName=:userName AND userPassword=:password")
    long verifyUserLogin(String userName, String password);
}