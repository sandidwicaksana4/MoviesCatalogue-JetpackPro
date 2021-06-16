package com.app.aplikasiku.moviescatalogue.data.dao;

import android.content.Context;

import com.app.aplikasiku.moviescatalogue.data.entity.DetailMovieItem;
import com.app.aplikasiku.moviescatalogue.data.entity.DetailTvShowItem;
import com.app.aplikasiku.moviescatalogue.data.entity.MovieItem;
import com.app.aplikasiku.moviescatalogue.data.entity.TvShowItem;
import com.app.aplikasiku.moviescatalogue.utils.Converter;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {MovieItem.class, TvShowItem.class}, version = 1, exportSchema = false)
@TypeConverters(Converter.class)
public abstract class FavoriteRoomDatabase extends RoomDatabase {
    private static volatile FavoriteRoomDatabase INSTANCE;

    public static FavoriteRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (FavoriteRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FavoriteRoomDatabase.class, "movies.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract MovieDao movieDao();

    public abstract TvShowDao tvShowDao();

}
