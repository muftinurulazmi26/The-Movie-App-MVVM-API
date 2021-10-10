package dev.mufa.movieapp.api.request;

import dev.mufa.movieapp.utils.Credential;
import dev.mufa.movieapp.utils.GenresApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Services {
    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
            .baseUrl(Credential.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static GenresApi genresApi = retrofit.create(GenresApi.class);

    public static GenresApi getGenresApi(){
        return genresApi;
    }
}
