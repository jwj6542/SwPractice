package com.example.samplelockscreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.lockscreenactivity.R;

public class MainActivity extends Activity {
    private Button onBtn, offBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onBtn= findViewById(R.id.button);
        offBtn= findViewById(R.id.button2);
        onBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.example.samplelockscreen.ScreenService.class);
                startService(intent);
            }

        });
        offBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.example.samplelockscreen.ScreenService.class);
                stopService(intent);
            }
        });
    }
}