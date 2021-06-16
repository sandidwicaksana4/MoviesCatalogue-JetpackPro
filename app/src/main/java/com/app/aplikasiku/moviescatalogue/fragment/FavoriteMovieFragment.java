package com.app.aplikasiku.moviescatalogue.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.app.aplikasiku.moviescatalogue.adapter.MoviePagedListAdapter;
import com.app.aplikasiku.moviescatalogue.R;
import com.app.aplikasiku.moviescatalogue.utils.EspressoIdlingResource;
import com.app.aplikasiku.moviescatalogue.viewmodel.FavoriteViewModel;
import com.app.aplikasiku.moviescatalogue.viewmodel.ViewModelFactory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FavoriteMovieFragment extends Fragment {

    private MoviePagedListAdapter moviePagedListAdapter;
    RecyclerView movieList;
    ProgressBar pb;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_favorite, container, false);
    }

    @NonNull
    private FavoriteViewModel obtainViewModel(FragmentActivity activity) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(FavoriteViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        movieList = view.findViewById(R.id.rc_fav);
        pb = view.findViewById(R.id.progress_bar);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            FavoriteViewModel viewModel = obtainViewModel(getActivity());
            moviePagedListAdapter = new MoviePagedListAdapter(getActivity());
            EspressoIdlingResource.increment();
            viewModel.getAllFavMovie().observe(getActivity(), results -> {
                if (results != null) {
                    pb.setVisibility(View.GONE);
                    moviePagedListAdapter.submitList(results);
                    movieList.setLayoutManager(new GridLayoutManager(getActivity(),2));
                    movieList.setHasFixedSize(true);
                    movieList.setAdapter(moviePagedListAdapter);
                } else {
                    Toast.makeText(getActivity(), getString(R.string.data_notfound), Toast.LENGTH_SHORT).show();
                }
            });
            EspressoIdlingResource.decrement();
        }
    }
}
