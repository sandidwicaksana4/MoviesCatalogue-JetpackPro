package com.app.aplikasiku.moviescatalogue.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tvshowTable")
public class TvShowItem implements Parcelable {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private Integer id;

    @ColumnInfo (name = "title")
    @SerializedName("name")
    private String title;

    @ColumnInfo (name = "poster")
    @SerializedName("poster_path")
    private String poster;

    @ColumnInfo (name = "background")
    @SerializedName("backdrop_path")
    private String background;

    @ColumnInfo (name = "release_date")
    @SerializedName("first_air_date")
    private String releaseDate;

    @ColumnInfo (name = "language")
    @SerializedName("original_language")
    private String language;

    @Ignore
    @SerializedName("vote_average")
    private double popular;

    @Ignore
    @SerializedName("genre_ids")
    private List<Integer> genre;

    @ColumnInfo (name = "description")
    @SerializedName("overview")
    private String overview;

    public String getLanguage() {
        return language;
    }

    public Integer getId() {
        return id;
    }

    public List<Integer> getGenre() {
        return genre;
    }

    public String getOverview() {
        return overview;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster() {
        return poster;
    }

    public String getBackground() {
        return background;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public double getPopular() {
        return popular;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setPopular(double popular) {
        this.popular = popular;
    }

    public void setGenre(List<Integer> genre) {
        this.genre = genre;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public TvShowItem(){

    }

    public TvShowItem(Integer id, String title, String poster, String background, String releaseDate, String language, Double popular, String overview) {
        this.id = id;
        this.title = title;
        this.poster = poster;
        this.background = background;
        this.releaseDate = releaseDate;
        this.language = language;
        this.popular = popular;
        this.overview = overview;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.title);
        dest.writeString(this.poster);
        dest.writeString(this.background);
        dest.writeString(this.releaseDate);
        dest.writeString(this.language);
        dest.writeDouble(this.popular);
        dest.writeList(this.genre);
        dest.writeString(this.overview);
    }

    protected TvShowItem(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.title = in.readString();
        this.poster = in.readString();
        this.background = in.readString();
        this.releaseDate = in.readString();
        this.language = in.readString();
        this.popular = in.readDouble();
        this.genre = new ArrayList<Integer>();
        in.readList(this.genre, Integer.class.getClassLoader());
        this.overview = in.readString();
    }

    public static final Creator<TvShowItem> CREATOR = new Creator<TvShowItem>() {
        @Override
        public TvShowItem createFromParcel(Parcel source) {
            return new TvShowItem(source);
        }

        @Override
        public TvShowItem[] newArray(int size) {
            return new TvShowItem[size];
        }
    };
}
