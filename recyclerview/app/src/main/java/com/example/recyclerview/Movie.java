package com.example.recyclerview;

import android.graphics.drawable.Drawable;
import android.widget.TextView;

public class Movie {
    Drawable drawable;
    String text;

    public Movie(Drawable drawable, String text){
        this.drawable = drawable;
        this.text = text;
    }
}
