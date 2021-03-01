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
import com.example.moviestarsapp.shared.ConfigListener;
import com.example.moviestarsapp.shared.json.ConfigurationsResponse;
import com.example.moviestarsapp.shared.json.JsonResponse;
import com.example.moviestarsapp.shared.RequestListener;
import com.example.moviestarsapp.shared.json.MovieModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PopularViewModel extends AndroidViewModel {

    private final String APIKey = "9bb33d52c77a0f94a17eafe4c83b4988";
    private final String configURL = "https://api.themoviedb.org/3/configuration?api_key=" + APIKey;
    private final String popularURL = "https://api.themoviedb.org/3/movie/popular?api_key=" + APIKey;

    private final String MovieDetailsURL = "https://api.themoviedb.org/3/movie/movieId?api_key=" + APIKey;
    private String prefixPosterURL;

    @NonNull
    private RequestQueue queue;

    public PopularViewModel(@NonNull Application application) {
        super(application);

        queue = Volley.newRequestQueue(application);
    }

    private void retrieveConfiguration(ConfigListener configListener) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, configURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();

                        ConfigurationsResponse configurationsResponse = gson.fromJson(response, ConfigurationsResponse.class);

                        String baseURL = configurationsResponse.getImages().getBase_url();
                        String sizeURL = configurationsResponse.getImages().getPoster_sizes()[3];
                        prefixPosterURL = baseURL + sizeURL;
                        configListener.onSuccessResponse(prefixPosterURL);
                        Log.d("Good Configurations", prefixPosterURL);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("No configurations", error.getMessage(), error);
            }
        });

        queue.add(stringRequest);
    }

    public void retrievePopular(int page, RequestListener requestListener) {
        String popularPageURL = popularURL + "&page=" + page;

        retrieveConfiguration(new ConfigListener() {
            @Override
            public void onSuccessResponse(String msg) {

                StringRequest stringRequest = new StringRequest(Request.Method.GET, popularPageURL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String msg) {
                                Gson gson = new Gson();
                                JsonResponse response = gson.fromJson(msg, JsonResponse.class);
                                response.setThePosterUrl(prefixPosterURL);
                                Log.d("RECURSION", "onResponse: " + msg);
                                requestListener.onSuccessResponse(response);

                                if (page < response.getTotal_pages()) {
                                    retrievePopular(page + 1, requestListener);
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        requestListener.onErrorResponse(error.getMessage());
                        Log.d("ERROR_POPULAR", "onErrorResponse: " + error);
                    }
                });
                queue.add(stringRequest);
                Log.d("QUEUE", "onSuccessResponse: " + queue);
            }

            @Override
            public void onErrorResponse(String msg) {
            }
        });

    }

    public void retrieveMovieDetails(RequestListener detailRequestListener) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, MovieDetailsURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String msg) {
                        Gson gson = new Gson();
                        JsonResponse response = gson.fromJson(msg, JsonResponse.class);


                        detailRequestListener.onSuccessResponse(response);
                        //TODO("have another page? YES run again NO forgot: RECURSION")
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                detailRequestListener.onErrorResponse(error.getMessage());
            }
        });

        queue.add(stringRequest);
    }

    public int idGenerator(List<MovieModel> movieModelList) {

        List<Integer> idList = new ArrayList<Integer>();
        for (MovieModel movie : movieModelList) {
            idList.add(movie.getId());
        }
        Random rand = new Random();
        int id = idList.get(rand.nextInt(idList.size()));

        return id;
    }

}
