package com.app.aplikasiku.moviescatalogue.network;

import com.app.aplikasiku.moviescatalogue.data.entity.DetailMovieItem;
import com.app.aplikasiku.moviescatalogue.data.entity.DetailTvShowItem;
import com.app.aplikasiku.moviescatalogue.data.remote.response.MovieResponse;
import com.app.aplikasiku.moviescatalogue.data.remote.response.TvShowResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServer {

    @GET("discover/movie")
    Call<MovieResponse> getListMovie(
            @Query("api_key") String apiKey,
            @Query("language") String language);

    @GET("discover/tv")
    Call<TvShowResponse> getListTv(
            @Query("api_key") String apiKey,
            @Query("language") String language);

    @GET("movie/{movie_id}")
    Call<DetailMovieItem> getDetailMovie(
            @Path("movie_id") int movie_id,
            @Query("api_key") String apiKey,
            @Query("language") String language);

    @GET("tv/{tv_id}")
    Call<DetailTvShowItem> getDetailTv(
            @Path("tv_id") int tv_id,
            @Query("api_key") String apiKey,
            @Query("language") String language);

}
