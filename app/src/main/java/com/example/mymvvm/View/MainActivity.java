package com.example.mymvvm.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.mymvvm.AdapterRequired;
import com.example.mymvvm.Model.tile;
import com.example.mymvvm.MovieViewModel.MovieViewModel;
import com.example.mymvvm.Network.APICall;
import com.example.mymvvm.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    MovieViewModel movieViewModel;
    LiveData<ArrayList<tile>> liveData;
    ArrayList<tile> mtiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        APICall.getInstance(getApplicationContext());
        Log.d("Test", "in n MainActivity");

        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        liveData = movieViewModel.vgetTopRated();
        liveData.observe(this, new Observer<ArrayList<tile>>() {
            @Override
            public void onChanged(ArrayList<tile> tiles) {
                mtiles = tiles;
                Log.d("Test", "in onChange");
            }
        });
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, true);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdapterRequired(mtiles);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    public void upcoming(View view){
        Intent intent = new Intent(MainActivity.this, RecyclerViewActivity.class);
        intent.putExtra("type", 2);
        startActivity(intent);
    }
    public void nowplaying(View view){
        Intent intent = new Intent(MainActivity.this, RecyclerViewActivity.class);
        intent.putExtra("type", 1);
        startActivity(intent);
    }
}