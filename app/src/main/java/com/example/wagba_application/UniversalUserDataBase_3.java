package com.example.wagba_application;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User_3.class,UserLog_3.class},version = 1)
abstract class UniversalUserDatabase_3 extends RoomDatabase {
    abstract UniversalDao_3 getAllDao();

    private static UniversalUserDatabase_3 instance;

    static UniversalUserDatabase_3 getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context,
                            UniversalUserDatabase_3.class,"universaluser_3.db"
                    ).allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}