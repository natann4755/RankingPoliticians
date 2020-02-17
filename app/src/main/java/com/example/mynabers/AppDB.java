package com.example.mynabers;

import android.app.Activity;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = neighbor.class, version = 2)
public abstract class AppDB extends RoomDatabase {

    public static final String DB_NAME = "dB";
    static AppDB INSTANCE;
    public abstract daoNaber daoNaber();

    public static AppDB getIns(Activity context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext()
                    ,AppDB.class,DB_NAME)
                    .allowMainThreadQueries().fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
    public static void destroyInstans(){
        INSTANCE = null;
    }
}
