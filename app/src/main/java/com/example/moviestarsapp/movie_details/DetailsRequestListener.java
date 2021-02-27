package com.example.moviestarsapp.movie_details;

public interface DetailsRequestListener {

        void onSuccessResponse(DetailsJsonResponse response);
        void onErrorResponse(String msg);


}
