package com.app.aplikasiku.moviescatalogue.viewmodel;

import com.app.aplikasiku.moviescatalogue.data.entity.MovieItem;
import com.app.aplikasiku.moviescatalogue.data.entity.TvShowItem;
import com.app.aplikasiku.moviescatalogue.data.remote.CatalogueRepository;
import com.app.aplikasiku.moviescatalogue.utils.FakeDummy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private MovieViewModel viewModel;
    private CatalogueRepository catalogueRepository = mock (CatalogueRepository.class);

    @Before
    public void setUp() {
        viewModel = spy(new MovieViewModel(catalogueRepository));
    }

    @Test
    public void getMovies() {

        ArrayList<MovieItem> dummyMovies = FakeDummy.generateDummyMovies();

        MutableLiveData<List<MovieItem>> movies = new MutableLiveData<>();
        movies.setValue(dummyMovies);

        Observer<List<MovieItem>> observer = mock (Observer.class);
        viewModel.movies = movies;

        viewModel.movies.observeForever(observer);
        assertEquals(viewModel.movies, viewModel.getMovieData());

        verify(observer).onChanged(dummyMovies);
    }
}