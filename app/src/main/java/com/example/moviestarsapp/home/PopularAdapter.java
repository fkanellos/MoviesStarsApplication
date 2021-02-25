package com.example.moviestarsapp.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import com.example.moviestarsapp.R;
import com.example.moviestarsapp.shared.DiffUtilsItems;
import com.example.moviestarsapp.shared.json.MovieModel;

public class PopularAdapter extends ListAdapter<MovieModel, PopularViewHolder> {

    public PopularAdapter() {
        super(new DiffUtilsItems());
    }


    @NonNull
    @Override
    public PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_front, parent, false);
        return new PopularViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PopularViewHolder holder, int position) {

        MovieModel movieModel = getItem(position);
        holder.bind(movieModel);
    }
}