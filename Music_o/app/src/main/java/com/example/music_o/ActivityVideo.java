package com.example.music_o;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityVideo extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        VideoView videoView = (VideoView) findViewById(R.id.videoview);
        videoView.setVideoPath("android.resource://"+ getPackageName() + "/" + R.raw.hes_a_prites);
        MediaController MyController = new MediaController(this);
        MyController.setAnchorView(videoView);
        videoView.setMediaController(MyController);
        videoView.start();



    }
}
