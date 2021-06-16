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
public class DetailMovieItem implements Parcelable {

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

    @ColumnInfo (name = "status")
    @SerializedName("status")
    private String status;

    @Ignore
    @SerializedName("genres")
    private List<GenresItem> genre;

    @ColumnInfo (name = "runtime")
    @SerializedName("runtime")
    private Integer runtime;

    @Ignore
    @SerializedName("production_companies")
    private List<ProductionCompaniesItem> productionCompanies;

    @ColumnInfo (name = "language")
    @SerializedName("original_language")
    private String language;

    @Ignore
    @SerializedName("revenue")
    private double revenue;

    @Ignore
    @SerializedName("budget")
    private double budget;

    @ColumnInfo (name = "decription")
    @SerializedName("overview")
    private String overview;

    public DetailMovieItem() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return "https://image.tmdb.org/t/p/w342/" + poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getBackground() {
        return "https://image.tmdb.org/t/p/w342/" + background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getPopular() {
        return popular;
    }

    public void setPopular(double popular) {
        this.popular = popular;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<GenresItem> getGenre() {
        return genre;
    }

    public void setGenre(List<GenresItem> genre) {
        this.genre = genre;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public List<ProductionCompaniesItem> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(List<ProductionCompaniesItem> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }


    public DetailMovieItem(Integer id, String title, String status, String poster, String background, String releaseDate, String language, Integer runtime, String overview) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.poster = poster;
        this.background = background;
        this.releaseDate = releaseDate;
        this.language = language;
        this.runtime = runtime;
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
        dest.writeString(this.status);
        dest.writeList(this.genre);
        dest.writeValue(this.runtime);
        dest.writeList(this.productionCompanies);
        dest.writeString(this.language);
        dest.writeDouble(this.revenue);
        dest.writeDouble(this.budget);
        dest.writeString(this.overview);
    }

    protected DetailMovieItem(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.title = in.readString();
        this.poster = in.readString();
        this.background = in.readString();
        this.releaseDate = in.readString();
        this.popular = in.readDouble();
        this.status = in.readString();
        this.genre = new ArrayList<GenresItem>();
        in.readList(this.genre, GenresItem.class.getClassLoader());
        this.runtime = (Integer) in.readValue(Integer.class.getClassLoader());
        this.productionCompanies = new ArrayList<ProductionCompaniesItem>();
        in.readList(this.productionCompanies, ProductionCompaniesItem.class.getClassLoader());
        this.language = in.readString();
        this.revenue = in.readDouble();
        this.budget = in.readDouble();
        this.overview = in.readString();
    }

    public static final Creator<DetailMovieItem> CREATOR = new Creator<DetailMovieItem>() {
        @Override
        public DetailMovieItem createFromParcel(Parcel source) {
            return new DetailMovieItem(source);
        }

        @Override
        public DetailMovieItem[] newArray(int size) {
            return new DetailMovieItem[size];
        }
    };
}
