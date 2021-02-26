package com.example.moviestarsapp.shared.json;

public interface DetailsRequestListener {

        void onSuccessResponse(DetailsJsonResponse response);
        void onErrorResponse(String msg);


}
