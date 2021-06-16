package com.app.aplikasiku.moviescatalogue.data.dao;

import com.app.aplikasiku.moviescatalogue.data.entity.DetailTvShowItem;
import com.app.aplikasiku.moviescatalogue.data.entity.TvShowItem;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface TvShowDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(TvShowItem favorite);

    @Delete()
    void delete(TvShowItem favorite);

    @Query("SELECT *FROM tvshowTable WHERE id =:id")
    LiveData<DetailTvShowItem> getTvShowById(int id);

    @Query("SELECT *FROM tvshowTable ORDER BY title ASC")
    DataSource.Factory<Integer, TvShowItem> getAllfavtvshow();
}
