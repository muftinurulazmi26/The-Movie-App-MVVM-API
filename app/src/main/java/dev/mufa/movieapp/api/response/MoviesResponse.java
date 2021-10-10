package dev.mufa.movieapp.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import dev.mufa.movieapp.model.MoviesModel;

public class MoviesResponse {
    @SerializedName("page")
    @Expose()
    private int page;

    public int getPage(){
        return page;
    }

    @SerializedName("results")
    @Expose()
    private List<MoviesModel> movies;

    public List<MoviesModel> getMovies(){
        return movies;
    }

    @Override
    public String toString() {
        return "MoviesResponse{" +
                "page=" + page +
                ", movies=" + movies +
                '}';
    }
}
