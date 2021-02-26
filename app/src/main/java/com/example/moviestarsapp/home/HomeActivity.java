package com.example.moviestarsapp.home;

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

import com.example.moviestarsapp.SearchKaterina.SearchResult;
import com.example.moviestarsapp.shared.json.MovieModel;
import com.example.moviestarsapp.shared.json.JsonResponse;
import com.example.moviestarsapp.R;
import com.example.moviestarsapp.shared.RequestListener;
import com.example.moviestarsapp.profile.UserProfileActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private PopularViewModel viewModel;
    private List<MovieModel> movieList = new ArrayList<MovieModel>();

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

        viewModel.retrievePopular(new RequestListener() {
            @Override
            public void onSuccessResponse(JsonResponse response) {

                movieList = response.getResults();

                List<MovieModel> savedData = popularAdapter.getCurrentList();
                List<MovieModel> newList = new ArrayList<>();
                newList.addAll(savedData);
                newList.addAll(movieList);
                popularAdapter.submitList(newList);
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
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //TODO("handle the search keyword from user")
                Intent intent=new Intent(HomeActivity.this, SearchResult.class);
                Bundle parameter= new Bundle();
                parameter.putString("Search",query);
                intent.putExtras(parameter);
                startActivity(intent);

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