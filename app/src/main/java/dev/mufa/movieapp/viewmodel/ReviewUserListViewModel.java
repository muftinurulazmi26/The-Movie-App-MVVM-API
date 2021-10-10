package dev.mufa.movieapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import dev.mufa.movieapp.model.ReviewUserModel;
import dev.mufa.movieapp.model.TrailerModel;
import dev.mufa.movieapp.repository.ReviewUserRepo;
import dev.mufa.movieapp.repository.TrailerRepo;

public class ReviewUserListViewModel extends ViewModel {
    private ReviewUserRepo reviewUserRepo; // inisiasi movie repository

    public ReviewUserListViewModel() {
        reviewUserRepo = ReviewUserRepo.getInstance(); //get instance movie repository
    }

    public LiveData<List<ReviewUserModel>> getReviewUser(int movie_id){
        return reviewUserRepo.getMutableReviewUserData(movie_id); //return list of movie from repository
    }
}
