package com.example.moviestarsapp;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class SearchResultsViewHolder extends RecyclerView.ViewHolder {

    public SearchResultsViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @SuppressLint("SetTextI18n")
    public void bind(PopularDetails movieModel, String url){

        TextView title = itemView.findViewById(R.id.title);
        title.setText("\"" + movieModel.getTitle() + "\"");

//        TextView year = itemView.findViewById(R.id.year);
//        year.setText("(" + url + movieModel.getPoster_path()+ ")");


        ImageView img = itemView.findViewById(R.id.img);

        Picasso.get().setIndicatorsEnabled(true);
        Picasso.get().load("http://i.imgur.com/DvpvklR.png")
                .error(R.drawable.ic_launcher_background).into(img);


//        TextView genre = itemView.findViewById(R.id.genre);
//        genre.setText(movieModel.getGenre());

//        TextView duration = itemView.findViewById(R.id.duration);
//        duration.setText(movieModel.getDuration());

    }
}
