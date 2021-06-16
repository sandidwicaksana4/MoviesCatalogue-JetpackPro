package com.app.aplikasiku.moviescatalogue.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.app.aplikasiku.moviescatalogue.R;
import com.app.aplikasiku.moviescatalogue.utils.EspressoIdlingResource;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {

    private Fragment fragment;
    private String title="";
    private static final String KEY_TITLE = "title";

    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        BottomNavigationView favorite = view.findViewById(R.id.favoritemenu);
        favorite.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.item_movie:
                    title = getString(R.string.title_film);
                    fragment = new FavoriteMovieFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.item_tv:
                    title = getString(R.string.title_tv);
                    fragment = new FavoriteTvShowFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        });

        if (savedInstanceState == null) {
            EspressoIdlingResource.increment();
            loadFilm();
        }else {
            title = savedInstanceState.getString(KEY_TITLE);
        }
        EspressoIdlingResource.decrement();
    }
    private void loadFragment(Fragment fragment) {
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        // load fragment
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_favorite, fragment);
        transaction.commit();
    }
    private void loadFilm(){
        title = getString(R.string.title_film);
        fragment = new FavoriteMovieFragment();
        getFragmentManager().beginTransaction().replace(R.id.frame_favorite,fragment).commit();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_TITLE,title);
    }
}
