package com.app.aplikasiku.moviescatalogue.viewmodel;

import com.app.aplikasiku.moviescatalogue.data.entity.DetailTvShowItem;
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

public class DetailTvShowViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private DetailTvShowViewModel viewModel;
    private CatalogueRepository catalogueRepository = mock(CatalogueRepository.class);
    private DetailTvShowItem dummytv = FakeDummy.generateDummyDetailTv().get(0);
    private int tvId = dummytv.getId();

    @Before
    public void setUp(){
        viewModel = new DetailTvShowViewModel(catalogueRepository);
        viewModel.setTvShowId(tvId);
    }

    @Test
    public void getTvShowId() {
        MutableLiveData<DetailTvShowItem> tvresult = new MutableLiveData<>();
        tvresult.setValue(dummytv);

        when(catalogueRepository.getDetailDataTvShow(tvId)).thenReturn(tvresult);

        Observer<DetailTvShowItem> observer = mock(Observer.class);

        viewModel.getDetailTvShow().observeForever(observer);

        verify(observer).onChanged(dummytv);
    }

}