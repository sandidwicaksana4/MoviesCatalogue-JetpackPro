package com.app.aplikasiku.moviescatalogue.viewmodel;

import android.app.Application;

import com.app.aplikasiku.moviescatalogue.data.remote.CatalogueRepository;
import com.app.aplikasiku.moviescatalogue.utils.Injection;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private static volatile ViewModelFactory INSTANCE;

    private final CatalogueRepository catalogueRepository;

    private ViewModelFactory(CatalogueRepository catalogueRepository) {
        this.catalogueRepository = catalogueRepository;
    }

    public static ViewModelFactory getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(Injection.provideRepository(application));
                }
            }
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(MovieViewModel.class)) {
            //noinspection unchecked
            return (T) new MovieViewModel(catalogueRepository);
        } else if (modelClass.isAssignableFrom(TvShowViewModel.class)) {
            //noinspection unchecked
            return (T) new TvShowViewModel(catalogueRepository);
        } else if (modelClass.isAssignableFrom(DetailMovieViewModel.class)) {
            //noinspection unchecked
            return (T) new DetailMovieViewModel(catalogueRepository);
        } else if (modelClass.isAssignableFrom(DetailTvShowViewModel.class)) {
            //noinspection unchecked
            return (T) new DetailTvShowViewModel(catalogueRepository);
        }else if (modelClass.isAssignableFrom(FavoriteViewModel.class)) {
            //noinspection unchecked
            return (T) new FavoriteViewModel(catalogueRepository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }

}
