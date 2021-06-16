package com.app.aplikasiku.moviescatalogue.utils;

import android.app.Application;

import com.app.aplikasiku.moviescatalogue.data.remote.CatalogueRepository;
import com.app.aplikasiku.moviescatalogue.network.NetworkCall;

public class Injection {
    public static CatalogueRepository provideRepository(Application application) {
        NetworkCall networkCall = NetworkCall.getInstance();
        return CatalogueRepository.getInstance(networkCall,application);
    }
}
