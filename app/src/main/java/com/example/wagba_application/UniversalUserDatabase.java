package com.example.wagba_application;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class,UserLog.class},version = 3)
abstract class UniversalUserDatabase extends RoomDatabase {
    abstract UniversalDao_2 getAllDao();

    private static UniversalUserDatabase instance;

    static UniversalUserDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context,
                            UniversalUserDatabase.class,"universaluser.db"
                    ).allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}