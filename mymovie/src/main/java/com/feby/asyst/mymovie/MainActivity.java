package com.feby.asyst.mymovie;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.feby.asyst.mymovie.adapter.MovieAdapter;
import com.feby.asyst.mymovie.fragment.FilterFragment;
import com.feby.asyst.mymovie.fragment.SearchFragment;
import com.feby.asyst.mymovie.model.Movie;
import com.feby.asyst.mymovie.retrofit.ApiClient;
import com.feby.asyst.mymovie.retrofit.ApiService;
import com.feby.asyst.mymovie.retrofit.response.MovieResponse;
import com.feby.asyst.mymovie.utility.Constant;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements FilterFragment.OnSubmitButtonListener, SearchFragment.OnSubmitButtonListener{

    RecyclerView recyclerView;
    MovieAdapter movieAdapter;
    ArrayList<Movie> listMovie = new ArrayList<>();
    String year="", sort_by="popularity.desc";
    ProgressBar progressBar;
    int pages =1;
    String query="";
    boolean isLoading = false;
    int total_pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        progressBar =findViewById(R.id.progressbar);

        movieAdapter = new MovieAdapter(this, listMovie, new MovieAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Movie movie) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("movie", movie);
                startActivity(intent);
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(movieAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1)){
                    if (!isLoading){
                        if (total_pages>=pages){
                            progressBar.setVisibility(View.VISIBLE);
                            isLoading=true;
                            if (query.equalsIgnoreCase("")){
                                getData();
                            } else {
                                getSearchData();
                            }
                        }
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        getData();

    }

    public void getData(){
        ApiService apiService = ApiClient.newInstance(getApplicationContext()).create(ApiService.class);

        Call<MovieResponse> call = apiService.getMovie(Constant.KEY_API_KEY, pages, year, sort_by);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                progressBar.setVisibility(View.INVISIBLE);
                if (response.body() != null) {

                    if (response.body().getResults().size() > 0) {

                        listMovie.addAll(response.body().getResults());
                        total_pages=response.body().getTotal_pages();
                        pages=response.body().getPage()+1;
                        movieAdapter.notifyDataSetChanged();
                        isLoading=false;
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error Occured", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    public void getSearchData(){
        ApiService apiService = ApiClient.newInstance(getApplicationContext()).create(ApiService.class);

        Call<MovieResponse> call = apiService.searchMovie(Constant.KEY_API_KEY, pages, query);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                progressBar.setVisibility(View.INVISIBLE);
                if (response.body() != null) {

                    if (response.body().getResults().size() > 0) {

                        listMovie.addAll(response.body().getResults());
                        total_pages=response.body().getTotal_pages();
                        pages=response.body().getPage()+1;
                        movieAdapter.notifyDataSetChanged();
                        isLoading=false;
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error Occured", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.filter_main_menu:
                FilterFragment filterFragment = FilterFragment.newInstance(year, sort_by);
                filterFragment.show(getSupportFragmentManager(),"filter bottom sheet");
                break;
            case R.id.search_main_menu:
                SearchFragment searchFragment = SearchFragment.Instance(query);
                searchFragment.show(getSupportFragmentManager(),"search bottom sheet");
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void OnSubmitButton(String year, String sort_by) {
        this.year = year;
        this.sort_by = sort_by;

        listMovie.clear();
        movieAdapter.notifyDataSetChanged();

        pages = 1;

        getData();
    }

    @Override
    public void OnSubmitButton(String search) {
        this.query = search;

        listMovie.clear();
        movieAdapter.notifyDataSetChanged();

        pages = 1;

        getSearchData();
    }
}
