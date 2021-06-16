package com.app.aplikasiku.moviescatalogue.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.aplikasiku.moviescatalogue.R;
import com.app.aplikasiku.moviescatalogue.activity.DetailMovieActivity;
import com.app.aplikasiku.moviescatalogue.data.entity.MovieItem;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class MoviePagedListAdapter extends PagedListAdapter<MovieItem, MoviePagedListAdapter.MovieViewHolder> {
    private static DiffUtil.ItemCallback<MovieItem> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<MovieItem>() {
                // Concert details may have changed if reloaded from the database,
                // but ID is fixed.
                @Override
                public boolean areItemsTheSame(MovieItem oldItem, MovieItem newItem) {
                    return oldItem.getId().equals(newItem.getId());
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(MovieItem oldItem, @NonNull MovieItem newItem) {
                    return oldItem.equals(newItem);
                }
            };
    private final Activity activity;

    public MoviePagedListAdapter(Activity activity) {
        super(DIFF_CALLBACK);
        this.activity = activity;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_favorite, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder holder, int position) {
        final MovieItem movieResults = getItem(position);
        if (movieResults != null) {
            holder.judul.setText(movieResults.getTitle());
            Picasso.get().load("https://image.tmdb.org/t/p/w342/" +movieResults.getPoster()).into(holder.gambar);
            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(activity, DetailMovieActivity.class);
                intent.putExtra(DetailMovieActivity.EXTRA_MOV, movieResults);
                activity.startActivity(intent);
            });
        }
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        private TextView judul;
        private ImageView gambar;

        MovieViewHolder(View item) {
            super(item);

            gambar = item.findViewById(R.id.img_poster);
            judul = item.findViewById(R.id.txt_title);
        }
    }
}
