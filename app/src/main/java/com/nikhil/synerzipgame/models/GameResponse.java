package com.nikhil.synerzipgame.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GameResponse {

    @Expose
    @SerializedName("feed")
    private Feed feed;

    public Feed getFeed() {
        return feed;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }

    public static class Feed {
        @Expose
        @SerializedName("id")
        private Id id;
        @Expose
        @SerializedName("link")
        private List<Link> link;
        @Expose
        @SerializedName("icon")
        private Icon icon;
        @Expose
        @SerializedName("title")
        private Title title;
        @Expose
        @SerializedName("rights")
        private Rights rights;
        @Expose
        @SerializedName("updated")
        private Updated updated;
        @Expose
        @SerializedName("entry")
        private List<Entry> entry;
        @Expose
        @SerializedName("author")
        private Author author;

        public Id getId() {
            return id;
        }

        public void setId(Id id) {
            this.id = id;
        }

        public List<Link> getLink() {
            return link;
        }

        public void setLink(List<Link> link) {
            this.link = link;
        }

        public Icon getIcon() {
            return icon;
        }

        public void setIcon(Icon icon) {
            this.icon = icon;
        }

        public Title getTitle() {
            return title;
        }

        public void setTitle(Title title) {
            this.title = title;
        }

        public Rights getRights() {
            return rights;
        }

        public void setRights(Rights rights) {
            this.rights = rights;
        }

        public Updated getUpdated() {
            return updated;
        }

        public void setUpdated(Updated updated) {
            this.updated = updated;
        }

        public List<Entry> getEntry() {
            return entry;
        }

        public void setEntry(List<Entry> entry) {
            this.entry = entry;
        }

        public Author getAuthor() {
            return author;
        }

        public void setAuthor(Author author) {
            this.author = author;
        }
    }

    public static class Id {
        @Expose
        @SerializedName("label")
        private String label;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public static class Link {
        @Expose
        @SerializedName("attributes")
        private Attributes attributes;

        public Attributes getAttributes() {
            return attributes;
        }

        public void setAttributes(Attributes attributes) {
            this.attributes = attributes;
        }
    }

    public static class Attributes {
        @Expose
        @SerializedName("href")
        private String href;
        @Expose
        @SerializedName("type")
        private String type;
        @Expose
        @SerializedName("rel")
        private String rel;

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getRel() {
            return rel;
        }

        public void setRel(String rel) {
            this.rel = rel;
        }
    }

    public static class Icon {
        @Expose
        @SerializedName("label")
        private String label;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public static class Title {
        @Expose
        @SerializedName("label")
        private String label;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public static class Rights {
        @Expose
        @SerializedName("label")
        private String label;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public static class Updated {
        @Expose
        @SerializedName("label")
        private String label;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public static class Entry {

        @Expose
        @SerializedName("im:releaseDate")
        private ReleaseDate releaseDate;
        @Expose
        @SerializedName("category")
        private Category category;
        @Expose
        @SerializedName("im:contentType")
        private ContentType contentType;
        @Expose
        @SerializedName("id")
        private IdEntry id;
        @Expose
        @SerializedName("link")
        private LinkEntry link;
        @Expose
        @SerializedName("title")
        private TitleEntry title;
        @Expose
        @SerializedName("im:artist")
        private Artist artist;
        @Expose
        @SerializedName("im:image")
        private List<Image> image;
        @Expose
        @SerializedName("im:price")
        private Price price;
        @Expose
        @SerializedName("rights")
        private RightsEntry rights;
        @Expose
        @SerializedName("im:name")
        private NameEntry name;

        public ReleaseDate getReleaseDate() {
            return releaseDate;
        }

        public void setReleaseDate(ReleaseDate releaseDate) {
            this.releaseDate = releaseDate;
        }

        public Category getCategory() {
            return category;
        }

        public void setCategory(Category category) {
            this.category = category;
        }

        public ContentType getContentType() {
            return contentType;
        }

        public void setContentType(ContentType contentType) {
            this.contentType = contentType;
        }

        public IdEntry getId() {
            return id;
        }

        public void setId(IdEntry id) {
            this.id = id;
        }

        public LinkEntry getLink() {
            return link;
        }

        public void setLink(LinkEntry link) {
            this.link = link;
        }

        public TitleEntry getTitle() {
            return title;
        }

        public void setTitle(TitleEntry title) {
            this.title = title;
        }

        public Artist getArtist() {
            return artist;
        }

        public void setArtist(Artist artist) {
            this.artist = artist;
        }

        public List<Image> getImage() {
            return image;
        }

        public void setImage(List<Image> image) {
            this.image = image;
        }

        public Price getPrice() {
            return price;
        }

        public void setPrice(Price price) {
            this.price = price;
        }

        public RightsEntry getRights() {
            return rights;
        }

        public void setRights(RightsEntry rights) {
            this.rights = rights;
        }

        public NameEntry getName() {
            return name;
        }

        public void setName(NameEntry name) {
            this.name = name;
        }
    }

    public static class ReleaseDate {
        @Expose
        @SerializedName("attributes")
        private AttributesReleaseDate attributes;

        @Expose
        @SerializedName("label")
        private String label;

        public AttributesReleaseDate getAttributes() {
            return attributes;
        }

        public void setAttributes(AttributesReleaseDate attributes) {
            this.attributes = attributes;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public static class AttributesReleaseDate {
        @Expose
        @SerializedName("label")
        private String label;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public static class Category {
        @Expose
        @SerializedName("attributes")
        private AttributesCategory attributes;

        public AttributesCategory getAttributes() {
            return attributes;
        }

        public void setAttributes(AttributesCategory attributes) {
            this.attributes = attributes;
        }
    }

    public static class AttributesCategory {
        @Expose
        @SerializedName("label")
        private String label;
        @Expose
        @SerializedName("scheme")
        private String scheme;
        @Expose
        @SerializedName("term")
        private String term;
        @Expose
        @SerializedName("im:id")
        private String id;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getScheme() {
            return scheme;
        }

        public void setScheme(String scheme) {
            this.scheme = scheme;
        }

        public String getTerm() {
            return term;
        }

        public void setTerm(String term) {
            this.term = term;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static class ContentType {
        @Expose
        @SerializedName("attributes")
        private AttributesContentType attributes;

        public AttributesContentType getAttributes() {
            return attributes;
        }

        public void setAttributes(AttributesContentType attributes) {
            this.attributes = attributes;
        }
    }

    public static class AttributesContentType {
        @Expose
        @SerializedName("label")
        private String label;
        @Expose
        @SerializedName("term")
        private String term;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getTerm() {
            return term;
        }

        public void setTerm(String term) {
            this.term = term;
        }
    }

    public static class IdEntry {
        @Expose
        @SerializedName("attributes")
        private AttributesId attributes;
        @Expose
        @SerializedName("label")
        private String label;

        public AttributesId getAttributes() {
            return attributes;
        }

        public void setAttributes(AttributesId attributes) {
            this.attributes = attributes;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public static class AttributesId {
        @Expose
        @SerializedName("bundleId")
        private String bundleId;
        @Expose
        @SerializedName("id")
        private String id;

        public String getBundleId() {
            return bundleId;
        }

        public void setBundleId(String bundleId) {
            this.bundleId = bundleId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static class LinkEntry {
        @Expose
        @SerializedName("attributes")
        private AttributesLink attributes;

        public AttributesLink getAttributes() {
            return attributes;
        }

        public void setAttributes(AttributesLink attributes) {
            this.attributes = attributes;
        }
    }

    public static class AttributesLink {
        @Expose
        @SerializedName("href")
        private String href;
        @Expose
        @SerializedName("type")
        private String type;
        @Expose
        @SerializedName("rel")
        private String rel;

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getRel() {
            return rel;
        }

        public void setRel(String rel) {
            this.rel = rel;
        }
    }

    public static class TitleEntry {
        @Expose
        @SerializedName("label")
        private String label;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public static class Artist {
        @Expose
        @SerializedName("attributes")
        private AttributesArtist attributes;
        @Expose
        @SerializedName("label")
        private String label;

        public AttributesArtist getAttributes() {
            return attributes;
        }

        public void setAttributes(AttributesArtist attributes) {
            this.attributes = attributes;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public static class AttributesArtist {
        @Expose
        @SerializedName("href")
        private String href = "";

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }
    }

    public static class Image {
        @Expose
        @SerializedName("attributes")
        private AttributesImage attributes;
        @Expose
        @SerializedName("label")
        private String label;

        public AttributesImage getAttributes() {
            return attributes;
        }

        public void setAttributes(AttributesImage attributes) {
            this.attributes = attributes;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public static class AttributesImage {
        @Expose
        @SerializedName("height")
        private String height;

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }
    }

    public static class Price {
        @Expose
        @SerializedName("attributes")
        private AttributesPrice attributes;
        @Expose
        @SerializedName("label")
        private String label;

        public AttributesPrice getAttributes() {
            return attributes;
        }

        public void setAttributes(AttributesPrice attributes) {
            this.attributes = attributes;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public static class AttributesPrice {
        @Expose
        @SerializedName("currency")
        private String currency;
        @Expose
        @SerializedName("amount")
        private String amount;

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
    }

    public static class RightsEntry {
        @Expose
        @SerializedName("label")
        private String label;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public static class Name {
        @Expose
        @SerializedName("label")
        private String label;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public static class Author {
        @Expose
        @SerializedName("uri")
        private Uri uri;
        @Expose
        @SerializedName("name")
        private Name name;

        public Uri getUri() {
            return uri;
        }

        public void setUri(Uri uri) {
            this.uri = uri;
        }

        public Name getName() {
            return name;
        }

        public void setName(Name name) {
            this.name = name;
        }
    }

    public static class Uri {
        @Expose
        @SerializedName("label")
        private String label;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public static class NameEntry {
        @Expose
        @SerializedName("label")
        private String label;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }
}




