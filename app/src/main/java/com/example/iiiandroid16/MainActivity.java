package com.example.iiiandroid16;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaRecorder;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private AudioManager audioManager;
    private SoundPool soundPool;
    private int s1, s2;
    private File sdroot, musicDir;

    private MediaRecorder recorder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECORD_AUDIO,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE},
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
        sdroot = Environment.getExternalStorageDirectory();
        musicDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
        Log.v("brad", musicDir.getAbsolutePath());
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


    public void test4(View view) {
//        Intent intent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(sdroot, "bradiii.amr")));
//        startActivityForResult(intent, 123);

        try {
            recorder = new MediaRecorder();
            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
            //recorder.setOutputFile(new File(sdroot, "bradiii.3gp"));
            recorder.setOutputFile(new File(sdroot, "bradiii.3gp").getAbsolutePath());

            recorder.prepare();
            recorder.start();

        }catch (Exception e){

        }
    }

    public void test5(View view){
        if (recorder != null){
            recorder.stop();
            recorder.reset();
            recorder.release();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.v("brad", "OK");
    }
}
