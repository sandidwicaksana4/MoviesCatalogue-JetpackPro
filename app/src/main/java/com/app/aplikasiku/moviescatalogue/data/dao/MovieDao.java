package com.app.aplikasiku.moviescatalogue.data.dao;

import com.app.aplikasiku.moviescatalogue.data.entity.DetailMovieItem;
import com.app.aplikasiku.moviescatalogue.data.entity.MovieItem;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(MovieItem favorite);

    @Delete()
    void delete(MovieItem favorite);

    @Query("SELECT * FROM movieTable WHERE id =:id")
    LiveData<DetailMovieItem> getMovieById(int id);

    @Query("SELECT * FROM movieTable ORDER BY title ASC")
    DataSource.Factory<Integer, MovieItem> getAllfavmovie();
}
