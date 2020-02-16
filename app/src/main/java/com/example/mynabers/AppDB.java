package com.example.mynabers;

import android.app.Activity;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = neighbor.class, version = 1)
public abstract class AppDB extends RoomDatabase {

    public static final String DB_NAME = "dB";
    static AppDB INSTANS;
    public abstract daoNaber daoNaber();

    public static AppDB getIns(Activity context){
        if (INSTANS != null){
            INSTANS = Room.databaseBuilder(context.getApplicationContext(),AppDB.class,DB_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return INSTANS;
    }
    public static void destroyInstans(){
        INSTANS = null;
    }
}
