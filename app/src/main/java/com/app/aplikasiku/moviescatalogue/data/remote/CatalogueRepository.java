package com.app.aplikasiku.moviescatalogue.data.remote;

import android.app.Application;

import com.app.aplikasiku.moviescatalogue.data.dao.FavoriteRoomDatabase;
import com.app.aplikasiku.moviescatalogue.data.dao.MovieDao;
import com.app.aplikasiku.moviescatalogue.data.dao.TvShowDao;
import com.app.aplikasiku.moviescatalogue.data.entity.DetailMovieItem;
import com.app.aplikasiku.moviescatalogue.data.entity.DetailTvShowItem;
import com.app.aplikasiku.moviescatalogue.data.entity.MovieItem;
import com.app.aplikasiku.moviescatalogue.data.entity.TvShowItem;
import com.app.aplikasiku.moviescatalogue.network.NetworkCall;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class CatalogueRepository {

    private static CatalogueRepository INSTANCE;
    private NetworkCall networkCall;
    private MovieDao movieDao;
    private TvShowDao tvShowDao;
    private ExecutorService executorService;

    private CatalogueRepository(NetworkCall networkCall,Application application) {
        this.networkCall = networkCall;
        executorService = Executors.newSingleThreadExecutor();

        FavoriteRoomDatabase db = FavoriteRoomDatabase.getDatabase(application);
        movieDao = db.movieDao();
        tvShowDao = db.tvShowDao();
    }

    public static CatalogueRepository getInstance(NetworkCall networkCall, Application application) {
        if (INSTANCE == null) {
            INSTANCE = new CatalogueRepository(networkCall,application);
        }
        return INSTANCE;
    }

    // Methods for MovieFragment
    public void getMovieData(MutableLiveData<List<MovieItem>> liveData) {
        networkCall.getAllMovie(liveData);
    }

    // Methods for TvShowFragment
    public void getTvShowData(MutableLiveData<List<TvShowItem>> liveData) {
        networkCall.getAllTvShow(liveData);
    }

    // Methods for DetailMovie
    public LiveData<DetailMovieItem> getDetailDataMovie(int movie_id) {
        return networkCall.getDetailMovie(movie_id);
    }

    // Methods for DetailTvShow
    public LiveData<DetailTvShowItem> getDetailDataTvShow(int tv_id) {
        return networkCall.getDetailTvShow(tv_id);
    }

    public DataSource.Factory<Integer, MovieItem> getAllFavMovie() {
        return movieDao.getAllfavmovie();
    }

    public DataSource.Factory<Integer, TvShowItem> getAllFavTv() {
        return tvShowDao.getAllfavtvshow();
    }

    public LiveData<DetailMovieItem> getMovieByIdRoom(int id) {
        return movieDao.getMovieById(id);
    }

    public LiveData<DetailTvShowItem> getTvByIdRoom(int id) {
        return tvShowDao.getTvShowById(id);
    }

    public void insertMovie(MovieItem movieResults) {
        executorService.execute(() -> movieDao.insert(movieResults));
    }

    public void deleteMovie(final MovieItem movieResults) {
        executorService.execute(() -> movieDao.delete(movieResults));
    }

    public void insertTvShow(final TvShowItem tvResults) {
        executorService.execute(() -> tvShowDao.insert(tvResults));
    }

    public void deleteTvShow(final TvShowItem tvResults) {
        executorService.execute(() -> tvShowDao.delete(tvResults));
    }
}
