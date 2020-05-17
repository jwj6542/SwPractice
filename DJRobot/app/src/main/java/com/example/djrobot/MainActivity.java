package com.example.djrobot;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Locale;

import static android.speech.tts.TextToSpeech.ERROR;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout cl;
    ImageView iv;
    private TextToSpeech tts;
    Intent intent;
    SpeechRecognizer mRecognizer;
    MediaPlayer mp;
    TextView tvMusicTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cl = (ConstraintLayout) findViewById(R.id.cl);
        iv = (ImageView) findViewById(R.id.iv);

        tvMusicTitle = findViewById(R.id.musictitle);

        mp = new MediaPlayer();

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != ERROR) {
                    tts.setLanguage(Locale.KOREAN);
                }
            }
        });
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 1);

        }
        intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-kr");

        mRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        mRecognizer.setRecognitionListener(recognitionListener);

        AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int amStreamMusicMaxVol = am.getStreamMaxVolume(am.STREAM_MUSIC);
        am.setStreamVolume(am.STREAM_MUSIC, amStreamMusicMaxVol, 0);
    }

    private RecognitionListener recognitionListener = new RecognitionListener() {
        @Override
        public void onReadyForSpeech(Bundle params) {

        }

        @Override
        public void onBeginningOfSpeech() {

        }

        @Override
        public void onRmsChanged(float rmsdB) {

        }

        @Override
        public void onBufferReceived(byte[] buffer) {

        }

        @Override
        public void onEndOfSpeech() {

        }

        @Override
        public void onError(int error) {

        }

        @Override
        public void onResults(Bundle bundle) {
            ArrayList<String> mResult = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
            String[] rs = new String[mResult.size()];
            mResult.toArray(rs);

            Log.d("test", "실행 :" + rs[0]);
            tvMusicTitle.setText(rs[0]);

            if (rs[0].equals("탑차이")) {
                Log.d("test", "실행됨");
                iv.setImageResource(R.drawable.topgap);
                mp = MediaPlayer.create(MainActivity.this, R.raw.top);
                mp.start();
            } else if (rs[0].equals("정글차이")) {
                iv.setImageResource(R.drawable.junglegap);
                mp = MediaPlayer.create(MainActivity.this, R.raw.jungle);
                mp.start();
            } else if (rs[0].equals("미드 차이")) {
                iv.setImageResource(R.drawable.midgap);
                mp = MediaPlayer.create(MainActivity.this, R.raw.jungle);
                mp.start();
            } else if (rs[0].equals("지")) {
                tts.speak("네 주인님 저는 찌 입니다", TextToSpeech.QUEUE_FLUSH, null);
            }

        }

        @Override
        public void onPartialResults(Bundle partialResults) {

        }

        @Override
        public void onEvent(int eventType, Bundle params) {

        }
    };

    public void recognizeVoice(View v) {
        if (mp.isPlaying()) {
            mp.stop();
            mp.release();
        }
        tts.speak("어떤 노래를 들려드릴까요?", TextToSpeech.QUEUE_FLUSH, null);
        mRecognizer.startListening(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (tts != null) {
            tts.stop();
            tts.shutdown();
            tts = null;
        }
        if (mp.isPlaying()) {
            mp.stop();
            mp.release();
        }
    }
}