package com.example.moviestarsapp.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviestarsapp.movie_details.MovieDetailsActivity;
import com.example.moviestarsapp.search.SearchResult;
import com.example.moviestarsapp.shared.json.MovieModel;
import com.example.moviestarsapp.shared.json.JsonResponse;
import com.example.moviestarsapp.R;
import com.example.moviestarsapp.shared.RequestListener;
import com.example.moviestarsapp.profile.UserProfileActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private PopularViewModel viewModel;
    private List<MovieModel> movieList = new ArrayList<MovieModel>();
    private List<MovieModel> responseMovieList;
    private int startPage=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setSupportActionBar(findViewById(R.id.toolbar));

        viewModel = new ViewModelProvider(this).get(PopularViewModel.class);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {

        super.onPostCreate(savedInstanceState);

        PopularAdapter popularAdapter = new PopularAdapter();
        RecyclerView popularRecyclerView = findViewById(R.id.recyclerView);
        popularRecyclerView.setAdapter(popularAdapter);

        MaterialButton randomBtn = findViewById(R.id.random_button);

        viewModel.retrievePopular(startPage, new RequestListener() {
            @Override
            public void onSuccessResponse(JsonResponse response) {

                responseMovieList = response.getResults();
                movieList.addAll(responseMovieList);
                popularAdapter.submitList(movieList);

                randomBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int randomId = viewModel.idGenerator(movieList);
                        Bundle bundle = new Bundle();
                        bundle.putInt("movieId", randomId);
                        Intent intent = new Intent(v.getContext(), MovieDetailsActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        Log.d("randomId", String.valueOf(randomId));
                    }
                });
            }

            @Override
            public void onErrorResponse(String msg) { }
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
        searchView.setQueryHint("Search movie");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Bundle parameter= new Bundle();
                if (query!= null) {
                    parameter.putString("Search", query);
                    Intent intent = new Intent(HomeActivity.this, SearchResult.class);
                    intent.putExtras(parameter);
                    startActivity(intent);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
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