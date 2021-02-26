package com.example.moviestarsapp.home;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.moviestarsapp.shared.json.ConfigurationsResponse;
import com.example.moviestarsapp.shared.json.JsonResponse;
import com.example.moviestarsapp.shared.RequestListener;
import com.google.gson.Gson;

public class PopularViewModel extends AndroidViewModel {

    private final String APIKey = "9bb33d52c77a0f94a17eafe4c83b4988";
    private final String configURL = "https://api.themoviedb.org/3/configuration?api_key=" + APIKey;
    private final String popularURL = "https://api.themoviedb.org/3/movie/popular?api_key=" + APIKey;
    int page;


    private String prefixPosterURL;
    @NonNull
    private RequestQueue queue;

    public PopularViewModel(@NonNull Application application) {
        super(application);

        page = 0;
        queue = Volley.newRequestQueue(application);
        retrieveConfiguration();
    }

    private void retrieveConfiguration() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, configURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();

//                      prefixURL -> imageURLpath
                        ConfigurationsResponse configurationsResponse = gson.fromJson(response, ConfigurationsResponse.class);

                        String baseURL = configurationsResponse.getImages().getBase_url();
                        String sizeURL = configurationsResponse.getImages().getPoster_sizes()[3];
                        prefixPosterURL = baseURL + sizeURL;
                        Log.d("GOOD prefixPosterURL", prefixPosterURL);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ViewModel", error.getMessage(), error);
            }
        });

        queue.add(stringRequest);
    }

    public void retrievePopular(RequestListener requestListener) {
        page = page + 1;
        String popularPageURL = popularURL + "&page=" + page;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, popularPageURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String msg) {
                        Gson gson = new Gson();
                        JsonResponse response = gson.fromJson(msg, JsonResponse.class);
                        response.setThePosterUrl(prefixPosterURL);
                        Log.d("RECURSION", "onResponse: " + popularPageURL);
                        requestListener.onSuccessResponse(response);

                        if(page<=7){retrievePopular(requestListener);}
                        //TODO("have another page? YES run again NO forgot: RECURSION")
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                requestListener.onErrorResponse(error.getMessage());
            }
        });

        queue.add(stringRequest);
    }
}
