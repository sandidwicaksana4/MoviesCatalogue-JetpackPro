package com.app.aplikasiku.moviescatalogue.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import androidx.room.TypeConverter;

public class Converter {
    @TypeConverter()
    public String genretoJson(List<Integer> data) {
        return new Gson().toJson(data);
    }

    @TypeConverter()
    public String generateJsontoData(String data) {
        return new Gson().fromJson(data, new TypeToken<List<Integer>>() {
        }.getType());
    }
}
