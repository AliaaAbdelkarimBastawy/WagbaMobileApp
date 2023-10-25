package com.example.wagba_application;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "log")
class UserLog {
    @PrimaryKey
    Long id;
    Long timestamp;
    Long userId;
    String logData;

    public UserLog(){}

    @Ignore
    public UserLog(User user, String logData) {
        this.id = null;
        this.timestamp = System.currentTimeMillis();
        this.userId = user.getUserId();
        this.logData = logData;
    }

    public Long getId() {
        return id;
    }
    public Long getUserId() {
        return userId;
    }
    public Long getTimestamp() {
        return timestamp;
    }
    public String getLogData() {
        return logData;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
    public void setLogData(String logData) {
        this.logData = logData;
    }
}