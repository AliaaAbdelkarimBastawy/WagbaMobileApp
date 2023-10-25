package com.example.wagba_application;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
class User_3 {
    @PrimaryKey
    Long userId;
    @ColumnInfo(index = true)
    String userName;
    String userPassword;
    String number;
    String name;
    String collegeID;

    public User_3(){};

    @Ignore
    public User_3(Long userId, String userName, String userPassword, String number, String name, String collegeID) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.number = number;
        this.name = name;
        this.collegeID = collegeID;
    }


    @Ignore
    public User_3(String userName, String userPassword, String name, String number, String collegeID)
    {
        this(null,userName,userPassword,name, number, collegeID);
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}