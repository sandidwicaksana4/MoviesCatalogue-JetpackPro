package com.app.aplikasiku.moviescatalogue.data.remote.response;

import com.app.aplikasiku.moviescatalogue.data.entity.TvShowItem;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvShowResponse {
    @SerializedName("results")
    private List<TvShowItem> results;

    @SerializedName("page")
    private int page;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("total_results")
    private int totalResults;

    public List<TvShowItem> getResults() {
        return results;
    }
}
