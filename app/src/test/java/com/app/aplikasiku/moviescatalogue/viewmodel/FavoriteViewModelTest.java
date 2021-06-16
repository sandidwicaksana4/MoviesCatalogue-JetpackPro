package com.app.aplikasiku.moviescatalogue.viewmodel;

import com.app.aplikasiku.moviescatalogue.data.entity.MovieItem;
import com.app.aplikasiku.moviescatalogue.data.entity.TvShowItem;
import com.app.aplikasiku.moviescatalogue.data.remote.CatalogueRepository;
import com.app.aplikasiku.moviescatalogue.utils.FakeDummy;
import com.app.aplikasiku.moviescatalogue.utils.PagedListTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.paging.DataSource;
import androidx.paging.PagedList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FavoriteViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private FavoriteViewModel viewModel;
    private CatalogueRepository catalogueRepository = mock(CatalogueRepository.class);


    @Before
    public void setUp() { viewModel = new FavoriteViewModel(catalogueRepository);
    }

    @Test
    public void getAllFavMovie() {
        ArrayList<MovieItem> movieResults = FakeDummy.generateDummyMovies();
        DataSource.Factory<Integer, MovieItem> dataSourceFactory = mock(DataSource.Factory.class);

        when(catalogueRepository.getAllFavMovie()).thenReturn(dataSourceFactory);
        PagedList<MovieItem> result = PagedListTest.mockPagedList(movieResults);

        viewModel.getAllFavMovie();

        verify(catalogueRepository).getAllFavMovie();
        assertNotNull(result);
        assertEquals(movieResults.size(), result.size());
    }

    @Test
    public void getAllFavTv() {
        ArrayList<TvShowItem> tvResults = FakeDummy.generateDummyTv();
        DataSource.Factory<Integer, TvShowItem> dataSourceFactory = mock(DataSource.Factory.class);

        when(catalogueRepository.getAllFavTv()).thenReturn(dataSourceFactory);
        PagedList<TvShowItem> result = PagedListTest.mockPagedList(tvResults);

        viewModel.getAllFavTv();

        verify(catalogueRepository).getAllFavTv();
        assertNotNull(result);
        assertEquals(tvResults.size(), result.size());
    }
}