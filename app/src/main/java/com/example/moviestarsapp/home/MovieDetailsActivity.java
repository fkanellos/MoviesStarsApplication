package com.example.moviestarsapp.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.moviestarsapp.R;
import com.example.moviestarsapp.SearchKaterina.SearchResult;
import com.example.moviestarsapp.profile.UserProfileActivity;
import com.example.moviestarsapp.shared.json.DetailsJsonResponse;
import com.example.moviestarsapp.shared.json.DetailsRequestListener;

public class MovieDetailsActivity<parameter> extends AppCompatActivity {
    private final String APIKey = "9bb33d52c77a0f94a17eafe4c83b4988";
    private DetailsViewModel detailsViewModel;
    private int movieId;
    private String strId;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(findViewById(R.id.toolbar_details));
        setContentView(R.layout.movie_details_activity);
        detailsViewModel= new ViewModelProvider(this).get(DetailsViewModel.class);



        Bundle parameter = getIntent().getExtras();

        if(parameter != null){
            movieId = parameter.getInt("movieId");
            strId = String.valueOf(movieId);
        Log.d("blabla", "onCreate: " + movieId +","+ strId);}

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        detailsViewModel.retrieveMovieDetails( strId, new DetailsRequestListener() {
            @Override
            public void onSuccessResponse(DetailsJsonResponse response) {

                TextView title = findViewById(R.id.title);
                TextView title2 = findViewById(R.id.txt_title);
                title2.setText("Movie Title: ");
                title.setText(response.getTitle());
                Log.d("blabla", "onSuccessResponse: "+ response.getTitle());

                TextView releaseDate = findViewById(R.id.release_date);
                TextView releaseDate2 = findViewById(R.id.txt_release_date);
                releaseDate2.setText("Release Date: ");
                releaseDate.setText(response.getRelease_date());

                TextView runtime = findViewById(R.id.runtime);
                TextView runtime2 = findViewById(R.id.txt_runtime);
                runtime2.setText("Runtime: ");
                runtime.setText(String.valueOf(response.getRuntime()));

                TextView status = findViewById(R.id.status);
                TextView status2 = findViewById(R.id.txt_status);
                status2.setText("Status: ");
                status.setText(response.getStatus());

                TextView voteAverage = findViewById(R.id.vote_average);
                TextView voteAverage2 = findViewById(R.id.txt_vote_average);
                voteAverage2.setText("Vote Average: ");
                voteAverage.setText(String.valueOf(response.getVote_average()));

                TextView overview = findViewById(R.id.overview);
                TextView overview2 = findViewById(R.id.txt_overview);
                overview2.setText("Movie Overview: ");
                overview.setText(response.getOverview());

                ImageView image = findViewById(R.id.imageView3);
                Glide.with(image.getContext()).load(response.getPoster_path()).into(image);
            }

            @Override
            public void onErrorResponse(String msg) {
                Log.d("error", "onErrorResponse: aaaaaaaaaaaaa");

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
        searchView.setQueryHint("Search movie");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent=new Intent(MovieDetailsActivity.this, SearchResult.class);
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