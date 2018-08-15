package com.feby.asyst.mymovie.retrofit.response;


import com.feby.asyst.mymovie.model.Movie;

import java.util.ArrayList;

public class MovieResponse {
    int page;
    int total_results;
    int total_pages;
    ArrayList<Movie> results;

    public int getPage() {
        return page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public ArrayList<Movie> getResults() {
        return results;
    }
}
