package com.app.aplikasiku.moviescatalogue.network;

import com.app.aplikasiku.moviescatalogue.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private final static String BASE_URL = BuildConfig.BASE_URL_TMDB;

    static Retrofit getClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(logging).connectTimeout(30, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).build();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }
}
