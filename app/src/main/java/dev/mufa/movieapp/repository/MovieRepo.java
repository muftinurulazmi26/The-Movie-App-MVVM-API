package dev.mufa.movieapp.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import dev.mufa.movieapp.model.MoviesModel;
import dev.mufa.movieapp.api.request.Services;
import dev.mufa.movieapp.api.response.MoviesResponse;
import dev.mufa.movieapp.utils.Credential;
import dev.mufa.movieapp.utils.GenresApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepo {
    private String TAG = "GenreRepo";
    private static MovieRepo instance;
    private MutableLiveData<List<MoviesModel>> mMovies = new MutableLiveData<>(); //live data
    public static MovieRepo getInstance(){
        if (instance == null){
            instance = new MovieRepo();
        }
        return instance;
    }

    public MutableLiveData<List<MoviesModel>> getMutableMovieData(int genre){
        GenresApi genresApi = Services.getGenresApi();
        Call<MoviesResponse> moviesResponseCall = genresApi
                .listOfMovies(Credential.API_KEY, genre);
        moviesResponseCall.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                if (response.isSuccessful()){
                    Log.v("Tag", "movie title "+response.body().toString());
                    List<MoviesModel> movies = new ArrayList<>(response.body().getMovies());
                    mMovies.setValue(movies);
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getCause());
                Log.e(TAG, "onFailure: " + t.getLocalizedMessage());
                Log.e(TAG, "onFailure: " + t.getMessage());
                Log.e(TAG, "onFailure: " + t.toString());
            }
        });
        return mMovies;
    }
}
