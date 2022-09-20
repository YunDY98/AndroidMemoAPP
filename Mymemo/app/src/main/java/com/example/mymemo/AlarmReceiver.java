package com.example.mymemo;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {
    private  String channelId = "alarm_channel";

    @Override
    public void onReceive(Context context, Intent intent) {

        String msg = intent.getStringExtra("sendMessage");
        Intent newIntent = new Intent(context, MainActivity.class);
        newIntent.putExtra("from noti","from noti");
        PendingIntent pendingIntent = PendingIntent.getActivity(
                context,
                0,
                newIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setDefaults(Notification.DEFAULT_ALL)
                //.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setAutoCancel(true)
                .setContentTitle("메모앱")
                .setContentText(msg)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(channelId,
                    "채널 이름",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }
        else
            notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);

        Notification noti = notificationBuilder.build();

        int id = 20201078;   // 임의의 고유번호
        notificationManager.notify(id, noti);
    }
}

