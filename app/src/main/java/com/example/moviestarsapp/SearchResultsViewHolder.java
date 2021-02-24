package com.example.moviestarsapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class SearchResultsViewHolder extends RecyclerView.ViewHolder {

    public SearchResultsViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void bind(PopularDetails movieModel) {

        TextView title = itemView.findViewById(R.id.title);
        title.setText("\"" + movieModel.getTitle() + "\"");

        TextView year = itemView.findViewById(R.id.year);
        year.setText("(" + movieModel.getTitle() + ")");


        ImageView imageView = itemView.findViewById(R.id.img);
        Glide.with(itemView.getContext()).load(movieModel.getPoster_path()).into(imageView);
//        TextView genre = itemView.findViewById(R.id.genre);
//        genre.setText(movieModel.getGenre());

//        TextView duration = itemView.findViewById(R.id.duration);
//        duration.setText(movieModel.getDuration());

    }
}

///http://image.tmdb.org/t/p/w500uwjaCH7PiWrkz7oWJ4fcL3xGrb0.jpg