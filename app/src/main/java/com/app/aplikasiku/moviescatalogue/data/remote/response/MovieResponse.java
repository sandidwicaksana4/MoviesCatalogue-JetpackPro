package com.app.aplikasiku.moviescatalogue.data.remote.response;

import com.app.aplikasiku.moviescatalogue.data.entity.MovieItem;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse{

    @SerializedName("page")
    private int page;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("results")
    private List<MovieItem> results;

    @SerializedName("total_results")
    private int totalResults;

    public List<MovieItem> getResults() {
        return results;
    }
}
