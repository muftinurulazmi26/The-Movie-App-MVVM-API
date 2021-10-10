package dev.mufa.movieapp.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import dev.mufa.movieapp.model.TrailerModel;

public class TrailerResponse {
    @SerializedName("id")
    @Expose()
    private int id;

    public int getId(){
        return id;
    }

    @SerializedName("results")
    @Expose()
    private List<TrailerModel> trailers;

    public List<TrailerModel> getTrailers(){
        return trailers;
    }

    @Override
    public String toString() {
        return "TrailerResponse{" +
                "id=" + id +
                ", trailers=" + trailers +
                '}';
    }
}
