package com.example.staywithme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    ImageView img;
    SensorManager sm;
    Sensor sensor_proximity;
    Animation ani;
    Vibrator mVibe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img =(ImageView)findViewById(R.id.sad);
        sm =(SensorManager)getSystemService(SENSOR_SERVICE);
        sensor_proximity = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        ani = AnimationUtils.loadAnimation(this,R.anim.shaking);
        img.startAnimation(ani);
        mVibe =(Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        sm.registerListener(this,sensor_proximity,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();

        sm.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType()==Sensor.TYPE_PROXIMITY){
            if (event.values[0]<=6){
                img.setImageResource(R.drawable.smile);
                img.clearAnimation();
                mVibe.cancel();
            }else{
                img.setImageResource(R.drawable.sad);
                img.startAnimation(ani);
                mVibe.vibrate(new long[]{1000,2000,500},0);
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVibe.cancel();
    }
}
