package com.example.seekbarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SeekBar seekbar1,seekbar2,seekbar3;
    TextView textView,textView1,textView2,textView3;
    LinearLayout llayout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekbar1 = (SeekBar) findViewById(R.id.seek1);
        seekbar2 = (SeekBar) findViewById(R.id.seek2);
        seekbar3 = (SeekBar) findViewById(R.id.seek3);
        textView1=(TextView)findViewById(R.id.text1);
        textView=(TextView)findViewById(R.id.textView);
        textView2=(TextView)findViewById(R.id.text2);
        textView3=(TextView)findViewById(R.id.text3);


        seekbar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {

                textView.setText(""+progress);
                textView.setTextColor(Color.RED);
                textView1.setBackgroundColor(Color.RED);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(),"seekbar touch started1!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(),"seekbar touch stopped1!", Toast.LENGTH_SHORT).show();
            }


        });


        seekbar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                textView2.setText(""+progress);
                textView2.setTextColor(Color.GREEN);
                textView1.setBackgroundColor(Color.GREEN);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(),"seekbar touch started!2", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(),"seekbar touch stopped!2", Toast.LENGTH_SHORT).show();
            }


        });

        seekbar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                textView3.setText(""+progress);
                textView3.setTextColor(Color.BLUE);
                textView1.setBackgroundColor(Color.BLUE);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(),"seekbar touch started!3", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(),"seekbar touch stopped!3", Toast.LENGTH_SHORT).show();
            }


        });
    }
}
