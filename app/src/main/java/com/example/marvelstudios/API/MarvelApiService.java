package com.example.marvelstudios.API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MarvelApiService {
    @GET("characters")
    Call<MarvelResponse> getCharacters(
            @Query("apikey") String apiKey,
            @Query("ts") String timestamp,
            @Query("hash") String hash,
            @Query("nameStartsWith") String nameStartsWith
    );

    class MarvelResponse {
    }
}
