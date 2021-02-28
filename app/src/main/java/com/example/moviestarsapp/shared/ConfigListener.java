package com.example.moviestarsapp.shared;

import com.example.moviestarsapp.shared.json.JsonResponse;

public interface ConfigListener {

    void onSuccessResponse(String msg);
    void onErrorResponse(String msg);
}
