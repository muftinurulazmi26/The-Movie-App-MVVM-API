package dev.mufa.movieapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import dev.mufa.movieapp.model.MoviesModel;
import dev.mufa.movieapp.model.TrailerModel;
import dev.mufa.movieapp.repository.MovieRepo;
import dev.mufa.movieapp.repository.TrailerRepo;

public class TrailerListViewModel extends ViewModel {
    private TrailerRepo trailerRepo; // inisiasi movie repository

    public TrailerListViewModel() {
        trailerRepo = TrailerRepo.getInstance(); //get instance movie repository
    }

    public LiveData<List<TrailerModel>> getTrailers(int movie_id){
        return trailerRepo.getMutableTrailerData(movie_id); //return list of movie from repository
    }
}
