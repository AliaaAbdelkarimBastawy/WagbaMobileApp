package com.example.wagba_application;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
interface UniversalDao_3 {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertUser(User_3 user);

    @Insert
    long insertLog(UserLog_3 userLog);

    @Query("SELECT * FROM user_3 WHERE userId=:userId")
    User_3 getUserById(long userId);

    @Query("SELECT * FROM log_3 WHERE userId=:userId")
    List<UserLog_3> getUserLogs(long userId);

    @Query("SELECT userId FROM user_3 WHERE userName=:userName AND userPassword=:password")
    long verifyUserLogin(String userName, String password);

    @Query("UPDATE user_3 SET number=:number, name=:name, collegeID=:collegeID WHERE userId LIKE :userId")
    void Update(String number, String name, String collegeID, long userId);
}
