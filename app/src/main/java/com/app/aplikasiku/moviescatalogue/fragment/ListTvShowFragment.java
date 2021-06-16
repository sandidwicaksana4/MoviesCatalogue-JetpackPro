package com.app.aplikasiku.moviescatalogue.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.app.aplikasiku.moviescatalogue.R;
import com.app.aplikasiku.moviescatalogue.adapter.TvShowAdapter;
import com.app.aplikasiku.moviescatalogue.data.entity.TvShowItem;
import com.app.aplikasiku.moviescatalogue.utils.EspressoIdlingResource;
import com.app.aplikasiku.moviescatalogue.viewmodel.MovieViewModel;
import com.app.aplikasiku.moviescatalogue.viewmodel.TvShowViewModel;
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
public class ListTvShowFragment extends Fragment {

    private TvShowAdapter tvAdapter;
    private TvShowViewModel viewModel;

    SwipeRefreshLayout swipe;
    RecyclerView tvList;
    ProgressBar pb;

    public ListTvShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_tv_show, container, false);
    }


    @NonNull
    private TvShowViewModel obtainViewModel(FragmentActivity activity) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(TvShowViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        tvList = view.findViewById(R.id.rc_tv);
        swipe = view.findViewById(R.id.swipe_refresh);
        pb = view.findViewById(R.id.progress_bar);
        if (getActivity()!=null) {
            pb.setVisibility(View.VISIBLE);
            viewModel = obtainViewModel(getActivity());
            EspressoIdlingResource.increment();
            viewModel.getTvShowData().observe(this, tvShowItems ->{
                if (tvShowItems != null) {
                    pb.setVisibility(View.GONE);
                    tvAdapter = new TvShowAdapter(getActivity(),tvShowItems);
                    tvList.setLayoutManager(new LinearLayoutManager(getContext()));
                    tvList.setHasFixedSize(true);
                    tvList.setAdapter(tvAdapter);
                    tvAdapter.notifyDataSetChanged();
                EspressoIdlingResource.decrement();
                }
            });
        }
        swipe.setOnRefreshListener(() -> {
            loadView();
            loadData();
            swipe.setRefreshing(false);
        });
    }

    private void loadView(){
        tvList.setLayoutManager(new LinearLayoutManager(getContext()));
        tvList.setHasFixedSize(true);
        tvList.setAdapter(tvAdapter);
    }

    private void loadData() {
        viewModel.getTvShowData().observe(this, tvShowItems ->{
        if (tvShowItems != null) {
            pb.setVisibility(View.GONE);
            tvAdapter = new TvShowAdapter(getActivity(),tvShowItems);
            tvAdapter.notifyDataSetChanged();
        }
        });
    }
}
