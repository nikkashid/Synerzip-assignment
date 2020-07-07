package com.nikhil.synerzipgame.views.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.nikhil.synerzipgame.R;
import com.nikhil.synerzipgame.entitiesForDB.EntryTable;
import com.nikhil.synerzipgame.views.adapters.EntryGridViewAdapter;
import com.nikhil.synerzipgame.views.adapters.EntryListViewAdapter;
import com.nikhil.synerzipgame.viewModel.EntryViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private static final String TAG = "MainActivity";

    EntryViewModel entryViewModel;

    private ViewStub stubGrid;

    private ViewStub stubList;

    private RecyclerView listView;

    private RecyclerView gridView;

    private RecyclerView.LayoutManager layoutManager;

    private int currentViewMode = 0;

    static final int VIEW_MODE_LISTVIEW = 0;

    static final int VIEW_MODE_GRIDVIEW = 1;

    List<EntryTable> al_entryTable;

    EntryGridViewAdapter entryGridViewAdapter;

    EntryListViewAdapter entryListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniViews();

        entryViewModel.getAll().observe(this, new Observer<List<EntryTable>>() {
            @Override
            public void onChanged(List<EntryTable> entries) {
                Log.d(TAG, "onChanged: entry table onObserver :" + entries.size());
                al_entryTable = entries;
                Collections.sort(al_entryTable);
                switchView();
            }
        });
    }

    private void iniViews() {

        entryViewModel = ViewModelProviders.of(MainActivity.this).get(EntryViewModel.class);

        al_entryTable = new ArrayList<>();

        stubList = (ViewStub) findViewById(R.id.stub_list);
        stubGrid = (ViewStub) findViewById(R.id.stub_grid);

        stubList.inflate();
        stubGrid.inflate();

        listView = (RecyclerView) findViewById(R.id.mylistview);
        gridView = (RecyclerView) findViewById(R.id.mygridview);

        SharedPreferences sharedPreferences = getSharedPreferences("ViewMode", MODE_PRIVATE);
        currentViewMode = sharedPreferences.getInt("currentViewMode", VIEW_MODE_LISTVIEW);

        entryListViewAdapter = new EntryListViewAdapter(this, R.layout.entry_list_item, al_entryTable);
        entryGridViewAdapter = new EntryGridViewAdapter(this, R.layout.entry_grid_item, al_entryTable);

        switchView();
    }

    private void switchView() {

        if (VIEW_MODE_LISTVIEW == currentViewMode) {
            stubList.setVisibility(View.VISIBLE);
            stubGrid.setVisibility(View.GONE);
        } else {
            stubList.setVisibility(View.GONE);
            stubGrid.setVisibility(View.VISIBLE);
        }

        setAdapters();
    }

    private void setAdapters() {
        if (VIEW_MODE_LISTVIEW == currentViewMode) {
            entryListViewAdapter = new EntryListViewAdapter(this, R.layout.entry_list_item, al_entryTable);
            listView.setLayoutManager(new LinearLayoutManager(this));
            listView.setAdapter(entryListViewAdapter);
        } else {
            entryGridViewAdapter = new EntryGridViewAdapter(this, R.layout.entry_grid_item, al_entryTable);
            layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            gridView.setLayoutManager(layoutManager);
            gridView.setAdapter(entryGridViewAdapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_menu_1:
                if (VIEW_MODE_LISTVIEW == currentViewMode) {
                    currentViewMode = VIEW_MODE_GRIDVIEW;
                } else {
                    currentViewMode = VIEW_MODE_LISTVIEW;
                }
                //Switch view
                switchView();
                //Save view mode in share reference
                SharedPreferences sharedPreferences = getSharedPreferences("ViewMode", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("currentViewMode", currentViewMode);
                editor.commit();

                break;
        }
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        String userInput = newText.toLowerCase();
        List<EntryTable> entryTables = new ArrayList<>();

        for (EntryTable entryTable : al_entryTable) {
            if (entryTable.getName().trim().toLowerCase().contains(userInput)) {
                entryTables.add(entryTable);
            }
        }

        entryGridViewAdapter.setData(entryTables);
        entryListViewAdapter.setData(entryTables);

        return true;
    }
}