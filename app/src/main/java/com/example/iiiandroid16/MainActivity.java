package com.example.iiiandroid16;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private AudioManager audioManager;
    private SoundPool soundPool;
    private int s1, s2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECORD_AUDIO},
                    1);
        }else{
            init();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        init();
    }

    private void init(){
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        soundPool = new SoundPool(10,AudioManager.STREAM_MUSIC, 5);
        s1 = soundPool.load(this, R.raw.s1, 1);
        s2 = soundPool.load(this, R.raw.s2, 1);
    }


    public void test1(View view) {
        audioManager.playSoundEffect(AudioManager.FX_KEYPRESS_INVALID);
    }

    public void test2(View view) {
        soundPool.play(s1, 1f, 1f,1,0,1);
    }
    public void test3(View view) {
        soundPool.play(s2, 1f, 1f,1,0,1);
    }


}
