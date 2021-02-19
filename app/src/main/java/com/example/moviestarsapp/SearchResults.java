package com.example.moviestarsapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Movie;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class SearchResults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        RecyclerView srRecyclerView = findViewById(R.id.search_results);

        SearchResultsAdapter srAdapter = new SearchResultsAdapter(getMockUserList());
        srRecyclerView.setAdapter(srAdapter);
    }


    private List<MovieModel> getMockUserList(){

        List<MovieModel> movieList = new ArrayList<MovieModel>();

        movieList.add(new MovieModel("Titanic", 2000, "Romantic", 120 ));
        movieList.add(new MovieModel("Turtles Can Fly", 2007, "Drama", 135 ));
        movieList.add(new MovieModel("Titanic", 2000, "Romantic", 120 ));
        movieList.add(new MovieModel("Titanic", 2000, "Romantic", 120 ));

        return movieList;
    }
}