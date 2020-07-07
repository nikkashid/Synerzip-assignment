package com.nikhil.synerzipgame.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.nikhil.synerzipgame.entitiesForDB.EntryTable;
import com.nikhil.synerzipgame.repositories.EntryRepository;

import java.util.List;

public class EntryViewModel extends AndroidViewModel {

    EntryRepository entryRepository;

    public EntryViewModel(@NonNull Application application) {
        super(application);
        entryRepository = new EntryRepository(getApplication());
        entryRepository.checkData();
    }

    public LiveData<List<EntryTable>> getAll() {
        return entryRepository.getAll();
    }
}
