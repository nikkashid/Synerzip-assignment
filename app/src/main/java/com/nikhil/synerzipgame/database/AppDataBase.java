package com.nikhil.synerzipgame.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.nikhil.synerzipgame.entitiesForDB.EntryTable;

@Database(entities = {EntryTable.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    private static final String TAG = "AppDataBase";

    private static AppDataBase instance;

    public abstract EntryDao entryDao();

    public static synchronized AppDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, TAG)
                    .fallbackToDestructiveMigration().build();
        }

        return instance;
    }
}
