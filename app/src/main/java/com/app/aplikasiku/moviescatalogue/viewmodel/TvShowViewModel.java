package com.app.aplikasiku.moviescatalogue.viewmodel;

import android.util.Log;

import com.app.aplikasiku.moviescatalogue.data.entity.TvShowItem;
import com.app.aplikasiku.moviescatalogue.data.remote.CatalogueRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TvShowViewModel extends ViewModel {

    public MutableLiveData<List<TvShowItem>> tvShow = new MutableLiveData<>();
    private CatalogueRepository catalogueRepository;


    TvShowViewModel(CatalogueRepository tvShowRepository) {
        this.catalogueRepository = tvShowRepository;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d("xxx", "data cleared");
    }

    public LiveData<List<TvShowItem>> getTvShowData() {
        if (tvShow.getValue() == null) {
            catalogueRepository.getTvShowData(tvShow);
        }
        return tvShow;
    }
}
