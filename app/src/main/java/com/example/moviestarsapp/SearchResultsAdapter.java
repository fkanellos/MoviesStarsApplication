package com.example.moviestarsapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

public class SearchResultsAdapter extends ListAdapter<MovieModel, SearchResultsViewHolder> {

    public SearchResultsAdapter() {
        super(new DiffUtilsItems());
    }


    @NonNull
    @Override
    public SearchResultsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_front, parent, false);
        return new SearchResultsViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultsViewHolder holder, int position) {

        MovieModel movieModel = getItem(position);
        holder.bind(movieModel);
    }
}