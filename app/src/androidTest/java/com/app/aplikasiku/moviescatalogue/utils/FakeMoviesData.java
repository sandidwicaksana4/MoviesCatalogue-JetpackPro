package com.app.aplikasiku.moviescatalogue.utils;

import com.app.aplikasiku.moviescatalogue.data.entity.MovieItem;

import java.util.ArrayList;

public class FakeMoviesData {
    public static ArrayList<MovieItem> generateMovie(){

        ArrayList<MovieItem> movieItems = new ArrayList<>();

        movieItems.add(new MovieItem(121,
                "Joker",
                "https://image.tmdb.org/t/p/w342//udDclJoHjfjb8Ekgsd4FDteOkCU.jpg",
                "https://image.tmdb.org/t/p/w342//n6bUvigpRFqSwmPp1m2YADdbRBc.jpg",
                "02 Oct 2019",
                "en",
                8.4,
                "During the 1980s, a failed stand-up comedian is driven insane and turns to a life of crime and chaos in Gotham City while becoming an infamous psychopathic crime figure."));

        return movieItems;
    }
}
