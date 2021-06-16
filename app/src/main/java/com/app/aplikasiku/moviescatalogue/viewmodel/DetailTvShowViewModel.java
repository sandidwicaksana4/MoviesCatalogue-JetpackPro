package com.app.aplikasiku.moviescatalogue.viewmodel;

import android.content.Intent;
import android.util.Log;

import com.app.aplikasiku.moviescatalogue.data.entity.DetailTvShowItem;
import com.app.aplikasiku.moviescatalogue.data.entity.TvShowItem;
import com.app.aplikasiku.moviescatalogue.data.remote.CatalogueRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class DetailTvShowViewModel extends ViewModel {

    private CatalogueRepository detailtvshowRepository;
    private Integer tvId;

    DetailTvShowViewModel(CatalogueRepository detailtvshowRepository) {
        this.detailtvshowRepository = detailtvshowRepository;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d("xxx", "data cleared");
    }

    public LiveData<DetailTvShowItem> getDetailTvShow(){ return detailtvshowRepository.getDetailDataTvShow(tvId);}

    public int getTvShowId() {
        return tvId;
    }

    public void setTvShowId(Integer tvId) {
        this.tvId = tvId;
    }

    public LiveData<DetailTvShowItem> getTvShowByIdRoom() {
        return detailtvshowRepository.getTvByIdRoom(tvId);
    }

    public void insertTv(TvShowItem tvshowResults) {
        detailtvshowRepository.insertTvShow(tvshowResults);
    }

    public void deleteTv(TvShowItem tvshowResults) {
        detailtvshowRepository.deleteTvShow(tvshowResults);
    }
}
