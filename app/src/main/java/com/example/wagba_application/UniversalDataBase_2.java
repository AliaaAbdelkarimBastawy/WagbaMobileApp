package com.example.wagba_application;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class,UserLog.class},version = 3)
abstract class UniversalUserDatabase_2 extends RoomDatabase {
    abstract UniversalDao_2 getAllDao();

    private static UniversalUserDatabase_2 instance;

    static UniversalUserDatabase_2 getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context,
                            UniversalUserDatabase_2.class,"universaluser_2.db"
                    ).allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}