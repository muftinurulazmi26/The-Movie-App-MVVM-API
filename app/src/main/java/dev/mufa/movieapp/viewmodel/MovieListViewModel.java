package dev.mufa.movieapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import dev.mufa.movieapp.model.GenresModel;
import dev.mufa.movieapp.model.MoviesModel;
import dev.mufa.movieapp.repository.GenreRepo;
import dev.mufa.movieapp.repository.MovieRepo;

public class MovieListViewModel extends ViewModel {
    private MovieRepo movieRepo; // inisiasi movie repository

    public MovieListViewModel() {
        movieRepo = MovieRepo.getInstance(); //get instance movie repository
    }

    public LiveData<List<MoviesModel>> getMovies(int genre){
        return movieRepo.getMutableMovieData(genre); //return list of movie from repository
    }
}
