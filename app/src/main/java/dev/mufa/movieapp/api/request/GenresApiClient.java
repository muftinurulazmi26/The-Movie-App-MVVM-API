package dev.mufa.movieapp.api.request;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import dev.mufa.movieapp.utils.AppExecutors;
import dev.mufa.movieapp.model.GenresModel;
import dev.mufa.movieapp.api.response.GenresResponse;
import dev.mufa.movieapp.utils.Credential;
import dev.mufa.movieapp.utils.GenresApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenresApiClient {
    private String TAG = "GenresApiClient";
    private MutableLiveData<List<GenresModel>> mGenres; //live data
    private static GenresApiClient instance;

    public static GenresApiClient getInstance(){
        if (instance == null){
            instance = new GenresApiClient();
        }
        return instance;
    }
    private GenresApiClient(){
        mGenres = new MutableLiveData<>();
    }

    public void getGenresApi(){
        final Future myHandler = AppExecutors.getInstance().networkIO().submit(new Runnable() {
            @Override
            public void run() {
                //retrieve data from api

            }
        });
        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //cancel retrofit call
                myHandler.cancel(true);
            }
        },5000, TimeUnit.MICROSECONDS);
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
