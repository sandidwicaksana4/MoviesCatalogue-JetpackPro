package com.app.aplikasiku.moviescatalogue.viewmodel;

import android.util.Log;

import com.app.aplikasiku.moviescatalogue.data.entity.DetailTvShowItem;
import com.app.aplikasiku.moviescatalogue.data.entity.MovieItem;
import com.app.aplikasiku.moviescatalogue.data.entity.TvShowItem;
import com.app.aplikasiku.moviescatalogue.data.remote.CatalogueRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

public class FavoriteViewModel extends ViewModel {
    private CatalogueRepository catalogueRepository;

    FavoriteViewModel(@NonNull CatalogueRepository catalogueRepository) {
        this.catalogueRepository = catalogueRepository;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d("xxx", "data cleared");
    }

    public LiveData<PagedList<MovieItem>> getAllFavMovie() {
        return new LivePagedListBuilder<>(catalogueRepository.getAllFavMovie(), 10).build();
    }

    public LiveData<PagedList<TvShowItem>> getAllFavTv() {
        return new LivePagedListBuilder<>(catalogueRepository.getAllFavTv(), 10).build();
    }
}
