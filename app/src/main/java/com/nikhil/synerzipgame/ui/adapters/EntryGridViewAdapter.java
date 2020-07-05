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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.nikhil.synerzipgame.R;
import com.nikhil.synerzipgame.entitiesForDB.EntryTable;

import java.util.List;

public class EntryGridViewAdapter extends RecyclerView.Adapter<EntryGridViewAdapter.ViewHolder> {

    Context context;

    List<EntryTable> al_entryTables;

    public EntryGridViewAdapter(@NonNull Context context, int resource, @NonNull List<EntryTable> objects) {
        this.context = context;
        this.al_entryTables = objects;
    }

    public void setData(List<EntryTable> al_entryTables) {
        this.al_entryTables = al_entryTables;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.entry_grid_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        EntryTable entryTable = al_entryTables.get(position);

        holder.txtTitle.setText(entryTable.getName());
        holder.txtDescription.setText(entryTable.getTitle());

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
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return al_entryTables.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView txtTitle;
        TextView txtDescription;

        public ViewHolder(View view) {
            super(view);

            img = (ImageView) view.findViewById(R.id.imageView);
            txtTitle = (TextView) view.findViewById(R.id.tv_name);
            txtDescription = (TextView) view.findViewById(R.id.tv_tile);
        }


    }
}
