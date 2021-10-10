package dev.mufa.movieapp.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import dev.mufa.movieapp.model.GenresModel;

public class GenresResponse {
    @SerializedName("genres")
    @Expose()
    private List<GenresModel> genres;

    public List<GenresModel> getGenres(){
        return genres;
    }

    @Override
    public String toString() {
        return "GenresResponse{" +
                "genres=" + genres +
                '}';
    }
}
