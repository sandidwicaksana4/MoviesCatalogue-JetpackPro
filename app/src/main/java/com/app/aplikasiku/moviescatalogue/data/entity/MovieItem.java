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

@Entity(tableName = "movieTable")
public class MovieItem implements Parcelable {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private Integer id;

    @ColumnInfo (name = "title")
    @SerializedName("title")
    private String title;

    @ColumnInfo (name = "poster")
    @SerializedName("poster_path")
    private String poster;

    @ColumnInfo (name = "background")
    @SerializedName("backdrop_path")
    private String background;

    @ColumnInfo (name = "release_date")
    @SerializedName("release_date")
    private String releaseDate;

    @Ignore
    @SerializedName("vote_average")
    private double popular;

    @Ignore
    @SerializedName("genre_ids")
    private List<Integer> genre;

    @ColumnInfo (name = "language")
    @SerializedName("original_language")
    private String language;

    @ColumnInfo (name = "decription")
    @SerializedName("overview")
    private String overview;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getPoster() {
        return poster;
    }

    public String getBackground() {
        return background;
    }

    public double getPopular() {
        return popular;
    }

    public List<Integer> getGenre() {
        return genre;
    }

    public String getLanguage() {
        return language;
    }

    public String getOverview() {
        return overview;
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

    public void setPopular(double popular) {
        this.popular = popular;
    }

    public void setGenre(List<Integer> genre) {
        this.genre = genre;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public MovieItem(){

    }

    public MovieItem(Integer id, String title, String poster, String background, String releaseDate, String language, Double popular, String overview) {
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
        dest.writeDouble(this.popular);
        dest.writeList(this.genre);
        dest.writeString(this.language);
        dest.writeString(this.overview);
    }

    protected MovieItem(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.title = in.readString();
        this.poster = in.readString();
        this.background = in.readString();
        this.releaseDate = in.readString();
        this.popular = in.readDouble();
        this.genre = new ArrayList<Integer>();
        in.readList(this.genre, Integer.class.getClassLoader());
        this.language = in.readString();
        this.overview = in.readString();
    }

    public static final Creator<MovieItem> CREATOR = new Creator<MovieItem>() {
        @Override
        public MovieItem createFromParcel(Parcel source) {
            return new MovieItem(source);
        }

        @Override
        public MovieItem[] newArray(int size) {
            return new MovieItem[size];
        }
    };
}