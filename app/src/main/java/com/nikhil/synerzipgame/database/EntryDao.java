package com.nikhil.synerzipgame.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.nikhil.synerzipgame.entitiesForDB.EntryTable;

import java.util.List;

@Dao
public interface EntryDao {

    @Insert
    void insert(EntryTable entities);

    @Query("SELECT * FROM EntryTable")
    LiveData<List<EntryTable>> getAll();

    @Query("SELECT count(uid) FROM EntryTable")
    int getDataCount();
}
