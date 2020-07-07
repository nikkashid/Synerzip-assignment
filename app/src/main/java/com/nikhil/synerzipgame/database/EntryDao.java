package com.nikhil.synerzipgame.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.nikhil.synerzipgame.entitiesForDB.EntryTable;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface EntryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(EntryTable entities);

    @Query("SELECT * FROM EntryTable")
    Maybe<List<EntryTable>> getAll();

    @Query("SELECT count(uid) FROM EntryTable")
    int getDataCount();
}
