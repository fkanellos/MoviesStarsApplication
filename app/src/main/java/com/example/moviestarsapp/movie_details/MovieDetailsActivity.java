package com.example.moviestarsapp.movie_details;

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
import com.example.moviestarsapp.movie_details.DetailsJsonResponse;
import com.example.moviestarsapp.movie_details.DetailsRequestListener;
import com.example.moviestarsapp.movie_details.DetailsViewModel;
import com.example.moviestarsapp.profile.UserProfileActivity;
import com.example.moviestarsapp.search.SearchResult;


public class MovieDetailsActivity<parameter> extends AppCompatActivity {
    private final String APIKey = "9bb33d52c77a0f94a17eafe4c83b4988";
    private DetailsViewModel detailsViewModel;
    private int movieId;
    private String strId;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                title2.setText("Title: ");
                title.setText(response.getTitle());
                Log.d("blabla", "onSuccessResponse: "+ response.getTitle());

                TextView releaseDate = findViewById(R.id.release_date);
                TextView releaseDate2 = findViewById(R.id.txt_release_date);
                releaseDate2.setText("Release Date: ");
                if (response.getRelease_date()!=null){
                releaseDate.setText(response.getRelease_date());}

                TextView runtime = findViewById(R.id.runtime);
                TextView runtime2 = findViewById(R.id.txt_runtime);
                runtime2.setText("Runtime: ");
                if (String.valueOf(response.getRuntime())!=null){
                runtime.setText(String.valueOf(response.getRuntime()) + "min");}

                TextView status = findViewById(R.id.status);
                TextView status2 = findViewById(R.id.txt_status);
                status2.setText("Status: ");
                if (response.getStatus()!=null){
                status.setText(response.getStatus());}

                TextView voteAverage = findViewById(R.id.vote_average);
                TextView voteAverage2 = findViewById(R.id.txt_vote_average);
                voteAverage2.setText("Vote Average: ");
                if(response.getVote_average()!=0){
                voteAverage.setText(String.valueOf(response.getVote_average()) + "/10");}

                TextView overview = findViewById(R.id.overview);
                TextView overview2 = findViewById(R.id.txt_overview);
                overview2.setText("Overview: ");
                if (response.getOverview()!=null){
                overview.setText(response.getOverview());}

                ImageView image = findViewById(R.id.imageView3);
                Glide.with(image.getContext()).load(response.getPoster_path()).error(R.drawable.ic_movie_svgrepo_com).into(image);
            }

            @Override
            public void onErrorResponse(String msg) {
                Log.d("error", "onErrorResponse: aaaaaaaaaaaaa");

            }
        });


    }

}