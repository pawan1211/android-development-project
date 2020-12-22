package com.example.notification1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static final int NOTIFICATION_ID = 1;

    Button btnotification;
    private static final String CHANNEL_ID = "channel_id01";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnotification = (Button) findViewById(R.id.notify);
        btnotification.setOnClickListener(new View.OnClickListener() {
                                              public void onClick(View v) {
                                                  showNotification();
                                              }
                                          }
        );
    }
private void showNotification() {
    createNotificationChannel();
    Intent intent = new Intent(MainActivity.this, MainActivity.class);

    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
    PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

    Intent intent1 = new Intent(MainActivity.this, Notify.class);

    intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
    PendingIntent dial_pad = PendingIntent.getActivity(this, 0, intent1, PendingIntent.FLAG_ONE_SHOT);
    String message = "this is a notification message";
    NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID);
    builder.setSmallIcon(R.drawable.ic_action_name);
    builder.setContentTitle("notify");
    builder.setContentText(message);

    builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

    builder.setAutoCancel(true);
    builder.setContentIntent(pendingIntent);
    builder.addAction(R.drawable.dial,"dial",dial_pad);
    NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
    notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());
}


    private void createNotificationChannel () {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                CharSequence name = "my notification";
                String description = "MY description";
                int importance = NotificationManager.IMPORTANCE_DEFAULT;
                NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, importance);
                notificationChannel.setDescription(description);
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.createNotificationChannel(notificationChannel);

            }
        }
    }