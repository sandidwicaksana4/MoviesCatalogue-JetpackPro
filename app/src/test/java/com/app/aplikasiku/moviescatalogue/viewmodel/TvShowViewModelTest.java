package com.app.aplikasiku.moviescatalogue.viewmodel;

import com.app.aplikasiku.moviescatalogue.data.entity.TvShowItem;
import com.app.aplikasiku.moviescatalogue.data.remote.CatalogueRepository;
import com.app.aplikasiku.moviescatalogue.utils.FakeDummy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class TvShowViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private TvShowViewModel viewModel;
    private CatalogueRepository catalogueRepository = mock (CatalogueRepository.class);

    @Before
    public void setUp() {
        viewModel = spy(new TvShowViewModel(catalogueRepository));
    }

    @Test
    public void getTvShow() {

        ArrayList<TvShowItem> dummyTv = FakeDummy.generateDummyTv();

        MutableLiveData<List<TvShowItem>> tvshow = new MutableLiveData<>();
        tvshow.setValue(dummyTv);

        Observer<List<TvShowItem>> observer = mock (Observer.class);
        viewModel.tvShow = tvshow;

        viewModel.tvShow.observeForever(observer);
        assertEquals(viewModel.tvShow, viewModel.getTvShowData());

        verify(observer).onChanged(dummyTv);
    }

}