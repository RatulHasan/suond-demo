package com.besofty.suonddemo;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mplayer;
    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mplayer = MediaPlayer.create(this, R.raw.ahare_mon);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        SeekBar volumeBar = (SeekBar) findViewById(R.id.volumeBar);
        volumeBar.setMax(maxVolume);
        volumeBar.setProgress(curVolume);

        volumeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        final SeekBar scrubber = (SeekBar) findViewById(R.id.scrubber);
        scrubber.setMax(mplayer.getDuration());

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                scrubber.setProgress(mplayer.getCurrentPosition());
            }
        }, 0, 1000);

        scrubber.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //mplayer.seekTo(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void playButton(View view){
        mplayer.start();

        ImageView playButton = (ImageView) findViewById(R.id.playButton);
        playButton.setVisibility(View.INVISIBLE);

        ImageView pauseButton = (ImageView) findViewById(R.id.pauseButton);
        pauseButton.setVisibility(View.VISIBLE);

    }

    public void pauseButton(View view){
        mplayer.pause();

        ImageView pauseButton = (ImageView) findViewById(R.id.pauseButton);
        pauseButton.setVisibility(View.INVISIBLE);

        ImageView playButton = (ImageView) findViewById(R.id.playButton);
        playButton.setVisibility(View.VISIBLE);

    }

    public void stopButton(View view){
        mplayer.stop();
        mplayer = MediaPlayer.create(this, R.raw.ahare_mon);

        ImageView pauseButton = (ImageView) findViewById(R.id.pauseButton);
        pauseButton.setVisibility(View.INVISIBLE);

        ImageView playButton = (ImageView) findViewById(R.id.playButton);
        playButton.setVisibility(View.VISIBLE);

    }
}
