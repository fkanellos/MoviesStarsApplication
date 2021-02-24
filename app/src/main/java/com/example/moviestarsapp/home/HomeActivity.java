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

import com.example.moviestarsapp.PopularDetails;
import com.example.moviestarsapp.PopularResponse;
import com.example.moviestarsapp.R;
import com.example.moviestarsapp.RequestListener;
import com.example.moviestarsapp.SearchResultsAdapter;
import com.example.moviestarsapp.profile.UserProfileActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private SearchResultViewModel viewModel;
    private List<PopularDetails> listDetails = new ArrayList<PopularDetails>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setSupportActionBar(findViewById(R.id.toolbar));

        viewModel = new ViewModelProvider(this).get(SearchResultViewModel.class);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        SearchResultsAdapter srAdapter = new SearchResultsAdapter();
        RecyclerView srRecyclerView = findViewById(R.id.recyclerView);
        srRecyclerView.setAdapter(srAdapter);

        viewModel.retrievePopular(new RequestListener() {
            @Override
            public void onSuccessResponse(PopularResponse response) {

                listDetails = response.getResults();
                srAdapter.submitList(listDetails);
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
                return false;
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