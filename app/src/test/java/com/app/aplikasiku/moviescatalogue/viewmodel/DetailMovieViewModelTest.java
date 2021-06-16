package com.app.aplikasiku.moviescatalogue.viewmodel;

import com.app.aplikasiku.moviescatalogue.data.entity.DetailMovieItem;
import com.app.aplikasiku.moviescatalogue.data.remote.CatalogueRepository;
import com.app.aplikasiku.moviescatalogue.utils.FakeDummy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DetailMovieViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private DetailMovieViewModel viewModel;
    private CatalogueRepository catalogueRepository = mock(CatalogueRepository.class);
    private DetailMovieItem dummyMovie = FakeDummy.generateDummyDetailMovies().get(0);
    private int movieId = dummyMovie.getId();

    @Before
    public void setUp() {
        viewModel = new DetailMovieViewModel(catalogueRepository);
        viewModel.setMovieId(movieId);
    }

    @Test
    public void getMovieId() {
        MutableLiveData<DetailMovieItem> movieResult = new MutableLiveData<>();
        movieResult.setValue(dummyMovie);

        when(catalogueRepository.getDetailDataMovie(movieId)).thenReturn(movieResult);

        Observer<DetailMovieItem> observer = mock(Observer.class);

        viewModel.getDetailMovies().observeForever(observer);

        verify(observer).onChanged(dummyMovie);
    }

}