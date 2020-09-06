package com.nikhil.synerzipgame.database;

import java.util.List;

import com.nikhil.synerzipgame.entitiesForDB.EntryTable;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import io.reactivex.Maybe;
import io.reactivex.Single;

@Dao
public interface EntryDao
{
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	Single<Long> insert(EntryTable entities);

	@Query("SELECT * FROM EntryTable")
	Maybe<List<EntryTable>> getAll();

	@Query("SELECT count(uid) FROM EntryTable")
	Single<Long> getDataCount();
}
