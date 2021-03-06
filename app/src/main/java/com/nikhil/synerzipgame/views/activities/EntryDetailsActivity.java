package com.nikhil.synerzipgame.views.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.nikhil.synerzipgame.R;
import com.nikhil.synerzipgame.entitiesForDB.EntryTable;

import org.parceler.Parcels;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class EntryDetailsActivity extends AppCompatActivity
{

	private static final String TAG = "EntryDetailsActivity";

	EntryTable entryTable;

	ImageView iv_image;

	TextView tv_name;

	TextView tv_amount;

	TextView tv_currency;

	TextView tv_categoryId;

	TextView tv_tile;

	TextView tv_rights;

	TextView tv_releaseDate;

	TextView tv_categoryName;

	TextView tv_href;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_entry_details);

		if (getIntent() != null)
		{
			try
			{
				entryTable = Parcels.unwrap(getIntent().getParcelableExtra("entryTable"));
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

		initViews();
		if (entryTable != null)
		{
			setData();
		}

		askForPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, 101);

	}

	private void askForPermission(String writeExternalStorage, String readExternalStorage, int i)
	{
		if (ContextCompat.checkSelfPermission(this, writeExternalStorage) == PackageManager.PERMISSION_GRANTED
				&& ContextCompat.checkSelfPermission(this, readExternalStorage) == PackageManager.PERMISSION_GRANTED)
		{
			extractDataBase();
		}
		else
		{
			String[] permissions = { writeExternalStorage, readExternalStorage };
			ActivityCompat.requestPermissions(this, permissions, i);
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
	{
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 101)
		{
			if (grantResults[0] == PackageManager.PERMISSION_GRANTED
					&& grantResults[1] == PackageManager.PERMISSION_GRANTED)
			{
				extractDataBase();
			}
			else
			{
				Toast.makeText(this, "Permission Denied.", Toast.LENGTH_SHORT).show();
			}
		}
	}

	private void extractDataBase()
	{
		try
		{
			File sd = Environment.getExternalStorageDirectory();
			File data = Environment.getDataDirectory();
			boolean state = isExternalStorageWritable();

			if (true)
			{
				//String currentDBPath = "//data//com.nikhil.synerzipgame//databases//AppDataBase";
				String currentDBPath = getDatabasePath("AppDataBase").getAbsolutePath();

				String backupDBPath = "AppDataBase.db";
				File currentDB = new File(data, currentDBPath);
				File backupDB = new File(sd, backupDBPath);

				if (true)
				{
					FileChannel src = new FileInputStream(currentDB).getChannel();
					FileChannel dst = new FileOutputStream(backupDB).getChannel();
					dst.transferFrom(src, 0, src.size());
					src.close();
					dst.close();
				}
				else
				{
					Toast.makeText(this, "No database", Toast.LENGTH_SHORT).show();
				}
			}
		}
		catch (Exception e)
		{
			Log.v("Settings Backup", e.getMessage());
		}
	}

	/* Checks if external storage is available for read and write */
	public boolean isExternalStorageWritable()
	{
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state))
		{
			return true;
		}
		return false;
	}

	private void initViews()
	{
		iv_image = (ImageView) findViewById(R.id.iv_image);
		tv_name = (TextView) findViewById(R.id.tv_name);
		tv_amount = (TextView) findViewById(R.id.tv_amount);
		tv_currency = (TextView) findViewById(R.id.tv_currency);
		tv_categoryId = (TextView) findViewById(R.id.tv_categoryId);
		tv_tile = (TextView) findViewById(R.id.tv_tile);
		tv_rights = (TextView) findViewById(R.id.tv_rights);
		tv_releaseDate = (TextView) findViewById(R.id.tv_releaseDate);
		tv_categoryName = (TextView) findViewById(R.id.tv_categoryName);
		tv_href = (TextView) findViewById(R.id.tv_href);
	}

	private void setData()
	{

		tv_name.setText(entryTable.getName());
		tv_amount.setText(entryTable.getPrice_amount());
		tv_currency.setText(entryTable.getPrice_currency());
		tv_categoryId.setText(entryTable.getCategory_id());
		tv_tile.setText(entryTable.getTitle());
		tv_rights.setText(entryTable.getRights());
		tv_releaseDate.setText(entryTable.getReleaseDate());
		tv_categoryName.setText(entryTable.getCategory_label());

		String link = entryTable.getLink();
		SpannableString content = new SpannableString(link);
		content.setSpan(new UnderlineSpan(), 0, link.length(), 0);
		tv_href.setText(content);
		tv_href.setTextColor(Color.BLUE);

		tv_href.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String url = tv_href.getText().toString();
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(url));
				startActivity(i);
			}
		});

		Glide.with(this).load(entryTable.getImage()).listener(new RequestListener<Drawable>()
		{
			@Override
			public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target,
					boolean isFirstResource)
			{
				return false;
			}

			@Override
			public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target,
					DataSource dataSource, boolean isFirstResource)
			{
				return false;
			}
		}).diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop().into(iv_image);
	}
}