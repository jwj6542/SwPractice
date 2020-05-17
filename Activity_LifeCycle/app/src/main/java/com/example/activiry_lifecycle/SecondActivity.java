package com.example.activiry_lifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.nio.channels.InterruptedByTimeoutException;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
       Log.d("액티비티 생명주기","onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("액티비티 생명주기","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("액티비티 생명주기","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("액티비티 생명주기","onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("액티비티 생명주기","onDestroy");
    }
}
