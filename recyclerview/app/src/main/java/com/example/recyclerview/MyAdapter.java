package com.example.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<Movie> mMovieData = null;

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_item);
            textView = itemView.findViewById(R.id.tv_item);
        }
    }

    MyAdapter(ArrayList<Movie>movieList){
        mMovieData = movieList;
    }


    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.recyclerview_item, parent, false) ;
        MyAdapter.ViewHolder vh = new MyAdapter.ViewHolder(view) ;

        return vh;
    }

    @Override
        public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        holder.imageView.setImageDrawable(mMovieData.get(position).drawable);
        String text = mMovieData.get(position).text;
        holder.textView.setText(text);

    }

    @Override
    public int getItemCount() {
        return mMovieData.size();
    }
}
