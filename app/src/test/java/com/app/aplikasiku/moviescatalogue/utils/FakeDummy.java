package com.app.aplikasiku.moviescatalogue.utils;

import com.app.aplikasiku.moviescatalogue.data.entity.DetailMovieItem;
import com.app.aplikasiku.moviescatalogue.data.entity.DetailTvShowItem;
import com.app.aplikasiku.moviescatalogue.data.entity.MovieItem;
import com.app.aplikasiku.moviescatalogue.data.entity.TvShowItem;

import java.util.ArrayList;

public class FakeDummy {

    public static ArrayList<MovieItem> generateDummyMovies() {

        ArrayList<MovieItem> movies = new ArrayList<>();

        movies.add(new MovieItem(
                121,
                "Joker",
                "https://image.tmdb.org/t/p/w342//udDclJoHjfjb8Ekgsd4FDteOkCU.jpg",
                "https://image.tmdb.org/t/p/w342//n6bUvigpRFqSwmPp1m2YADdbRBc.jpg",
                "02 Oct 2019",
                "en",
                8.4,
                "During the 1980s, a failed stand-up comedian is driven insane and turns to a life of crime and chaos in Gotham City while becoming an infamous psychopathic crime figure."));
        return movies;
    }

    public static ArrayList<DetailMovieItem> generateDummyDetailMovies() {

        ArrayList<DetailMovieItem> movies = new ArrayList<>();

        movies.add(new DetailMovieItem(
                121,
                "Joker",
                "Released",
                "https://image.tmdb.org/t/p/w342//udDclJoHjfjb8Ekgsd4FDteOkCU.jpg",
                "https://image.tmdb.org/t/p/w342//n6bUvigpRFqSwmPp1m2YADdbRBc.jpg",
                "02 Oct 2019",
                "en",
                45,
                "During the 1980s, a failed stand-up comedian is driven insane and turns to a life of crime and chaos in Gotham City while becoming an infamous psychopathic crime figure."));
        return movies;
    }

    public static ArrayList<TvShowItem> generateDummyTv() {

        ArrayList<TvShowItem> tvshow = new ArrayList<>();

        tvshow.add(new TvShowItem(
                121,
                "The Mandalorion",
                "https://image.tmdb.org/t/p/w342//BbNvKCuEF4SRzFXR16aK6ISFtR.jpg",
                "https://image.tmdb.org/t/p/w342//o7qi2v4uWQ8bZ1tW3KI0Ztn2epk.jpg",
                "12 Nov 2019",
                "en",
                7.8,
                "Set after the fall of the Empire and before the emergence of the First Order, we follow the travails of a lone gunfighter in the outer reaches of the galaxy far from the authority of the New Republic."));

        return tvshow;
    }

    public static ArrayList<DetailTvShowItem> generateDummyDetailTv() {

        ArrayList<DetailTvShowItem> tv = new ArrayList<>();

        tv.add(new DetailTvShowItem(
                121,
                "The Mandalorion",
                "Returning Series",
                "https://image.tmdb.org/t/p/w342//BbNvKCuEF4SRzFXR16aK6ISFtR.jpg",
                "https://image.tmdb.org/t/p/w342//o7qi2v4uWQ8bZ1tW3KI0Ztn2epk.jpg",
                "12 Nov 2019",
                "en",
                19,
                20,
                "Set after the fall of the Empire and before the emergence of the First Order, we follow the travails of a lone gunfighter in the outer reaches of the galaxy far from the authority of the New Republic."));
        return tv;
    }
}
