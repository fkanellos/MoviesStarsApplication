package com.example.moviestarsapp.shared;

import com.example.moviestarsapp.shared.json.JsonResponse;

public interface RequestListener {

    void onSuccessResponse(JsonResponse response);
    void onErrorResponse(String msg);

}
