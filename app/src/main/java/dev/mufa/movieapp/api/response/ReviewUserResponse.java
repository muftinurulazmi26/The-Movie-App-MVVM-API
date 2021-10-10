package dev.mufa.movieapp.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import dev.mufa.movieapp.model.ReviewUserModel;

public class ReviewUserResponse {
    @SerializedName("id")
    @Expose()
    private int id;

    public int getId(){
        return id;
    }

    @SerializedName("page")
    @Expose()
    private int page;

    public int getPage(){ return page;}

    @SerializedName("results")
    @Expose()
    private List<ReviewUserModel> reviewUsers;

    public List<ReviewUserModel> getReviewUser(){
        return reviewUsers;
    }

    @Override
    public String toString() {
        return "ReviewUserResponse{" +
                "id=" + id +
                ", page=" + page +
                ", reviewUsers=" + reviewUsers +
                '}';
    }
}
