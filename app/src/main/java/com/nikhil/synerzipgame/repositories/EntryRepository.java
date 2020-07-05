package com.nikhil.synerzipgame.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.nikhil.synerzipgame.database.AppDataBase;
import com.nikhil.synerzipgame.database.EntryDao;
import com.nikhil.synerzipgame.entitiesForDB.EntryTable;

import java.util.List;

public class EntryRepository {

    private EntryDao entryDao;

    public EntryRepository(Application application) {
        AppDataBase appDataBase = AppDataBase.getInstance(application);
        entryDao = appDataBase.entryDao();
    }

    public LiveData<List<EntryTable>> getAll() {
        return entryDao.getAll();
    }

    public void insert(EntryTable entryTable) {
        new InsertDataAsyncTask(entryDao).execute(entryTable);
    }

    public int getDataCount() {
        return entryDao.getDataCount();
    }

    private static class InsertDataAsyncTask extends AsyncTask<EntryTable, Void, Void> {
        private EntryDao entityDao;

        private InsertDataAsyncTask(EntryDao entityDao) {
            this.entityDao = entityDao;
        }

        @Override
        protected Void doInBackground(EntryTable... entryTable) {
            entityDao.insert(entryTable[0]);
            return null;
        }
    }
}
