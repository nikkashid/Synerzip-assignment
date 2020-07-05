package com.nikhil.synerzipgame.entitiesForDB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.parceler.Parcel;

@Parcel
@Entity
public class EntryTable {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "name")
    String name;

    @ColumnInfo(name = "rights")
    String rights;

    @ColumnInfo(name = "price_amount")
    String price_amount;

    @ColumnInfo(name = "price_currency")
    String price_currency;

    @ColumnInfo(name = "image")
    String image;

    @ColumnInfo(name = "artist_label")
    String artist_label;

    @ColumnInfo(name = "artist_link")
    String artist_link;

    @ColumnInfo(name = "title")
    String title;

    @ColumnInfo(name = "link")
    String link;

    @ColumnInfo(name = "category_id")
    String category_id;

    @ColumnInfo(name = "category_term")
    String category_term;

    @ColumnInfo(name = "category_label")
    String category_label;

    @ColumnInfo(name = "category_scheme")
    String category_scheme;

    @ColumnInfo(name = "releaseDate")
    String releaseDate;

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }

    public String getPrice_amount() {
        return price_amount;
    }

    public void setPrice_amount(String price_amount) {
        this.price_amount = price_amount;
    }

    public String getPrice_currency() {
        return price_currency;
    }

    public void setPrice_currency(String price_currency) {
        this.price_currency = price_currency;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getArtist_label() {
        return artist_label;
    }

    public void setArtist_label(String artist_label) {
        this.artist_label = artist_label;
    }

    public String getArtist_link() {
        return artist_link;
    }

    public void setArtist_link(String artist_link) {
        this.artist_link = artist_link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategory_term() {
        return category_term;
    }

    public void setCategory_term(String category_term) {
        this.category_term = category_term;
    }

    public String getCategory_label() {
        return category_label;
    }

    public void setCategory_label(String category_label) {
        this.category_label = category_label;
    }

    public String getCategory_scheme() {
        return category_scheme;
    }

    public void setCategory_scheme(String category_scheme) {
        this.category_scheme = category_scheme;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
