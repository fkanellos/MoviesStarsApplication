package com.example.moviestarsapp.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviestarsapp.R;
import com.example.moviestarsapp.movie_details.MovieDetailsActivity;
import com.example.moviestarsapp.shared.json.MovieModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PopularViewHolder extends RecyclerView.ViewHolder {

    public PopularViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void bind(MovieModel movieModel) {

        TextView title = itemView.findViewById(R.id.title);
        if (movieModel.getTitle()!=null) {
            title.setText("\"" + movieModel.getTitle() + "\"");
            Log.d("VIEWHOLDER", "bind: " + movieModel.getTitle());
        }

        TextView year = itemView.findViewById(R.id.year);
        if (movieModel.getRelease_date()!=null) {
            String releaseYear = movieModel.getRelease_date().split("-")[0];
            year.setText("(" + releaseYear + ")");
        }

        ImageView imageView = itemView.findViewById(R.id.img);
        Glide.with(itemView.getContext()).load(movieModel.getPoster_path()).error(R.drawable.ic_movie_svgrepo_com).into(imageView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = movieModel.getId();
                Context context = itemView.getContext();
                Intent intent = new Intent(context, MovieDetailsActivity.class);
                Bundle parameter = new Bundle();
                parameter.putInt("movieId", id);
                intent.putExtras(parameter);
                context.startActivity(intent);

            }
        });
    }
}
