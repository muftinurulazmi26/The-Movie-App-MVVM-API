package dev.mufa.movieapp.utils;

import dev.mufa.movieapp.api.response.GenresResponse;
import dev.mufa.movieapp.api.response.MoviesResponse;
import dev.mufa.movieapp.api.response.ReviewUserResponse;
import dev.mufa.movieapp.api.response.TrailerResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GenresApi {

    //https://api.themoviedb.org/3/genre/movie/list?api_key=<<api_key>>
    @GET("/3/genre/movie/list")
    Call<GenresResponse> listOfGenres(
            @Query("api_key") String key
    );

    //https://api.themoviedb.org/3/discover/movie?api_key=<<api_key>>&with_genres={genre_id}
    @GET("/3/discover/movie")
    Call<MoviesResponse> listOfMovies(
            @Query("api_key") String key,
            @Query("with_genres") int genre
    );

    //https://api.themoviedb.org/3/movie/{movie_id}/videos?api_key=<<api_key>>
    @GET("/3/movie/{movie_id}/videos")
    Call<TrailerResponse> listOfTrailer(
            @Path("movie_id") int movie_id,
            @Query("api_key") String key
    );

    //https://api.themoviedb.org/3/movie/{movie_id}/reviews?api_key=<<api_key>>&page=1
    @GET("/3/movie/{movie_id}/reviews")
    Call<ReviewUserResponse> listOfReviewUser(
            @Path("movie_id") int movie_id,
            @Query("api_key") String api_key,
            @Query("page") int page
    );
}
