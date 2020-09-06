package com.nikhil.synerzipgame.repositories;

import java.util.ArrayList;
import java.util.List;

import com.nikhil.synerzipgame.database.AppDataBase;
import com.nikhil.synerzipgame.database.EntryDao;
import com.nikhil.synerzipgame.entitiesForDB.EntryTable;
import com.nikhil.synerzipgame.models.GameResponse;
import com.nikhil.synerzipgame.network.ApiClient;
import com.nikhil.synerzipgame.network.ApiInterface;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import io.reactivex.MaybeObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class EntryRepository
{
	private static final String TAG = "EntryRepository";

	private EntryDao entryDao;

	ApiInterface apiInterface;

	private MutableLiveData<List<EntryTable>> listLiveData = new MutableLiveData<>();

	public EntryRepository(Application application)
	{
		AppDataBase appDataBase = AppDataBase.getInstance(application);
		entryDao = appDataBase.entryDao();
	}

	public void checkData()
	{
		entryDao.getAll().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
				.subscribe(new MaybeObserver<List<EntryTable>>()
				{
					@Override
					public void onSubscribe(Disposable d)
					{

					}

					@Override
					public void onSuccess(List<EntryTable> entryTables)
					{
						listLiveData.setValue(entryTables);

						if (listLiveData == null || entryTables.size() == 0)
						{
							getDataFromServer();
						}

						getDBCountFromDB();
					}

					@Override
					public void onError(Throwable e)
					{

					}

					@Override
					public void onComplete()
					{

					}
				});
	}

	public MutableLiveData<List<EntryTable>> getAll()
	{
		return listLiveData;
	}

	private void getDataFromServer()
	{
		apiInterface = ApiClient.getClient().create(ApiInterface.class);

		apiInterface.getEntries().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
				.subscribeWith(new DisposableSingleObserver<GameResponse>()
				{
					@Override
					public void onSuccess(GameResponse gameResponse)
					{
						Log.d(TAG, "onResponse: " + gameResponse);

						try
						{
							insertDataInDataBase(gameResponse);
						}
						catch (Exception e)
						{
							e.printStackTrace();
						}
					}

					@Override
					public void onError(Throwable e)
					{
						Log.e(TAG, "onFailure: ", e);
					}
				});
	}

	private void insertDataInDataBase(GameResponse gameResponse)
	{
		try
		{
			GameResponse.Feed feed = gameResponse.getFeed();

			List<GameResponse.Entry> entries = feed.getEntry();

			List<EntryTable> al_entryTable = new ArrayList<>();

			for (int i = 0; i < entries.size(); i++)
			{

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
				insert(entryTable);
				al_entryTable.add(entryTable);
			}

			listLiveData.setValue(al_entryTable);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void insert(EntryTable entryTable)
	{
		entryDao.insert(entryTable).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
				.subscribeWith(new DisposableSingleObserver()
				{
					@Override
					public void onSuccess(Object o)
					{
						Log.d(TAG, "Data inserted successfully");
					}

					@Override
					public void onError(Throwable e)
					{
						Log.e(TAG, e.getMessage());
					}
				});
	}

	private void getDBCountFromDB()
	{
		entryDao.getDataCount().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
				.subscribeWith(new DisposableSingleObserver<Long>()
				{
					@Override
					public void onSuccess(Long aLong)
					{
						Log.d(TAG, "Data count from DB :" + aLong);
					}

					@Override
					public void onError(Throwable e)
					{
						Log.e(TAG, e.getMessage());
					}
				});
	}
}
