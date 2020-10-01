package com.example.mymvvm.MovieViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mymvvm.Model.tile;
import com.example.mymvvm.Repository.MovieRepository;

import java.util.ArrayList;

public class MovieViewModel extends ViewModel {

    MovieRepository movieRepository;
    LiveData<ArrayList<tile>> topMoives;
    public MovieViewModel() {
        movieRepository = MovieRepository.getInstance();
//        topMoives = movieRepository.getTopMovieList();
    }


//    LiveData<ArrayList<tile>> upComingMoives = movieRepository.getUpcomingMovieList();
//    LiveData<ArrayList<tile>> nowPlayingMoives = movieRepository.getNowPlayingMovieList();

    public LiveData<ArrayList<tile>> vgetTopRated(){
        Log.d("Test","In View Model Top");
        return movieRepository.getTopMovieList();
    }
    public LiveData<ArrayList<tile>> vgetUpComingRated(){
        Log.d("Test", "in View Model Up");

        return  movieRepository.getUpcomingMovieList();
    }
    public LiveData<ArrayList<tile>> vnowPlaying()
    {
        Log.d("Test", "in View Model Up");
        return  movieRepository.getNowPlayingMovieList();
    }


}
