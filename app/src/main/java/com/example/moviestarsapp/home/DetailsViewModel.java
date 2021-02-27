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
import com.example.moviestarsapp.shared.json.DetailsJsonResponse;
import com.example.moviestarsapp.shared.json.DetailsRequestListener;
import com.google.gson.Gson;

public class DetailsViewModel extends AndroidViewModel {

    private final String APIKey = "9bb33d52c77a0f94a17eafe4c83b4988";
    private final String configURL = "https://api.themoviedb.org/3/configuration?api_key=" + APIKey;
    private final String MovieDetailsURL = "https://api.themoviedb.org/3/movie/";
    private final String MovieDetailURLEnd = "?api_key=" + APIKey;

//    https://api.themoviedb.org/3/movie/464052?api_key=9bb33d52c77a0f94a17eafe4c83b4988&language=en-US




    private String prefixPosterURL;
    @NonNull
    private RequestQueue queue;


    public DetailsViewModel(@NonNull Application application) {
        super(application);
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


    public void retrieveMovieDetails(String strId , DetailsRequestListener detailRequestListener) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, MovieDetailsURL + strId + MovieDetailURLEnd,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String msg) {
                        Gson gson = new Gson();
                        DetailsJsonResponse response = gson.fromJson(msg, DetailsJsonResponse.class);
                        response.setPosterPrefixPath(prefixPosterURL);
                        detailRequestListener.onSuccessResponse(response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                detailRequestListener.onErrorResponse(error.getMessage());
            }
        });

        queue.add(stringRequest);
    }



}
