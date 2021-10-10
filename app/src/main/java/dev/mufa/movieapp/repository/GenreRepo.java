package dev.mufa.movieapp.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import dev.mufa.movieapp.model.GenresModel;
import dev.mufa.movieapp.api.request.Services;
import dev.mufa.movieapp.api.response.GenresResponse;
import dev.mufa.movieapp.utils.Credential;
import dev.mufa.movieapp.utils.GenresApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenreRepo {
    private String TAG = "GenreRepo";
    private static GenreRepo instance;
    private MutableLiveData<List<GenresModel>> mGenres = new MutableLiveData<>(); //live data
    public static GenreRepo getInstance(){
        if (instance == null){
            instance = new GenreRepo();
        }
        return instance;
    }

    public MutableLiveData<List<GenresModel>> getMutableGenreData(){
        GenresApi genresApi = Services.getGenresApi();
        Call<GenresResponse> genresResponseCall = genresApi
                .listOfGenres(Credential.API_KEY);
        genresResponseCall.enqueue(new Callback<GenresResponse>() {
            @Override
            public void onResponse(Call<GenresResponse> call, Response<GenresResponse> response) {
                if (response.isSuccessful()){
                    List<GenresModel> genres = new ArrayList<>(response.body().getGenres());
                    mGenres.setValue(genres);
                }
            }

            @Override
            public void onFailure(Call<GenresResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getCause());
                Log.e(TAG, "onFailure: " + t.getLocalizedMessage());
                Log.e(TAG, "onFailure: " + t.getMessage());
                Log.e(TAG, "onFailure: " + t.toString());
            }
        });
        return mGenres;
    }
}
