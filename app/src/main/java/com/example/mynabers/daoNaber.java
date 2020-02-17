package com.example.mynabers;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface daoNaber {

    @Query("SELECT * FROM neighbor ORDER BY rating ASC ")
    List<neighbor> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<neighbor> myArrey);

    @Query("DELETE FROM neighbor")
    void deleteAll();
}
