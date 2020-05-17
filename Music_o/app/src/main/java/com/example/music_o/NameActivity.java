package com.example.music_o;

import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NameActivity extends AppCompatActivity {

    MediaPlayer mp = new MediaPlayer();
    int length;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        ImageView imageView = findViewById(R.id.ImageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),ActivityVideo.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mp = MediaPlayer.create(this, R.raw.hes_a_prites_orchestra);
        mp.setLooping((false));
        mp.seekTo(length);
        mp.start();

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mp.isPlaying()) {
            mp.pause();
            length = mp.getCurrentPosition();
            mp.release();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mp.isPlaying()) {
            mp.pause();
            length = mp.getCurrentPosition();
            mp.release();
        }
    }
}
