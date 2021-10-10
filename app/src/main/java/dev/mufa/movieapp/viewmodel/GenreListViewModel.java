package dev.mufa.movieapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import dev.mufa.movieapp.model.GenresModel;
import dev.mufa.movieapp.repository.GenreRepo;

public class GenreListViewModel extends ViewModel {
//    private MutableLiveData<List<GenresModel>> mGenres = new MutableLiveData<>(); //live data
    private GenreRepo genreRepo; // inisiasi genre repository

    public GenreListViewModel() {
        genreRepo = GenreRepo.getInstance(); //get instance genre repository
    }

    public LiveData<List<GenresModel>> getGenres(){
        return genreRepo.getMutableGenreData(); //return list of genre from repository
    }
}
