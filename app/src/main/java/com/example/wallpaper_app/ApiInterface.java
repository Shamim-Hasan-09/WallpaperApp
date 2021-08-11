package com.example.wallpaper_app;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

import static com.example.wallpaper_app.ApiUtilities.API;

public interface ApiInterface {

    String BASE_URL="https://api.pexels.com/v1/";

    @Headers("Authorization: "+API)
    @GET("curated")
    Call<SearchModel> getImage(
            @Query("page") int page,
            @Query("per_page") int per_page
    );

    @Headers("Authorization: "+API)
    @GET("search")
    Call<SearchModel> getSearchImage(
            @Query("query") String Query,
            @Query("page") int page,
            @Query("per_page") int per_page
    );



}
