package com.app.aplikasiku.moviescatalogue.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.aplikasiku.moviescatalogue.R;
import com.app.aplikasiku.moviescatalogue.data.entity.DetailMovieItem;
import com.app.aplikasiku.moviescatalogue.data.entity.DetailTvShowItem;
import com.app.aplikasiku.moviescatalogue.data.entity.TvShowItem;
import com.app.aplikasiku.moviescatalogue.utils.EspressoIdlingResource;
import com.app.aplikasiku.moviescatalogue.viewmodel.DetailMovieViewModel;
import com.app.aplikasiku.moviescatalogue.viewmodel.DetailTvShowViewModel;
import com.app.aplikasiku.moviescatalogue.viewmodel.ViewModelFactory;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class DetailTvShowActivity extends AppCompatActivity {

    private ImageView ivposter, ivbackground;
    private TextView episode, season, txttitle, txtgenre, txtpopular, txttanggal, txtruntime, txtdirector, txtbahasa, txtdeskripsi, txtstatus, txtseason, txtepisode;

    public static final String EXTRA_TV = "extra_tv";
    private DetailTvShowViewModel viewModel;
    private TvShowItem item;
    private FloatingActionButton btnfav;

    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv_show);

        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.title_detail_tv));
        }
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));

        ivbackground = findViewById(R.id.image_backdrop);
        ivposter = findViewById(R.id.image_poster);
        txtpopular = findViewById(R.id.popular);
        txttitle = findViewById(R.id.txt_title);
        txtgenre = findViewById(R.id.txt_genre);
        txttanggal = findViewById(R.id.txt_tanggal);
        txtruntime = findViewById(R.id.txt_runtime);
        txtstatus = findViewById(R.id.txt_status);
        episode = findViewById(R.id.budget);
        season = findViewById(R.id.revenue);
        txtepisode = findViewById(R.id.txt_budget);
        txtseason = findViewById(R.id.txt_revenue);
        txtdirector = findViewById(R.id.txt_director);
        txtbahasa = findViewById(R.id.txt_bahasa);
        txtdeskripsi = findViewById(R.id.txt_deskripsi);
        btnfav = findViewById(R.id.btn_fav);

        episode.setText(R.string.text_episode);
        season.setText(R.string.text_season);

        item = getIntent().getParcelableExtra(EXTRA_TV);
        ViewModelFactory factory = ViewModelFactory.getInstance(this.getApplication());
        viewModel = ViewModelProviders.of(this, factory).get(DetailTvShowViewModel.class);
        viewModel.setTvShowId(Objects.requireNonNull(item).getId());

        viewModel.getTvShowByIdRoom().observe(this, results ->{
            if (results == null) {
                btnfav.setImageResource(R.drawable.ic_favorite);
                btnfav.setOnClickListener(v -> {
                    viewModel.insertTv(item);
                    Toast.makeText(this, getString(R.string.save_favorite), Toast.LENGTH_SHORT).show();
                });
            } else {
                btnfav.setImageResource(R.drawable.ic_terfavorite);
                btnfav.setOnClickListener(v -> {
                    viewModel.deleteTv(item);
                    Toast.makeText(this, getString(R.string.remove_favorite), Toast.LENGTH_SHORT).show();
                });
            }
        });

        if (item.getId() != 0){
            EspressoIdlingResource.increment();
            viewModel.getDetailTvShow().observe(this, results ->{
            Picasso.get().load(results.getBackground()).into(ivbackground);
            txttitle.setText(results.getTitle());
            Picasso.get().load(results.getPoster()).into(ivposter);
            txtpopular.setText("" + results.getPopular());
            txttanggal.setText(results.getReleaseDate());
            txtbahasa.setText(results.getLanguage());
            txtdeskripsi.setText(results.getOverview());
            int size = 0;

            String genres = "";
            size = results.getGenre().size();
            for (int i = 0; i < size; i++) {
                genres += results.getGenre().get(i).getName() + (i + 1 < size ? "\n" : "");
            }
            txtgenre.setText(genres);
            String companies = "";
            size = results.getProductionCompanies().size();
            for (int i = 0; i < size; i++) {
                companies += results.getProductionCompanies().get(i).getName() + (i + 1 < size ? "\n" : "");
            }
            txtdirector.setText(companies);

            String runtime = "";
            size = results.getRuntime().size();
            for (int i = 0; i < size; i++) {
                runtime += results.getRuntime().get(i) + (i + 1 < size ? "" : "");
            }
            txtruntime.setText(runtime + " " + getString(R.string.runtime));
            txtstatus.setText(results.getStatus());
            txtepisode.setText("" + results.getNumberepisode());
            txtseason.setText("" + results.getNumberseason());
            EspressoIdlingResource.decrement();
            });
        }

    }
}
