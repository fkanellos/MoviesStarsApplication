package com.example.moviestarsapp.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import com.example.moviestarsapp.R;
import com.example.moviestarsapp.SearchKaterina.SearchViewHolder;
import com.example.moviestarsapp.shared.DiffUtilsItems;
import com.example.moviestarsapp.shared.json.MovieModel;

public class SearchAdapter extends ListAdapter<MovieModel, SearchViewHolder> {

    public SearchAdapter(){
        super(new DiffUtilsItems());
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_search, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        MovieModel movieModel = getItem(position);
        holder.bind(movieModel);
    }
}
