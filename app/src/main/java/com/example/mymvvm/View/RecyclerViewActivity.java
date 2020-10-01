package com.example.mymvvm.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.example.mymvvm.CommonAdapter;
import com.example.mymvvm.Model.tile;
import com.example.mymvvm.MovieViewModel.MovieViewModel;
import com.example.mymvvm.R;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity {
    ArrayList<tile> tiles = new ArrayList<>();
    RecyclerView commonrv;
    RecyclerView.LayoutManager commonlm;
    RecyclerView.Adapter commonadap;
    MovieViewModel movieViewModel;
    LiveData<ArrayList<tile>> liveData;
    ArrayList<tile> mtiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        int page = getIntent().getIntExtra("type", 0);

        if(page == 1){
            liveData = movieViewModel.vnowPlaying();

            liveData.observe(this, new Observer<ArrayList<tile>>() {
                @Override
                public void onChanged(ArrayList<tile> tiles) {
                    mtiles = tiles;
                }
            });
            Log.d("TEST at page 1", String.valueOf(page));
        }

        if (page == 2){
            liveData = movieViewModel.vgetUpComingRated();

            liveData.observe(this, new Observer<ArrayList<tile>>() {
                @Override
                public void onChanged(ArrayList<tile> tiles) {
                    mtiles = tiles;
                }
            });
            Log.d("TEST at page 2", String.valueOf(page));
        }

        commonrv = findViewById(R.id.commonrv);
        commonrv.setHasFixedSize(true);
        commonlm = new LinearLayoutManager(this);
        commonadap = new CommonAdapter(mtiles);
        commonrv.setLayoutManager(commonlm);
        commonrv.setAdapter(commonadap);


    }
}