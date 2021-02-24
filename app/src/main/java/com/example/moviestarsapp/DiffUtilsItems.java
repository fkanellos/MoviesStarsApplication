package com.example.moviestarsapp;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class DiffUtilsItems extends DiffUtil.ItemCallback<PopularDetails>{

    @Override
    public boolean areItemsTheSame(@NonNull PopularDetails oldItem, @NonNull PopularDetails newItem) {
        return oldItem == newItem;
    }

    @SuppressLint("DiffUtilEquals")
    @Override
    public boolean areContentsTheSame(@NonNull PopularDetails oldItem, @NonNull PopularDetails newItem) {
        return oldItem.equals(newItem);
    }
}