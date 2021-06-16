package com.app.aplikasiku.moviescatalogue.network;

import android.util.Log;

import com.app.aplikasiku.moviescatalogue.BuildConfig;
import com.app.aplikasiku.moviescatalogue.adapter.Language;
import com.app.aplikasiku.moviescatalogue.data.entity.DetailMovieItem;
import com.app.aplikasiku.moviescatalogue.data.entity.DetailTvShowItem;
import com.app.aplikasiku.moviescatalogue.data.entity.MovieItem;
import com.app.aplikasiku.moviescatalogue.data.entity.TvShowItem;
import com.app.aplikasiku.moviescatalogue.data.remote.response.MovieResponse;
import com.app.aplikasiku.moviescatalogue.data.remote.response.TvShowResponse;
import com.app.aplikasiku.moviescatalogue.utils.EspressoIdlingResource;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkCall {

    private static NetworkCall INSTANCE;
    private static ApiServer apiServer = ApiClient.getClient().create(ApiServer.class);

    private NetworkCall() {
    }


    public static NetworkCall getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NetworkCall();
        }
        return INSTANCE;
    }


    public void getAllMovie(MutableLiveData<List<MovieItem>> liveData) {
        EspressoIdlingResource.increment();
        Call<MovieResponse> call = apiServer.getListMovie(BuildConfig.TMDB_API_KEY,Language.getCountry());
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    MovieResponse moviesResponse = response.body();
                    if (moviesResponse != null && moviesResponse.getResults() != null) {
                        liveData.postValue(moviesResponse.getResults());
                    } else {
                        Log.d("NetworkCall", "Empty Data");
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d("NetworkCall", "Failed Get Data!");
            }
        });
        EspressoIdlingResource.decrement();
    }

    public void getAllTvShow(MutableLiveData<List<TvShowItem>> liveData) {
        EspressoIdlingResource.increment();
        Call<TvShowResponse> call = apiServer.getListTv(BuildConfig.TMDB_API_KEY,Language.getCountry());
        call.enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(Call<TvShowResponse> call, Response<TvShowResponse> response) {
                if (response.isSuccessful()) {
                    TvShowResponse tvShowResponse = response.body();
                    if (tvShowResponse != null && tvShowResponse.getResults() != null) {
                        liveData.postValue(tvShowResponse.getResults());
                    } else {
                        Log.d("NetworkCall", "Empty Data");
                    }
                }
            }

            @Override
            public void onFailure(Call<TvShowResponse> call, Throwable t) {
                Log.d("NetworkCall", "Failed Get Data!");
            }
        });
        EspressoIdlingResource.decrement();
    }

    public LiveData<DetailMovieItem> getDetailMovie(int movie_id) {
        EspressoIdlingResource.increment();
        MutableLiveData<DetailMovieItem> movieDataById = new MutableLiveData<>();
        Call<DetailMovieItem> call = apiServer.getDetailMovie(movie_id,BuildConfig.TMDB_API_KEY,Language.getCountry());
        call.enqueue(new Callback<DetailMovieItem>() {
            @Override
            public void onResponse(Call<DetailMovieItem> call, Response<DetailMovieItem> response) {
                if (response.isSuccessful()) {
                    DetailMovieItem detailMovieItem = response.body();
                    if (detailMovieItem != null) {
                        movieDataById.postValue(detailMovieItem);
                    } else {
                        Log.d("NetworkCall", "Empty Data");
                    }
                }
            }

            @Override
            public void onFailure(Call<DetailMovieItem> call, Throwable t) {
                Log.d("NetworkCall", "Failed Get Data!");
            }
        });
        EspressoIdlingResource.decrement();
        return movieDataById;

    }

    public LiveData<DetailTvShowItem> getDetailTvShow(int tv_id) {
        EspressoIdlingResource.increment();
        MutableLiveData<DetailTvShowItem> tvDataById = new MutableLiveData<>();
        Call<DetailTvShowItem> call = apiServer.getDetailTv(tv_id,BuildConfig.TMDB_API_KEY,Language.getCountry());
        call.enqueue(new Callback<DetailTvShowItem>() {
            @Override
            public void onResponse(Call<DetailTvShowItem> call, Response<DetailTvShowItem> response) {
                if (response.isSuccessful()) {
                    DetailTvShowItem detailTvShowItem = response.body();
                    if (detailTvShowItem != null) {
                        tvDataById.postValue(detailTvShowItem);
                    } else {
                        Log.d("NetworkCall", "Empty Data");
                    }
                }
            }

            @Override
            public void onFailure(Call<DetailTvShowItem> call, Throwable t) {
                Log.d("NetworkCall", "Failed Get Data!");
            }
        });
        EspressoIdlingResource.decrement();
        return tvDataById;
    }
}
