package com.example.moviestarsapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class SearchResultsViewModel extends AndroidViewModel {

    private final String api_key = "9bb33d52c77a0f94a17eafe4c83b4988";
    private final String url = "https://api.themoviedb.org/3/movie/popular?api_key="
            + api_key + "&language=en-US&page=1";

    @NonNull
    private RequestQueue queue;

    public SearchResultsViewModel(@NonNull Application application) {
        super(application);
        queue = Volley.newRequestQueue(application);
    }

    public void retrievePopular(PopularListener popularListener){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                popularListener.onSuccessResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                popularListener.onErrorResponse(error.getMessage());
            }
        });

        queue.add(stringRequest);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

}
