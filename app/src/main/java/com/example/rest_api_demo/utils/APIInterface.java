package com.example.rest_api_demo.utils;

import com.example.rest_api_demo.models.MovieList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("list_movies.json")
    Call<MovieList> getListByYear(@Query("sort_by") String year, @Query("page") String page);

    //movie_details.json
}
