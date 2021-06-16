package com.app.aplikasiku.moviescatalogue.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.app.aplikasiku.moviescatalogue.R;
import com.app.aplikasiku.moviescatalogue.adapter.MovieAdapter;
import com.app.aplikasiku.moviescatalogue.data.entity.MovieItem;
import com.app.aplikasiku.moviescatalogue.utils.EspressoIdlingResource;
import com.app.aplikasiku.moviescatalogue.viewmodel.MovieViewModel;
import com.app.aplikasiku.moviescatalogue.viewmodel.ViewModelFactory;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListMovieFragment extends Fragment{

    private MovieAdapter movieAdapter;
    private MovieViewModel viewModel;

    SwipeRefreshLayout swipe;
    RecyclerView movieList;
    ProgressBar pb;

    public ListMovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_movie, container, false);
    }

    @NonNull
    private MovieViewModel obtainViewModel(FragmentActivity activity) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(MovieViewModel.class);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        movieList = view.findViewById(R.id.rc_film);
        swipe = view.findViewById(R.id.swipe_refresh);
        pb = view.findViewById(R.id.progress_bar);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity()!=null) {
            pb.setVisibility(View.VISIBLE);
            viewModel = obtainViewModel(getActivity());
            EspressoIdlingResource.increment();
            viewModel.getMovieData().observe(this, movies ->{
                if (movies != null) {
                    pb.setVisibility(View.GONE);
                    movieAdapter = new MovieAdapter(getActivity(),movies);
                    movieList.setLayoutManager(new LinearLayoutManager(getContext()));
                    movieList.setHasFixedSize(true);
                    movieList.setAdapter(movieAdapter);
                    movieAdapter.notifyDataSetChanged();
                    EspressoIdlingResource.decrement();
                }
            });
        }
        swipe.setOnRefreshListener(() -> {
            loadData();
            loadView();
            swipe.setRefreshing(false);
        });
    }

    private void loadData() {
        viewModel.getMovieData().observe(this, movies ->{
            if (movies != null) {
                pb.setVisibility(View.GONE);
                movieAdapter = new MovieAdapter(getActivity(),movies);
                movieAdapter.notifyDataSetChanged();
            }
        });
    }

    private void loadView(){
        movieList.setLayoutManager(new LinearLayoutManager(getContext()));
        movieList.setHasFixedSize(true);
        movieList.setAdapter(movieAdapter);
    }

}
