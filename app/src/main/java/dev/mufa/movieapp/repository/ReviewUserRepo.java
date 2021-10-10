package dev.mufa.movieapp.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import dev.mufa.movieapp.model.ReviewUserModel;
import dev.mufa.movieapp.api.request.Services;
import dev.mufa.movieapp.api.response.ReviewUserResponse;
import dev.mufa.movieapp.utils.Credential;
import dev.mufa.movieapp.utils.GenresApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewUserRepo {
    private String TAG = "GenreRepo";
    private static ReviewUserRepo instance;
    private MutableLiveData<List<ReviewUserModel>> mReviewUser = new MutableLiveData<>(); //live data
    public static ReviewUserRepo getInstance(){
        if (instance == null){
            instance = new ReviewUserRepo();
        }
        return instance;
    }

    public MutableLiveData<List<ReviewUserModel>> getMutableReviewUserData(int movie_id){
        GenresApi genresApi = Services.getGenresApi();
        Call<ReviewUserResponse> reviewUserModelCall = genresApi
                .listOfReviewUser(movie_id, Credential.API_KEY,1);

        reviewUserModelCall.enqueue(new Callback<ReviewUserResponse>() {
            @Override
            public void onResponse(Call<ReviewUserResponse> call, Response<ReviewUserResponse> response) {
                if (response.isSuccessful()){
                    Log.v("Tag", "review user "+response.body().toString());
                    List<ReviewUserModel> trailers = new ArrayList<>(response.body().getReviewUser());
                    mReviewUser.setValue(trailers);
                }
            }

            @Override
            public void onFailure(Call<ReviewUserResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getCause());
                Log.e(TAG, "onFailure: " + t.getLocalizedMessage());
                Log.e(TAG, "onFailure: " + t.getMessage());
                Log.e(TAG, "onFailure: " + t.toString());
            }
        });
        return mReviewUser;
    }
}
