package com.example.mymvvm.Model;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class tile {

    String text1;
    String text2;
    String text3;
    String text4;
    String text5;
    int id;
    //  int imageView;



    public tile(String text1, String text2, String text3, String text4, String text5, int id) {
        this.text1 = text1;
        this.text2 = text2;
        this.text3 = text3;
        this.text4 = text4;
        this.text5 = text5;
        this.id = id;
        Log.d("In tile", "At Constructor");
    }

    public String getText3() {
        return text3;
    }

    public String getText4() {
        return text4;
    }

    public String getText5() {
        return text5;
    }

    public String getText1() {
        return text1;
    }

    public String getText2() {
        return text2;
    }

    public String getId(){return String.valueOf(id);}
//
//    public int getImageView() {
//        return imageView;
//    }
}
