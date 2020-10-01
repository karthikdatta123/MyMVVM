package com.example.mymvvm;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mymvvm.Model.tile;

import androidx.fragment.app.FragmentTransaction;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// class for extending recycler view adapter

public class CommonAdapter extends RecyclerView.Adapter<CommonAdapter.SomeViewHolder> {
   // parceableExamp parceableExamp;

    // 6.1 - for creating constructor of AdapterRequired class
    private ArrayList<tile> mtiles;


    //6.2 constructor
    public CommonAdapter(ArrayList<tile> mtiles) {
        this.mtiles = mtiles;

    }
    // 1 class for view holder
    public static class SomeViewHolder extends RecyclerView.ViewHolder{
        // 4 getting ref / id of req view
//        public ImageView imageView;

        public ImageView imageView;  // todo
        TextView title, voteAvg, popularity, overView, movieid;


        // 2 constructor
        public SomeViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titlcv);
            voteAvg = itemView.findViewById(R.id.ratingcv);
            overView = itemView.findViewById(R.id.overviewcv);
//            movieid = itemView.findViewById((R.id.movieid));
            imageView = itemView.findViewById(R.id.commonimage);  // todo
        }
    }

    @NonNull
    @Override

    // this gives the layout on the screen as recycler starts
    public SomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("TEST at view holder", " Testtt");
        // 5
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cardcoomon, parent, false);
        SomeViewHolder someViewHolder = new SomeViewHolder(view);
//        view.setOnClickListener(onClickListener);
        return someViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final SomeViewHolder holder, int position) {
        // 7
        final tile currentItem = mtiles.get(position);

        holder.title.setText(currentItem.getText1());
        holder.voteAvg.setText(currentItem.getText3());
        holder.overView.setText(currentItem.getText5());

        Glide.with(holder.imageView.getContext()).load("https://image.tmdb.org/t/p/w500"+ currentItem.getText2()).into(holder.imageView);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //               Intent intent = new Intent(v.getContext(), detailActivity.class);
////                intent.putExtra("detail1", currentItem.getText2());
//                Bundle mBundle = new Bundle();
//                mBundle.putString("title", currentItem.getText1());
//                mBundle.putString("url", currentItem.getText2());
//                mBundle.putString("voteAvg", currentItem.getText3());
//                mBundle.putString("popularity", currentItem.getText4());
//                mBundle.putString("overView", currentItem.getText5());
//                intent.putExtras(mBundle);
//
//                v.getContext().startActivity(intent);
//TODO
//                parceableExamp = new parceableExamp(currentItem.getText1(), currentItem.getText2(), currentItem.getText3(), currentItem.getText4(), currentItem.getText5(), currentItem.getId());
//                Intent intent1 = new Intent(v.getContext(), detailActivity.class);
//                intent1.putExtra("Deta",parceableExamp);
//                v.getContext().startActivity(intent1);

            }
        };

        holder.itemView.setOnClickListener(onClickListener);

    }

    @Override
    public int getItemCount() {
        return mtiles.size();
    }
}
