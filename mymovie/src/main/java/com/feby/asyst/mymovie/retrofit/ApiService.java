package com.feby.asyst.mymovie.retrofit;

import com.feby.asyst.mymovie.retrofit.response.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("discover/movie")
    Call<MovieResponse> getMovie(@Query("api_key") String api_key, @Query("page") int page, @Query("year") String year, @Query("sort_by") String sort_by);

    @GET("search/movie")
    Call<MovieResponse> searchMovie(@Query("api_key") String api_key, @Query("page") int page, @Query("query") String query);
}
