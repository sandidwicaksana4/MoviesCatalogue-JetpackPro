package com.app.aplikasiku.moviescatalogue.viewmodel;

import android.util.Log;

import com.app.aplikasiku.moviescatalogue.data.entity.DetailMovieItem;
import com.app.aplikasiku.moviescatalogue.data.entity.MovieItem;
import com.app.aplikasiku.moviescatalogue.data.remote.CatalogueRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class DetailMovieViewModel extends ViewModel {
    private CatalogueRepository detailMovieRepository;
    private Integer movieId;

    DetailMovieViewModel(CatalogueRepository detailMovieRepository) {
        this.detailMovieRepository = detailMovieRepository;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d("xxx", "data cleared");
    }

    public LiveData<DetailMovieItem> getDetailMovies(){ return detailMovieRepository.getDetailDataMovie(movieId);}

    public LiveData<DetailMovieItem> getMovieByIdRoom() {
        return detailMovieRepository.getMovieByIdRoom(movieId);
    }

    public void insertMovie(MovieItem movieResults) {
        detailMovieRepository.insertMovie(movieResults);
    }

    public void deleteMovie(MovieItem movieResults) {
        detailMovieRepository.deleteMovie(movieResults);
    }

}
