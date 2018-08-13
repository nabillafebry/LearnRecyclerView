package com.feby.asyst.learnrecyclerview.retrofit;

import com.feby.asyst.learnrecyclerview.model.Album;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServices {

    @GET("music_albums")
    Call<ArrayList<Album>> getAlbums(); //@Query ("id") String id
}
