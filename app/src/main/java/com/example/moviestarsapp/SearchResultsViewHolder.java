package com.example.moviestarsapp;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SearchResultsViewHolder extends RecyclerView.ViewHolder {

    public SearchResultsViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @SuppressLint("SetTextI18n")
    public void bind(MovieModel movieModel){

        TextView title = itemView.findViewById(R.id.title);
        title.setText("\"" + movieModel.getTitle() + "\"");

        TextView year = itemView.findViewById(R.id.year);
        year.setText("(" + String.valueOf(movieModel.getYear())+ ")");

//        TextView genre = itemView.findViewById(R.id.genre);
//        genre.setText(movieModel.getGenre());

//        TextView duration = itemView.findViewById(R.id.duration);
//        duration.setText(movieModel.getDuration());

    }
}
