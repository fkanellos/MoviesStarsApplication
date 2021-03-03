package com.example.moviestarsapp.search;

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
import com.example.moviestarsapp.shared.RequestListener;
import com.example.moviestarsapp.shared.json.ConfigurationsResponse;
import com.example.moviestarsapp.shared.json.JsonResponse;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class SearchViewModel extends AndroidViewModel {

//https://api.themoviedb.org/3/search/movie?api_key=9bb33d52c77a0f94a17eafe4c83b4988&language=en-US&query=woman&page=1&include_adult=false

    private final String startUrl = "https://api.themoviedb.org/3/search/movie?api_key=9bb33d52c77a0f94a17eafe4c83b4988&language=en-US&query=";
    private final String endUrl1 = "&page=";
    private final String endUrl2 = "&include_adult=false";

    private final String APIKey = "9bb33d52c77a0f94a17eafe4c83b4988";
    private final String configURL = "https://api.themoviedb.org/3/configuration?api_key=" + APIKey;
    private String prefixPosterURL;
    private String queryFlag;


    @NonNull
    private RequestQueue queue;

    public SearchViewModel(@NonNull Application application) {
        super(application);
        queue = Volley.newRequestQueue(application);
//        retrieveConfiguration();
    }

    private void retrieveConfiguration(ConfigListener configListener) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, configURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();

                        ConfigurationsResponse configurationsResponse = gson.fromJson(response, ConfigurationsResponse.class);

                        String baseURL = configurationsResponse.getImages().getBase_url();
                        String sizeURL = configurationsResponse.getImages().getPoster_sizes()[4];
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

    public void retrieveMovie(int page, String wordNoEncoding, RequestListener requestListener) throws UnsupportedEncodingException {

        String word = URLEncoder.encode(wordNoEncoding, "UTF-8");
        if (!word.equals(queryFlag))
            queue.cancelAll(new RequestQueue.RequestFilter() {
                @Override
                public boolean apply(Request<?> request) {
                    return true;
                }
            });

        retrieveConfiguration(new ConfigListener() {

            @Override
            public void onSuccessResponse(String msg) {

                queryFlag = word;
                String fullRequestUrl = startUrl + word + endUrl1 + page + endUrl2;
                Log.d("SearchViewModel", "Request: " + fullRequestUrl);

                StringRequest stringRequest = new StringRequest(Request.Method.GET, fullRequestUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String msg) {

                                Gson gson = new Gson();
                                JsonResponse response = gson.fromJson(msg, JsonResponse.class);
                                response.setThePosterUrl(prefixPosterURL);
                                requestListener.onSuccessResponse(response);

                                if (page < response.getTotal_pages()) {
                                    try {
                                        retrieveMovie(page + 1, word, requestListener);
                                    } catch (UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                    }
                                }
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
            public void onErrorResponse(String msg) {

            }
        });


    }
}
