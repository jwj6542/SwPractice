package com.example.recyclerview;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Movie> list = new ArrayList<>();
        list.add(new Movie(getDrawable(R.drawable.red),"색깔1"));
        list.add(new Movie(getDrawable(R.drawable.orange),"색깔2"));
        list.add(new Movie(getDrawable(R.drawable.pink),"색깔3"));
        list.add(new Movie(getDrawable(R.drawable.yellow),"색깔4"));
        list.add(new Movie(getDrawable(R.drawable.green),"색깔5"));
        list.add(new Movie(getDrawable(R.drawable.blue),"색깔6"));
        list.add(new Movie(getDrawable(R.drawable.namseck),"색깔7"));
        list.add(new Movie(getDrawable(R.drawable.parple),"색깔8"));
        list.add(new Movie(getDrawable(R.drawable.brown),"색깔9"));
        list.add(new Movie(getDrawable(R.drawable.rainbow),"색깔10"));

        RecyclerView recyclerView = findViewById(R.id.recycler1);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        MyAdapter adapter = new MyAdapter(list);
        recyclerView.setAdapter(adapter);
    }
}
