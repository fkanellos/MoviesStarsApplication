package com.example.moviestarsapp.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviestarsapp.R;
import com.example.moviestarsapp.shared.json.MovieModel;

public class PopularViewHolder extends RecyclerView.ViewHolder {

    public PopularViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void bind(MovieModel movieModel) {

        TextView title = itemView.findViewById(R.id.title);
        title.setText("\"" + movieModel.getTitle() + "\"");

        TextView year = itemView.findViewById(R.id.year);
        String releaseYear = movieModel.getRelease_date().split("-")[0];
        year.setText("(" + releaseYear + ")");


        ImageView imageView = itemView.findViewById(R.id.img);
        Glide.with(itemView.getContext()).load(movieModel.getPoster_path()).into(imageView);

    }
}
