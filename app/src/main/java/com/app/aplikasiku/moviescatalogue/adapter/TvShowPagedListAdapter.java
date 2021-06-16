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
import com.app.aplikasiku.moviescatalogue.activity.DetailTvShowActivity;
import com.app.aplikasiku.moviescatalogue.data.entity.TvShowItem;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class TvShowPagedListAdapter extends PagedListAdapter<TvShowItem, TvShowPagedListAdapter.TvViewHolder> {
    private static DiffUtil.ItemCallback<TvShowItem> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<TvShowItem>() {
                @Override
                public boolean areItemsTheSame(TvShowItem oldItem, TvShowItem newItem) {
                    return oldItem.getId().equals(newItem.getId());
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(TvShowItem oldItem, @NonNull TvShowItem newItem) {
                    return oldItem.equals(newItem);
                }
            };
    private final Activity activity;

    public TvShowPagedListAdapter(Activity activity) {
        super(DIFF_CALLBACK);
        this.activity = activity;
    }

    @NonNull
    @Override
    public TvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_favorite, parent, false);
        return new TvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TvViewHolder holder, int position) {
        final TvShowItem tvResults = getItem(position);
        if (tvResults != null) {
            holder.judul.setText(tvResults.getTitle());
            Picasso.get().load("https://image.tmdb.org/t/p/w342/" + tvResults.getPoster()).into(holder.gambar);
            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(activity, DetailTvShowActivity.class);
                intent.putExtra(DetailTvShowActivity.EXTRA_TV, tvResults);
                activity.startActivity(intent);
            });
        }
    }

    class TvViewHolder extends RecyclerView.ViewHolder {
        private TextView judul;
        private ImageView gambar;

        TvViewHolder(View item) {
            super(item);

            gambar = item.findViewById(R.id.img_poster);
            judul = item.findViewById(R.id.txt_title);
        }
    }
}

