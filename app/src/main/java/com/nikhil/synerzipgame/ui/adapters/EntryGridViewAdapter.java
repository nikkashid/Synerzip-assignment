package com.nikhil.synerzipgame.ui.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.nikhil.synerzipgame.R;
import com.nikhil.synerzipgame.entitiesForDB.EntryTable;

import java.util.List;

public class EntryGridViewAdapter extends ArrayAdapter<EntryTable> {

    Context context;

    List<EntryTable> al_entryTables;

    public EntryGridViewAdapter(@NonNull Context context, int resource, @NonNull List<EntryTable> objects) {
        super(context, resource, objects);
        this.context = context;
        this.al_entryTables = objects;
    }

    public void setData(List<EntryTable> al_entryTables){
        this.al_entryTables = al_entryTables;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (null == view) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.entry_grid_item, null);
        }

        EntryTable entryTable = getItem(position);

        ImageView img = (ImageView) view.findViewById(R.id.imageView);
        TextView txtTitle = (TextView) view.findViewById(R.id.tv_name);
        TextView txtDescription = (TextView) view.findViewById(R.id.tv_tile);

        txtTitle.setText(entryTable.getName());
        txtDescription.setText(entryTable.getTitle());

        Glide.with(context)
                .load(entryTable.getImage())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(img);

        return view;
    }
}
