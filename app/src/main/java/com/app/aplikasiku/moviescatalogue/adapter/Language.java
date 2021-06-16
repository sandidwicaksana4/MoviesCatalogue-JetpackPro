package com.app.aplikasiku.moviescatalogue.adapter;

import java.util.Locale;


public class Language {

    public static String getCountry() {
        String country = Locale.getDefault().getCountry().toLowerCase();

        if ("in".equals(country)) {
        } else {
            country = "en";
        }
        return country;
    }
}
