package com.example.moviestarsapp.SearchKaterina;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviestarsapp.R;
import com.example.moviestarsapp.home.HomeActivity;
import com.example.moviestarsapp.profile.UserProfileActivity;
import com.example.moviestarsapp.shared.RequestListener;
import com.example.moviestarsapp.shared.json.JsonResponse;
import com.example.moviestarsapp.shared.json.MovieModel;

import java.util.ArrayList;
import java.util.List;

public class SearchResult extends AppCompatActivity {

    private SearchViewModel searchViewModel;
    private List<MovieModel> searchList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        setSupportActionBar(findViewById(R.id.toolbar_search));

        searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        Bundle parameter = getIntent().getExtras();
        SearchViewModel searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        String word = parameter.getString("Search");
        RecyclerView recyclerView = findViewById(R.id.search_result_recycleView);
        SearchAdapter adapter = new SearchAdapter();
        recyclerView.setAdapter(adapter);

        searchViewModel.retrieveMovie(word,new RequestListener() {
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

            }
        });

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
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //TODO("handle the search keyword from user")


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Intent intent=new Intent(SearchResult.this, SearchResult.class);
                Bundle parameter= new Bundle();
                parameter.putString("Search",newText);
                intent.putExtras(parameter);
                startActivity(intent);
                return true;
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
