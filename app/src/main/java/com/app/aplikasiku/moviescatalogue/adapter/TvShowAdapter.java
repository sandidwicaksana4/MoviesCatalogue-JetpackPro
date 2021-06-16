package com.app.aplikasiku.moviescatalogue.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.aplikasiku.moviescatalogue.R;
import com.app.aplikasiku.moviescatalogue.activity.DetailTvShowActivity;
import com.app.aplikasiku.moviescatalogue.data.entity.MovieItem;
import com.app.aplikasiku.moviescatalogue.data.entity.TvShowItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import jp.wasabeef.picasso.transformations.BlurTransformation;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.TvHolder> {

    private List<TvShowItem> list;
    private Context context;

    public TvShowAdapter(Context context, List<TvShowItem> list) { this.list = list; }

    @Override
    public TvHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new TvHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TvHolder holder, final int i) {
        holder.onBind(list.get(i));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TvHolder extends RecyclerView.ViewHolder {

        private TextView judul, tanggal, popular;
        private ImageView gambar, background;

        public TvHolder(View item) {
            super(item);

            context = item.getContext();

            gambar = item.findViewById(R.id.image_poster);
            judul = item.findViewById(R.id.title_movie);
            background = item.findViewById(R.id.background);
            tanggal = item.findViewById(R.id.date_release);
            popular = item.findViewById(R.id.popular);
            item.setOnClickListener(view -> {
                Intent masuk;
                masuk = new Intent(context, DetailTvShowActivity.class);
                masuk.putExtra(DetailTvShowActivity.EXTRA_TV, list.get(getAdapterPosition()));
                context.startActivity(masuk);
            });
        }

        void onBind(TvShowItem item) {
            if (item.getPoster() != null && !item.getPoster().isEmpty()) {
                Picasso.get().load("https://image.tmdb.org/t/p/w342/" +item.getPoster()).transform(new CropCircleTransformation()).into(gambar);
            }

            if (item.getBackground() != null && !item.getBackground().isEmpty()) {
                Picasso.get().load("https://image.tmdb.org/t/p/w342/" +item.getPoster()).transform(new BlurTransformation(itemView.getContext(), 20)).into(background);
            }

            String title = checkTextIfNull(item.getTitle());
            if (title.length() > 30) {
                judul.setText(String.format("%s...", title.substring(0, 29)));
            } else {
                judul.setText(checkTextIfNull(item.getTitle()));
            }

            tanggal.setText(checkTextIfNull(item.getReleaseDate()));
            popular.setText(checkTextIfNull("" + item.getPopular()));
        }

        String checkTextIfNull(String text) {
            if (text != null && !text.isEmpty()) {
                return text;
            } else {
                return "-";
            }
        }

    }
}
