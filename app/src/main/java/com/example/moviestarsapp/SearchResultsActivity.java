package com.example.moviestarsapp;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsActivity extends AppCompatActivity {

    private final String APIKey = "9bb33d52c77a0f94a17eafe4c83b4988";
    private final String configURL = "https://api.themoviedb.org/3/configuration?api_key=" + APIKey;
    private final String popularURL = "https://api.themoviedb.org/3/movie/popular?api_key=" + APIKey;

    private String prefixPosterURL;
    private List<PopularDetails> listDetails = new ArrayList<PopularDetails>();
    private Boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        SearchResultsViewModel searchResultsViewModel = new ViewModelProvider(this).get(SearchResultsViewModel.class);
        RecyclerView srRecyclerView = findViewById(R.id.search_results);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
//        srRecyclerView.setLayoutManager(gridLayoutManager);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        srRecyclerView.setLayoutManager(linearLayoutManager);

        searchResultsViewModel.retrieveData(configURL, new RequestListener() {
            @Override
            public void onSuccessResponse(String msg) {

                Gson gson = new Gson();
                ConfigurationsResponse configurationsResponse = gson.fromJson(msg, ConfigurationsResponse.class);

                String baseURL = configurationsResponse.getImages().getBase_url();
                String sizeURL = configurationsResponse.getImages().getPoster_sizes()[2];
                prefixPosterURL = baseURL + sizeURL;

                SearchResultsAdapter srAdapter = new SearchResultsAdapter(prefixPosterURL);
                srRecyclerView.setAdapter(srAdapter);

                Log.d("GOOD prefixPosterURL", prefixPosterURL);

                searchResultsViewModel.retrieveData(popularURL, new RequestListener() {
                    @Override
                    public void onSuccessResponse(String msg) {

                        Gson gson = new Gson();
                        PopularResponse response = gson.fromJson(msg, PopularResponse.class);
                        listDetails = response.getResults();
                        srAdapter.submitList(listDetails);

                        String epitelous = prefixPosterURL + listDetails.get(0).getPoster_path();

                        Log.d("GOOD popularList", epitelous);
                    }

                    @Override
                    public void onErrorResponse(String msg) {
                        Log.d("BAD popularList", msg);
                    }
                });

            }
            @Override
            public void onErrorResponse(String msg) {
                Log.d("BAD prefixPosterURL", msg);
            }
        });


    }



    private List<MovieModel> getMockUserList(){

        List<MovieModel> movieList = new ArrayList<MovieModel>();

        movieList.add(new MovieModel("Once Upon a Time in Holywood", 2000));
        movieList.add(new MovieModel("Titanic", 2000));
        movieList.add(new MovieModel("Turtles Can Fly", 2000));
        movieList.add(new MovieModel("Joker", 2000));
        movieList.add(new MovieModel("Once Upon a Time in Holywood", 2000));
        movieList.add(new MovieModel("Titanic", 2000));
        movieList.add(new MovieModel("Turtles Can Fly", 2000));
        movieList.add(new MovieModel("Joker", 2000));
        movieList.add(new MovieModel("Once Upon a Time in Holywood", 2000));
        movieList.add(new MovieModel("Titanic", 2000));
        movieList.add(new MovieModel("Turtles Can Fly", 2000));
        movieList.add(new MovieModel("Joker", 2000));
        movieList.add(new MovieModel("Once Upon a Time in Holywood", 2000));
        movieList.add(new MovieModel("Titanic", 2000));
        movieList.add(new MovieModel("Turtles Can Fly", 2000));
        movieList.add(new MovieModel("Joker", 2000));
        movieList.add(new MovieModel("Once Upon a Time in Holywood", 2000));
        movieList.add(new MovieModel("Titanic", 2000));
        movieList.add(new MovieModel("Turtles Can Fly", 2000));
        movieList.add(new MovieModel("Joker", 2000));
        movieList.add(new MovieModel("Once Upon a Time in Holywood", 2000));
        movieList.add(new MovieModel("Titanic", 2000));
        movieList.add(new MovieModel("Turtles Can Fly", 2000));
        movieList.add(new MovieModel("Joker", 2000));







        return movieList;
    }
}