package com.example.picturesapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ViewFlipper viewFlipper;
    Button next,previous,start;
    Switch my_switch;
    int flipping=0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewFlipper = (ViewFlipper)findViewById(R.id.viewFlipper);
        next = (Button) findViewById(R.id.next);
        previous = (Button) findViewById(R.id.previous);
        start = (Button) findViewById(R.id.b1);
        next.setOnClickListener(this);
        previous.setOnClickListener(this);
        start.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v == next) {
            viewFlipper.showNext();
        }
        else if (v == previous) {
            viewFlipper.showPrevious();
        }
        else
        {
            if(flipping==0)
            {
                viewFlipper.startFlipping();
                flipping=1;
                start.setText(R.string.stop);

            }
            else
            {
                viewFlipper.stopFlipping();
                flipping=0;
                start.setText("stop");

            }
        }
    }





}
