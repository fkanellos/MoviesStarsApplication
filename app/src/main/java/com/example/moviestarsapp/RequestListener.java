package com.example.moviestarsapp;

public interface RequestListener {

    void onSuccessResponse(String msg);
    void onErrorResponse(String msg);

}
