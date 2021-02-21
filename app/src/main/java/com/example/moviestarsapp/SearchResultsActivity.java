package com.example.moviestarsapp;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.AndroidViewModel;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Movie;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        SearchResultsViewModel searchResultsViewModel = new ViewModelProvider(this).get(SearchResultsViewModel.class);
//        ArrayList<Object> popularList;

        TextView textView = findViewById(R.id.text);
        searchResultsViewModel.retrievePopular(new PopularListener() {

        String jsonString;

            @Override
            public void onSuccessResponse(String msg) {

                    jsonString = msg;
                    ArrayList<JSONObject> popularList = new ArrayList<JSONObject>();
                    try {
                        JSONObject jsonObject = new JSONObject(jsonString);
                        JSONArray results = jsonObject.getJSONArray("results");
                        System.out.println(results);
//                        for (int i=0; i<jsonArray.length();i++){
//                            popularList.add(jsonArray.getJSONObject(i));
//                        }
//                        System.out.println(popularList.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    textView.setText(msg);
                }
            }

            @Override
            public void onErrorResponse(String msg) {
                textView.setText(msg);

            }
        });


        RecyclerView srRecyclerView = findViewById(R.id.search_results);

        SearchResultsAdapter srAdapter = new SearchResultsAdapter();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);

        srRecyclerView.setLayoutManager(gridLayoutManager);
        srRecyclerView.setAdapter(srAdapter);
        srAdapter.submitList(getMockUserList());

    }



    private List<MovieModel> getMockUserList(){

        List<MovieModel> movieList = new ArrayList<MovieModel>();

        movieList.add(new MovieModel("Titanic", 2000, "Romantic", "3h 50m" ));
        movieList.add(new MovieModel("Turtles Can Fly", 2007, "Drama", "2h" ));
        movieList.add(new MovieModel("Titanic", 2000, "Romantic", "3h 50m" ));
        movieList.add(new MovieModel("Titanic", 2000, "Romantic", "3h 50m" ));
        movieList.add(new MovieModel("Turtles Can Fly", 2007, "Drama", "2h" ));
        movieList.add(new MovieModel("Titanic", 2000, "Romantic", "3h 50m" ));
        movieList.add(new MovieModel("Titanic", 2000, "Romantic", "3h 50m" ));
        movieList.add(new MovieModel("Turtles Can Fly", 2007, "Drama", "2h" ));
        movieList.add(new MovieModel("Titanic", 2000, "Romantic", "3h 50m" ));
        movieList.add(new MovieModel("Titanic", 2000, "Romantic", "3h 50m" ));
        movieList.add(new MovieModel("Turtles Can Fly", 2007, "Drama", "2h" ));
        movieList.add(new MovieModel("Titanic", 2000, "Romantic", "3h 50m" ));
        movieList.add(new MovieModel("Titanic", 2000, "Romantic", "3h 50m" ));
        movieList.add(new MovieModel("Turtles Can Fly", 2007, "Drama", "2h" ));
        movieList.add(new MovieModel("Titanic", 2000, "Romantic", "3h 50m" ));
        movieList.add(new MovieModel("Titanic", 2000, "Romantic", "3h 50m" ));

        return movieList;
    }
}