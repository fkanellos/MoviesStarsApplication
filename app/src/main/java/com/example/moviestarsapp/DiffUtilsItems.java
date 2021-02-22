package com.example.moviestarsapp;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class DiffUtilsItems extends DiffUtil.ItemCallback<MovieModel>{

    @Override
    public boolean areItemsTheSame(@NonNull MovieModel oldItem, @NonNull MovieModel newItem) {
        return oldItem == newItem;
    }

    @Override
    public boolean areContentsTheSame(@NonNull MovieModel oldItem, @NonNull MovieModel newItem) {
        return oldItem.equals(newItem);
    }
}