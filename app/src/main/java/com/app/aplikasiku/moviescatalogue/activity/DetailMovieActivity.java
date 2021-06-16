package com.app.aplikasiku.moviescatalogue.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.aplikasiku.moviescatalogue.R;
import com.app.aplikasiku.moviescatalogue.data.entity.MovieItem;
import com.app.aplikasiku.moviescatalogue.utils.EspressoIdlingResource;
import com.app.aplikasiku.moviescatalogue.viewmodel.DetailMovieViewModel;
import com.app.aplikasiku.moviescatalogue.viewmodel.ViewModelFactory;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

public class DetailMovieActivity extends AppCompatActivity {
    private ImageView ivposter, ivbackground;
    private TextView txttitle, txtgenre, txtpopular, txttanggal, txtruntime, txtdirector, txtbahasa, txtdeskripsi, txtstatus, txtrevenue, txtbudget;

    public static final String EXTRA_MOV = "extra_mov";
    private DetailMovieViewModel viewModel;
    private MovieItem item;
    private FloatingActionButton btnfav;

    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.title_detail_movie));
        }
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));

        ivbackground = findViewById(R.id.image_backdrop);
        ivposter = findViewById(R.id.image_poster);
        txtpopular = findViewById(R.id.popular);
        txttitle = findViewById(R.id.txt_title);
        txtgenre = findViewById(R.id.txt_genre);
        txttanggal = findViewById(R.id.txt_tanggal);
        txtruntime = findViewById(R.id.txt_runtime);
        txtrevenue = findViewById(R.id.txt_revenue);
        txtstatus = findViewById(R.id.txt_status);
        txtbudget = findViewById(R.id.txt_budget);
        txtdirector = findViewById(R.id.txt_director);
        txtbahasa = findViewById(R.id.txt_bahasa);
        txtdeskripsi = findViewById(R.id.txt_deskripsi);
        btnfav = findViewById(R.id.btn_fav);
        item = getIntent().getParcelableExtra(EXTRA_MOV);
        ViewModelFactory factory = ViewModelFactory.getInstance(this.getApplication());
        viewModel = ViewModelProviders.of(this, factory).get(DetailMovieViewModel.class);
        viewModel.setMovieId(Objects.requireNonNull(item).getId());

        viewModel.getMovieByIdRoom().observe(this, results ->{
            if (results == null) {
                btnfav.setImageResource(R.drawable.ic_favorite);
                btnfav.setOnClickListener(v -> {
                    viewModel.insertMovie(item);
                    Toast.makeText(this, getString(R.string.save_favorite), Toast.LENGTH_SHORT).show();
                });
            } else {
                btnfav.setImageResource(R.drawable.ic_terfavorite);
                btnfav.setOnClickListener(v -> {
                    viewModel.deleteMovie(item);
                    Toast.makeText(this, getString(R.string.remove_favorite), Toast.LENGTH_SHORT).show();
                });
            }
        });
        if (item.getId() != 0) {
            EspressoIdlingResource.increment();
            viewModel.getDetailMovies().observe(this, results -> {
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
            txtruntime.setText("" + results.getRuntime() + " " + getString(R.string.runtime));
            txtstatus.setText(results.getStatus());
            txtbudget.setText("$ " + NumberFormat.getIntegerInstance().format(results.getBudget()));
            txtrevenue.setText("$ " + NumberFormat.getIntegerInstance().format(results.getRevenue()));
            EspressoIdlingResource.decrement();
            });
        }
    }
}
