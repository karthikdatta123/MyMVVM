package com.example.mymvvm.Repository;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.mymvvm.Model.tile;
import com.example.mymvvm.Network.APICall;

import java.util.ArrayList;

public class MovieRepository {

    APICall apiCall = APICall.getInstance();

    private static MovieRepository instance = null;

    public static  MovieRepository getInstance(){
        if(instance == null){
            instance = new MovieRepository();
        }
        return instance;
    }

    public LiveData<ArrayList<tile>> getTopMovieList(){
        Log.d("Test","In Movie Repository Top");
        return apiCall.getTopRatedMovieList("https://api.themoviedb.org/3/movie/top_rated?api_key=d7f878bb9451378443935606cd16f3ec");
    }

    public LiveData<ArrayList<tile>> getUpcomingMovieList(){
        Log.d("Test","In Movie Repository Up");
        return apiCall.getUpComingMovieList("https://api.themoviedb.org/3/movie/upcoming?api_key=d7f878bb9451378443935606cd16f3ec");
    }

    public LiveData<ArrayList<tile>> getNowPlayingMovieList(){
        Log.d("Test","In Movie Repository Now");
        return apiCall.getNowPlayingMovieList("https://api.themoviedb.org/3/movie/now_playing?api_key=d7f878bb9451378443935606cd16f3ec");
    }

}
