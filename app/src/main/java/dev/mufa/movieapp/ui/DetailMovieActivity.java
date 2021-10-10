package dev.mufa.movieapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import dev.mufa.movieapp.R;
import dev.mufa.movieapp.adapter.ReviewUserAdapter;
import dev.mufa.movieapp.adapter.TrailerAdapter;
import dev.mufa.movieapp.model.MoviesModel;
import dev.mufa.movieapp.model.ReviewUserModel;
import dev.mufa.movieapp.model.TrailerModel;
import dev.mufa.movieapp.utils.Credential;
import dev.mufa.movieapp.viewmodel.ReviewUserListViewModel;
import dev.mufa.movieapp.viewmodel.TrailerListViewModel;

public class DetailMovieActivity extends AppCompatActivity {
    private TextView tvTitle, tvName, tvRating, tvRelease, tvPopularity, tvOverview;
    private ImageView imgCover, imgPhoto;
    private View line, line2;
    private FloatingActionButton fabShare;
    private MaterialFavoriteButton imgFavorite;
    private Toolbar toolbar;
    private MoviesModel moviesModel;
    private double Rating;
    private RatingBar ratingBar;
    private RecyclerView rvTrailer, rvReviewUser;
    private TrailerAdapter trailerAdapter;
    private TrailerListViewModel trailerListViewModel;
    private ReviewUserAdapter reviewUserAdapter;
    private ReviewUserListViewModel reviewUserListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        initView();

        moviesModel = (MoviesModel) getIntent().getExtras().getParcelable("detailMovie");
        if (moviesModel != null){
            Log.v("Tag",moviesModel.getTitle());
            tvTitle.setText(moviesModel.getTitle());
            tvName.setText(moviesModel.getTitle());
            Rating = moviesModel.getvote_average();
            tvRating.setText(Rating + "/10");
            tvRelease.setText(moviesModel.getrelease_date());
            tvPopularity.setText(String.valueOf(moviesModel.getPopularity()));
            tvOverview.setText(moviesModel.getOverview());
            tvTitle.setSelected(true);
            tvName.setSelected(true);

            float newValue = (float)Rating;
            ratingBar.setNumStars(5);
            ratingBar.setStepSize((float) 0.5);
            ratingBar.setRating(newValue / 2);

            Glide.with(this)
                    .load(Credential.BASE_IMAGE + moviesModel.getbackdrop_path())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgCover);

            Glide.with(this)
                    .load(Credential.BASE_IMAGE + moviesModel.getposter_path())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgPhoto);
        }

        reviewUserListViewModel.getReviewUser(moviesModel.getId()).observe(this, new Observer<List<ReviewUserModel>>() {
            @Override
            public void onChanged(List<ReviewUserModel> reviewUserModels) {
                reviewUserAdapter = new ReviewUserAdapter(DetailMovieActivity.this, reviewUserModels);
                LinearLayoutManager layoutManager
                        = new LinearLayoutManager(DetailMovieActivity.this, LinearLayoutManager.HORIZONTAL, false);
                rvReviewUser.setHasFixedSize(true);
                rvReviewUser.setLayoutManager(layoutManager);
                rvReviewUser.setItemAnimator(new DefaultItemAnimator());
                rvReviewUser.setAdapter(reviewUserAdapter);
                if (reviewUserModels.size() == 0)
                    line.setVisibility(View.VISIBLE);
                else
                    line.setVisibility(View.GONE);
            }
        });

        trailerListViewModel.getTrailers(moviesModel.getId()).observe(this, new Observer<List<TrailerModel>>() {
            @Override
            public void onChanged(List<TrailerModel> trailerModels) {
                trailerAdapter = new TrailerAdapter(DetailMovieActivity.this, trailerModels);
                rvTrailer.setHasFixedSize(true);
                rvTrailer.setLayoutManager(new LinearLayoutManager(DetailMovieActivity.this));
                rvTrailer.setItemAnimator(new DefaultItemAnimator());
                rvTrailer.setAdapter(trailerAdapter);
                if (trailerModels.size() == 0)
                    line2.setVisibility(View.VISIBLE);
                else
                    line2.setVisibility(View.GONE);
            }
        });
    }

    private void initView() {
        trailerListViewModel = new ViewModelProvider(this).get(TrailerListViewModel.class);
        reviewUserListViewModel = new ViewModelProvider(this).get(ReviewUserListViewModel.class);

        ratingBar = findViewById(R.id.ratingBar);
        imgCover = findViewById(R.id.imgCover);
        imgPhoto = findViewById(R.id.imgPhoto);
        line = findViewById(R.id.line);
        line2 = findViewById(R.id.line2);
        imgFavorite = findViewById(R.id.imgFavorite);
        tvTitle = findViewById(R.id.tvTitle);
        tvName = findViewById(R.id.tvName);
        tvRating = findViewById(R.id.tvRating);
        tvRelease = findViewById(R.id.tvRelease);
        tvPopularity = findViewById(R.id.tvPopularity);
        tvOverview = findViewById(R.id.tvOverview);
        rvTrailer = findViewById(R.id.rvTrailer);
        rvReviewUser = findViewById(R.id.rvReviewUser);
        fabShare = findViewById(R.id.fabShare);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fabShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                String subject = moviesModel.getTitle();
                String description = moviesModel.getOverview();
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
                shareIntent.putExtra(Intent.EXTRA_TEXT, subject + "\n\n" + description + "\n\n" + Credential.URL_FILM + "" +moviesModel.getId());
                startActivity(Intent.createChooser(shareIntent, "Bagikan dengan :"));
            }
        });
    }

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams winParams = window.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        window.setAttributes(winParams);
    }

}
