package com.example.moviestarsapp.SearchKaterina;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviestarsapp.R;
import com.example.moviestarsapp.shared.json.MovieModel;

public class SearchViewHolder extends RecyclerView.ViewHolder {
    public SearchViewHolder(@NonNull View itemView) {
        super(itemView);
    }
    public void bind(MovieModel movieModel){
        TextView title=itemView.findViewById(R.id.movie_title);
        if (movieModel.getTitle()!=null) {
            title.setText("\"" + movieModel.getTitle() + "\"");
        }

        TextView year = itemView.findViewById(R.id.movie_year);
        if (movieModel.getRelease_date()!=null) {
            String releaseYear = movieModel.getRelease_date().split("-")[0];
            year.setText("(" + releaseYear + ")");
        }


        ImageView imageView = itemView.findViewById(R.id.movie_img);
        Glide.with(itemView.getContext()).load(movieModel.getPoster_path()).into(imageView);
    }
}
