package dev.mufa.movieapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.List;

import dev.mufa.movieapp.R;
import dev.mufa.movieapp.adapter.GenreAdapter;
import dev.mufa.movieapp.adapter.MovieAdapter;
import dev.mufa.movieapp.model.GenresModel;
import dev.mufa.movieapp.model.MoviesModel;
import dev.mufa.movieapp.utils.GridSpacingItemDecoration;
import dev.mufa.movieapp.viewmodel.GenreListViewModel;
import dev.mufa.movieapp.viewmodel.MovieListViewModel;

public class MainActivity extends AppCompatActivity implements MovieAdapter.onSelectData {
    private static final int GENRE_DEFAULT = 28;
    private GenreListViewModel genreListViewModel;
    private MovieListViewModel movieListViewModel;
    private RecyclerView rv_genre, rv_movies;
    private MovieAdapter movieAdapter;
    private GenreAdapter genreAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        genreListViewModel = new ViewModelProvider(this).get(GenreListViewModel.class);
        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        initView();
        genreListViewModel.getGenres().observe(this, new Observer<List<GenresModel>>() {
            @Override
            public void onChanged(List<GenresModel> genresModels) {
                getViewGenre(genresModels);
                genreAdapter.setOnItemClickedListener(new GenreAdapter.OnItemClickedListener() {
                    @Override
                    public void onItemClicked(View itemView, int position) {
                        getMovieList(genreAdapter.getGenres().get(position).getId());
                    }
                });
            }
        });
        getMovieList(GENRE_DEFAULT);
    }

    private void getMovieList(int genre_id) {
        movieListViewModel.getMovies(genre_id).observe(this, new Observer<List<MoviesModel>>() {
            @Override
            public void onChanged(List<MoviesModel> moviesModels) {
                getViewMovie(moviesModels);
            }
        });
    }

    private void initView() {
        rv_genre = findViewById(R.id.rv_genres);
        rv_movies = findViewById(R.id.rv_movies);
    }

    private void getViewMovie(List<MoviesModel> moviesModels) {
        movieAdapter = new MovieAdapter(this,moviesModels, this::onSelected);
        rv_movies.setHasFixedSize(true);
        rv_movies.setLayoutManager(new GridLayoutManager(this,2));
        rv_movies.setItemAnimator(new DefaultItemAnimator());
//        rv_movies.addItemDecoration(new GridSpacingItemDecoration(2,30,false));
        rv_movies.setAdapter(movieAdapter);
    }

    private void getViewGenre(List<GenresModel> genresModels) {
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        genreAdapter = new GenreAdapter(this,genresModels);
        rv_genre.setLayoutManager(layoutManager);
        rv_genre.setItemAnimator(new DefaultItemAnimator());
        rv_genre.setAdapter(genreAdapter);
    }

    @Override
    public void onSelected(MoviesModel moviesModel) {
        Intent intent = new Intent(MainActivity.this, DetailMovieActivity.class);
        intent.putExtra("detailMovie", moviesModel);
        startActivity(intent);
    }
}
