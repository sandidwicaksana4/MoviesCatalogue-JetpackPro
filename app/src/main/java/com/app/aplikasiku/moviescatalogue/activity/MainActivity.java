package com.app.aplikasiku.moviescatalogue.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.app.aplikasiku.moviescatalogue.R;
import com.app.aplikasiku.moviescatalogue.fragment.FavoriteFragment;
import com.app.aplikasiku.moviescatalogue.fragment.ListMovieFragment;
import com.app.aplikasiku.moviescatalogue.fragment.ListTvShowFragment;
import com.app.aplikasiku.moviescatalogue.utils.EspressoIdlingResource;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {
    private ActionBar toolbar;
    private Fragment fragment;
    private String title = "";
    private static final String KEY_TITLE = "title";

    BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = getSupportActionBar();

        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.item_movie:
                    title = getString(R.string.title_film);
                    menuItem.setTitle(title);
                    fragment = new ListMovieFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.item_tv:
                    title = getString(R.string.title_tv);
                    menuItem.setTitle(title);
                    fragment = new ListTvShowFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.item_favorite:
                    title = getString(R.string.title_fav);
                    menuItem.setTitle(title);
                    fragment = new FavoriteFragment();
                    loadFragment(fragment);
                    return true;
            }
            return true;
        });
        if (savedInstanceState == null) {
            EspressoIdlingResource.increment();
            loadBeranda();
        } else {
            title = savedInstanceState.getString(KEY_TITLE);
            toolbar.setTitle(title);
        }
        EspressoIdlingResource.decrement();
    }

    private void loadFragment(Fragment fragment) {
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        // load fragment
        toolbar.setTitle(title);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.commit();
    }

    private void loadBeranda() {
        title = getString(R.string.title_film);
        toolbar.setTitle(title);
        fragment = new ListMovieFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment).commit();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(R.string.exit);
        builder.setMessage(R.string.pesan_exit);

        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                moveTaskToBack(true);
                finish();
                dialog.dismiss();
            }

        });
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(KEY_TITLE, title);
        super.onSaveInstanceState(outState);
    }

}
