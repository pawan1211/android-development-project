package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.time.Instant;

public class MainActivity extends AppCompatActivity {

    Button btnotification;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnotification = (Button) findViewById(R.id.notify);
        btnotification.setOnClickListener(new View.OnClickListener() {


            private static final String CHANNEL_ID = "channel_id01";

            @Override
            public void onClick(View v) {
                String message = "this is a notification message";
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this,CHANNEL_ID);
                       builder.setSmallIcon(R.drawable.ic_assignment_black_24dp);
                builder.setContentTitle("notify");
                builder.setContentText(message);
                builder.setAutoCancel(true);
                Intent intent = new Intent(MainActivity.this, NotificationActivity.class);

                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("message", message);
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(0, builder.build());
            }
        });
    }
}