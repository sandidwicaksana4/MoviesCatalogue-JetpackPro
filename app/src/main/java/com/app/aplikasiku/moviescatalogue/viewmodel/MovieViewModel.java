package com.app.aplikasiku.moviescatalogue.viewmodel;

import android.util.Log;

import com.app.aplikasiku.moviescatalogue.data.entity.MovieItem;
import com.app.aplikasiku.moviescatalogue.data.remote.CatalogueRepository;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MovieViewModel extends ViewModel {

    public MutableLiveData<List<MovieItem>> movies = new MutableLiveData<>();
    private CatalogueRepository catalogueRepository;

    MovieViewModel(CatalogueRepository catalogueRepository) {
        this.catalogueRepository = catalogueRepository;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d("xxx", "data cleared");
    }

    public LiveData<List<MovieItem>> getMovieData() {
        if (movies.getValue() == null) {
            catalogueRepository.getMovieData(movies);
        }
        return movies;
    }

}
