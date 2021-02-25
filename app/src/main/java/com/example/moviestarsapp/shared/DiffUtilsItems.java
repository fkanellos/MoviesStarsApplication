package com.example.moviestarsapp.shared;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.moviestarsapp.shared.json.MovieModel;

public class DiffUtilsItems extends DiffUtil.ItemCallback<MovieModel>{

    @Override
    public boolean areItemsTheSame(@NonNull MovieModel oldItem, @NonNull MovieModel newItem) {
        return oldItem == newItem;
    }

    @SuppressLint("DiffUtilEquals")
    @Override
    public boolean areContentsTheSame(@NonNull MovieModel oldItem, @NonNull MovieModel newItem) {
        return oldItem.equals(newItem);
    }
}