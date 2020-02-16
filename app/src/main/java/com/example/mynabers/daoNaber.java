package com.example.mynabers;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;

@Dao
public interface daoNaber {

    @Query("SELECT * FROM neighbor")
    ArrayList<neighbor> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(ArrayList<neighbor> myArrey);
}
