package dev.mufa.movieapp.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import dev.mufa.movieapp.model.TrailerModel;
import dev.mufa.movieapp.api.request.Services;
import dev.mufa.movieapp.api.response.TrailerResponse;
import dev.mufa.movieapp.utils.Credential;
import dev.mufa.movieapp.utils.GenresApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrailerRepo {
    private String TAG = "GenreRepo";
    private static TrailerRepo instance;
    private MutableLiveData<List<TrailerModel>> mTrailer = new MutableLiveData<>(); //live data
    public static TrailerRepo getInstance(){
        if (instance == null){
            instance = new TrailerRepo();
        }
        return instance;
    }

    public MutableLiveData<List<TrailerModel>> getMutableTrailerData(int movie_id){
        GenresApi genresApi = Services.getGenresApi();
        Call<TrailerResponse> trailerResponseCall = genresApi
                .listOfTrailer(movie_id, Credential.API_KEY);
        trailerResponseCall.enqueue(new Callback<TrailerResponse>() {
            @Override
            public void onResponse(Call<TrailerResponse> call, Response<TrailerResponse> response) {
                if (response.isSuccessful()){
                    Log.v("Tag", "movie trailer "+response.body().toString());
                    List<TrailerModel> trailers = new ArrayList<>(response.body().getTrailers());
                    mTrailer.setValue(trailers);
                }
            }

            @Override
            public void onFailure(Call<TrailerResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getCause());
                Log.e(TAG, "onFailure: " + t.getLocalizedMessage());
                Log.e(TAG, "onFailure: " + t.getMessage());
                Log.e(TAG, "onFailure: " + t.toString());
            }
        });
        return mTrailer;
    }
}
