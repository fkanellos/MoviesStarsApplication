package com.example.moviestarsapp.home;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.moviestarsapp.R;
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
                title.setText(response.getTitle());
                Log.d("blabla", "onSuccessResponse: "+ response.getTitle());

                TextView originaltitle =findViewById(R.id.original_title);
                originaltitle.setText(response.getOriginal_title());

                TextView releaseDate = findViewById(R.id.release_date);
                releaseDate.setText(response.getRelease_date());

                TextView runtime = findViewById(R.id.runtime);
                runtime.setText(String.valueOf(response.getRuntime()));

                TextView status = findViewById(R.id.status);
                status.setText(response.getStatus());

                TextView voteAverage = findViewById(R.id.vote_average);
                voteAverage.setText(String.valueOf(response.getVote_average()));

                TextView overview = findViewById(R.id.overview);
                overview.setText(response.getOverview());
//
                ImageView image = findViewById(R.id.imageView3);
                Glide.with(image.getContext()).load(response.getPoster_path()).into(image);
            }

            @Override
            public void onErrorResponse(String msg) {
                Log.d("error", "onErrorResponse: aaaaaaaaaaaaa");

            }
        });


    }
}