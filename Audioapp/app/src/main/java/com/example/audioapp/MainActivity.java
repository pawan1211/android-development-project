package com.example.audioapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.wifi.aware.DiscoverySession;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity<SongsManager> extends AppCompatActivity {

ImageView play,prev,next,imageView;
TextView textView;
SeekBar mseekbartime,mseekbarvol;
static MediaPlayer mediaPlayer;
private Runnable runnable;
private AudioManager audioManager;
int currentindex=0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play=(ImageView)findViewById(R.id.play);
        prev=(ImageView)findViewById(R.id.prev);
        next=(ImageView)findViewById(R.id.next);
        imageView=(ImageView)findViewById(R.id.imageview);

        mseekbartime=(SeekBar)findViewById(R.id.seek);
        mseekbarvol=(SeekBar)findViewById(R.id.seek1);

        textView=(TextView)findViewById(R.id.songtitle);

        audioManager=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<Integer>song=new ArrayList<>();
        song.add(0,R.raw.p1);
        song.add(1,R.raw.p1);
        int max=audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curv=audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        mseekbarvol.setMax(max);
        mseekbarvol.setProgress(curv);
        mseekbarvol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

play.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        mseekbartime.setMax(mediaPlayer.getDuration());
        if(mediaPlayer!=null&&mediaPlayer.isPlaying())
        {
            mediaPlayer.pause();
            play.setImageResource(R.drawable.play_audio_image);
        }
        else
        {
            mediaPlayer.start();
            play.setImageResource(R.drawable.pause_audio_image);
        }
        songDetails();
    }


});
next.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if (mediaPlayer != null) {
            play.setImageResource(R.drawable.music1);

        }
        if (currentindex < song.size() - 1) {
            currentindex++;
        } else {
            currentindex=0;

        }
        if(mediaPlayer.isPlaying())
        {
            mediaPlayer.stop();
        }
        mediaPlayer=MediaPlayer.create(getApplicationContext(),song.get(currentindex));
        mediaPlayer.start();
        songDetails();
    }

});

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null) {
                    play.setImageResource(R.drawable.pause_audio_image);

                }
                if (currentindex < song.size() - 1) {
                    currentindex--;
                } else {
                    currentindex=song.size() - 1;

                }
                if(mediaPlayer.isPlaying())
                {
                    mediaPlayer.stop();
                }
                mediaPlayer=MediaPlayer.create(getApplicationContext(),song.get(currentindex));
                mediaPlayer.start();
                songDetails();
            }

        });

        mseekbartime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser)
                {
                    mediaPlayer.seekTo(progress);
                    mseekbartime.setProgress(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    private void songDetails() {
        if(currentindex==0)
        {
            textView.setText("paw");
imageView.setImageResource(R.drawable.music1);
        }
        if(currentindex==1)
        {
            textView.setText("paw1");
            imageView.setImageResource(R.drawable.music2);
        }

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {


                mseekbartime.setMax(mediaPlayer.getDuration());
                mediaPlayer.start();
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(mediaPlayer!=null)
                {
                    try {
                        if(mediaPlayer.isPlaying())
                        {
                            Message message=new Message();
                            message.what=mediaPlayer.getCurrentPosition();

                            handler.sendMessage(message);
                            Thread.sleep(1000);
                        }
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    Handler handler=new Handler()
    {
        public void handleMessage(Message message)
        {
            mseekbartime.setProgress(message.what);
        }
    };
}
