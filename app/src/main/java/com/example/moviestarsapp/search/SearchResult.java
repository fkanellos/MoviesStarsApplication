package com.example.moviestarsapp.search;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviestarsapp.R;
import com.example.moviestarsapp.profile.UserProfileActivity;
import com.example.moviestarsapp.shared.RequestListener;
import com.example.moviestarsapp.shared.json.JsonResponse;
import com.example.moviestarsapp.shared.json.MovieModel;
import com.google.android.material.snackbar.Snackbar;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class SearchResult extends AppCompatActivity {

    private SearchViewModel searchViewModel;
    private List<MovieModel> searchList = new ArrayList<>();
    private SearchAdapter adapter = new SearchAdapter();
    private Bundle parameter;
    private String word;
    private final int startPage = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        setSupportActionBar(findViewById(R.id.toolbar_search));

        searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        parameter = getIntent().getExtras();
        word = parameter.getString("Search");
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        SearchViewModel searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        RecyclerView recyclerView = findViewById(R.id.search_result_recycleView);

        recyclerView.setAdapter(adapter);

        TextView searchTerm=findViewById(R.id.search_term);
        searchTerm.setText("Search Results: "+ word);

        try {
            searchViewModel.retrieveMovie(startPage, word, new RequestListener() {
                @Override
                public void onSuccessResponse(JsonResponse response) {
                    searchList = response.getResults();

                    List<MovieModel> savedData = adapter.getCurrentList();
                    List<MovieModel> newList = new ArrayList<>();
                    newList.addAll(savedData);
                    newList.addAll(searchList);
                    adapter.submitList(newList);
                }

                @Override
                public void onErrorResponse(String msg) {
//                    Snackbar.make(searchTerm, R.string.search_error, Snackbar.LENGTH_SHORT)
//                            .show();
                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setQueryHint("Search movie");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (newText.length()>=3) {
                    TextView searchTerm = findViewById(R.id.search_term);
                    searchTerm.setText("Search Results: " + newText);

                    adapter.submitList(new ArrayList<>());

                    try {
                        searchViewModel.retrieveMovie(startPage, newText, new RequestListener() {
                        @Override
                        public void onSuccessResponse(JsonResponse response) {

                            searchList = response.getResults();
                            List<MovieModel> savedData = adapter.getCurrentList();
                            List<MovieModel> newList = new ArrayList<>();
                            newList.addAll(savedData);
                            newList.addAll(searchList);
                            adapter.submitList(newList);
                            Log.d("SearchResultGood", "onSuccessResponse: " + response.getResults().toString());
                        }

                        @Override
                        public void onErrorResponse(String msg) {
                            Log.d("SearchResultBad", "onErrorResponse: " + msg);
                        }
                    });
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }


                }
                return false;
            }
        });
        return super.onPrepareOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.search) {

        } else if (item.getItemId() == R.id.profile) {
            Intent intent = new Intent(this, UserProfileActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
