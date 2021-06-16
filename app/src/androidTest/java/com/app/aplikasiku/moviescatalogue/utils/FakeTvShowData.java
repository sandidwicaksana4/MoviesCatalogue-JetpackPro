package com.app.aplikasiku.moviescatalogue.utils;

import com.app.aplikasiku.moviescatalogue.data.entity.TvShowItem;

import java.util.ArrayList;

public class FakeTvShowData {

    public static ArrayList<TvShowItem> generateTv(){

        ArrayList<TvShowItem> tvShowItems = new ArrayList<>();

        tvShowItems.add(new TvShowItem(121,
                "The Mandalorion",
                "https://image.tmdb.org/t/p/w342//BbNvKCuEF4SRzFXR16aK6ISFtR.jpg",
                "https://image.tmdb.org/t/p/w342//o7qi2v4uWQ8bZ1tW3KI0Ztn2epk.jpg",
                "12 Nov 2019",
                "en",
                7.8,
                "Set after the fall of the Empire and before the emergence of the First Order, we follow the travails of a lone gunfighter in the outer reaches of the galaxy far from the authority of the New Republic."));


        return tvShowItems;
    }

}
