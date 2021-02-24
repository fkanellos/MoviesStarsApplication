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

    @NonNull
    private RequestQueue queue;

    public SearchResultsViewModel(@NonNull Application application) {
        super(application);
        queue = Volley.newRequestQueue(application);
    }

    public void retrieveData(String requestURL, RequestListener requestListener){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, requestURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        requestListener.onSuccessResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                requestListener.onErrorResponse(error.getMessage());
            }
        });

        queue.add(stringRequest);
    }



    @Override
    protected void onCleared() {
        super.onCleared();
    }

}
