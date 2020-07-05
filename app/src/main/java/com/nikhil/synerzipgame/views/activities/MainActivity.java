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
import com.nikhil.synerzipgame.models.GameResponse;
import com.nikhil.synerzipgame.network.ApiClient;
import com.nikhil.synerzipgame.network.ApiInterface;
import com.nikhil.synerzipgame.views.adapters.EntryGridViewAdapter;
import com.nikhil.synerzipgame.views.adapters.EntryListViewAdapter;
import com.nikhil.synerzipgame.viewModel.EntryViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private static final String TAG = "MainActivity";

    ApiInterface apiInterface;

    EntryViewModel entryViewModel;

    int dbCount = 0;

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

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "Thread dataCount:" + entryViewModel.getDataCount());
                dbCount = entryViewModel.getDataCount();
            }
        });
        t.setPriority(10);
        t.start();
        try {
            t.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (dbCount == 0) {

            apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<GameResponse> call = apiInterface.getEntries();
            call.enqueue(new Callback<GameResponse>() {
                @Override
                public void onResponse(Call<GameResponse> call, Response<GameResponse> response) {
                    Log.d(TAG, "onResponse: " + response.body());

                    try {
                        GameResponse gameResponse = response.body();
                        insertDataInDataBase(gameResponse);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<GameResponse> call, Throwable t) {
                    Log.e(TAG, "onFailure: ", t);
                    call.cancel();
                }
            });
        } else {
            //notifyDataSetChanged();
        }

        entryViewModel.getAll().observe(this, new Observer<List<EntryTable>>() {
            @Override
            public void onChanged(List<EntryTable> entries) {
                Log.d(TAG, "onChanged: entry table onObserver :" + entries.size());
                al_entryTable = entries;
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

    private void insertDataInDataBase(GameResponse gameResponse) {

        try {

            GameResponse.Feed feed = gameResponse.getFeed();

            List<GameResponse.Entry> entries = feed.getEntry();

            for (int i = 0; i < entries.size(); i++) {

                Log.d(TAG, "insertDataInDataBase: position " + i);
                GameResponse.Entry entry = entries.get(i);
                EntryTable entryTable = new EntryTable();
                entryTable.setName(entry.getName().getLabel());
                if (entry.getRights() != null)
                    entryTable.setRights(entry.getRights().getLabel());
                else
                    entryTable.setRights("");
                entryTable.setPrice_amount(entry.getPrice().getAttributes().getAmount());
                entryTable.setPrice_currency(entry.getPrice().getAttributes().getCurrency());
                entryTable.setImage(entry.getImage().get(2).getLabel());
                entryTable.setArtist_label(entry.getArtist().getLabel());
                if (entry.getArtist().getAttributes() != null)
                    entryTable.setArtist_link(entry.getArtist().getAttributes().getHref());
                else
                    entryTable.setArtist_link("");
                entryTable.setTitle(entry.getTitle().getLabel());
                entryTable.setLink(entry.getLink().getAttributes().getHref());
                entryTable.setCategory_id(entry.getCategory().getAttributes().getId());
                entryTable.setCategory_label(entry.getCategory().getAttributes().getLabel());
                entryTable.setCategory_scheme(entry.getCategory().getAttributes().getScheme());
                entryTable.setCategory_term(entry.getCategory().getAttributes().getTerm());
                if (entry.getReleaseDate() != null)
                    entryTable.setReleaseDate(entry.getReleaseDate().getAttributes().getLabel());
                else
                    entryTable.setReleaseDate("");
                entryViewModel.insertEntry(entryTable);
                al_entryTable.add(entryTable);
            }

            notifyDataSetChanged();

            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    Log.d(TAG, "insertDataInDataBase: dataCount:" + entryViewModel.getDataCount());
                }
            });
            t.setPriority(10);
            t.start();
            t.join();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void notifyDataSetChanged() {
        entryListViewAdapter.notifyDataSetChanged();
        entryGridViewAdapter.notifyDataSetChanged();
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