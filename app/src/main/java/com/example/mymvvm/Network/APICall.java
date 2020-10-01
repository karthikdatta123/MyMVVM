package com.example.mymvvm.Network;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mymvvm.App;
import com.example.mymvvm.Model.tile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class APICall {

    private RequestQueue requestQueue;
    private LiveData<ArrayList<tile>> upComingMovieList;
    private LiveData<ArrayList<tile>> topRatedMovieList;
    private LiveData<ArrayList<tile>> nowPlayingMovieList;
    private static APICall instance = null;
    ArrayList<tile> tiles;
    Context context;

    public APICall(Context context){
        upComingMovieList = new MutableLiveData<>();
        topRatedMovieList = new MutableLiveData<>();
        nowPlayingMovieList = new MutableLiveData<>();
        requestQueue = Volley.newRequestQueue(context);
    }
//
//    public APICall(){
//        requestQueue = Volley.newRequestQueue(App.getContext());
//
//        upComingMovieList = new MutableLiveData<>();
//        topRatedMovieList = new MutableLiveData<>();
//        nowPlayingMovieList = new MutableLiveData<>();
//    }

    public static  synchronized  APICall getInstance(Context context){
        if(null == instance){
            instance = new APICall(context);
        }
        return instance;
    }

    public static  synchronized  APICall getInstance(){
        if(instance == null){
            throw new IllegalStateException(APICall.class.getSimpleName() + "is not initialized");
        }
        return instance;
    }

    public LiveData<ArrayList<tile>> getUpComingMovieList(String url){
        Log.d("Test", "in API getUp");

       return getMovieList(url, upComingMovieList);
    }

    public LiveData<ArrayList<tile>> getTopRatedMovieList(String url){
        Log.d("Test", "in API getTop");
        return getMovieList(url, topRatedMovieList);
    }

    public LiveData<ArrayList<tile>> getNowPlayingMovieList(String url){
        Log.d("Test", "in API getNow");

        return getMovieList(url, nowPlayingMovieList);
    }

    public LiveData<ArrayList<tile>> getMovieList(String url, final LiveData<ArrayList<tile>> initial){
        Log.d("Test", "in API getMovie");

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Test", "API Json response");

                Log.d("TEST url", url);

                try {
                    JSONArray jsonArray = response.getJSONArray("results");

                    for(int i=0; i< jsonArray.length(); i++){
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                        Log.d("TEST", jsonObject1.getString("original_title"));

                        String title = jsonObject1.getString("original_title");
                        String posterUrl =  jsonObject1.getString("poster_path");
                        String voteAvg = jsonObject1.getString("vote_average");
                        String popularity = jsonObject1.getString("popularity");
                        String overView = jsonObject1.getString("overview");
                        int id = jsonObject1.getInt("id");

                        Log.d("TEST Json", title);
                        Log.d("TEST Json", String.valueOf(jsonArray.length()));

                        tiles.add(new tile(title, posterUrl, voteAvg, popularity, overView, id));

                    }
                    if(initial.getValue()!=null)
                        initial.getValue().addAll(tiles);
                    else ((MutableLiveData)initial).setValue(tiles);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Test", "API Error");

            }
        });
        requestQueue.add(request);
        return initial;
    }


}
