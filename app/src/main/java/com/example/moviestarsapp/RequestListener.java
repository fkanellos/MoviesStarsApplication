package com.example.moviestarsapp;

public interface RequestListener {

    void onSuccessResponse(PopularResponse response);
    void onErrorResponse(String msg);

}
