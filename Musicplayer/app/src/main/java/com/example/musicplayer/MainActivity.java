package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    int i = -1;
    Button pause, play, forward,backward,stop;
    MediaPlayer mediaplayer;
    int pausecurrentposition;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play = (Button) findViewById(R.id.play);
        pause = (Button) findViewById(R.id.pause);
        stop=(Button)findViewById(R.id.stop);
        forward = (Button) findViewById(R.id.forward);
        backward = (Button) findViewById(R.id.back);
        play.setOnClickListener(this);
        pause.setOnClickListener(this);
         stop.setOnClickListener(this);
        forward.setOnClickListener(this);
        backward.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play:
                if (mediaplayer == null) {
                    mediaplayer = MediaPlayer.create(getApplicationContext(), R.raw.music2);
                    mediaplayer.start();
                } else if (!mediaplayer.isPlaying()) {
                    mediaplayer.seekTo(pausecurrentposition);

                    if (i == 1) {
                        mediaplayer = MediaPlayer.create(getApplicationContext(), R.raw.music3);
                        mediaplayer.start();
                    }
                 else if (i == 0) {
                    mediaplayer = MediaPlayer.create(getApplicationContext(), R.raw.music2);

                    mediaplayer.start();
                }
                    else if (i == 2) {
                        mediaplayer = MediaPlayer.create(getApplicationContext(), R.raw.music4);
                        mediaplayer.start();
                    }

                }
                break;
            case R.id.pause:
                if (mediaplayer != null) {

                    mediaplayer.pause();
                    pausecurrentposition = mediaplayer.getCurrentPosition();
                }

                break;
            case R.id.stop:
                if(mediaplayer!=null) {

                    mediaplayer.stop();
                    mediaplayer=null;
                }
                break;

            case R.id.forward:
                i++;
                mediaplayer.stop();
                    if (i == 1) {

                        mediaplayer = MediaPlayer.create(getApplicationContext(), R.raw.music3);
                        mediaplayer.start();
                    } else if (i == 2) {
                        mediaplayer = MediaPlayer.create(getApplicationContext(), R.raw.music4);

                        mediaplayer.start();
                    }
                    break;
            case R.id.back:
                i--;
                mediaplayer.stop();
                if (i == 1) {
                    mediaplayer = MediaPlayer.create(getApplicationContext(), R.raw.music3);
                    mediaplayer.start();
                }
         else if (i == 0) {
            mediaplayer = MediaPlayer.create(getApplicationContext(), R.raw.music2);

            mediaplayer.start();
        }

                else if (i == 2) {
                    mediaplayer = MediaPlayer.create(getApplicationContext(), R.raw.music4);

                    mediaplayer.start();
                }
                break;


        }
    }
}

