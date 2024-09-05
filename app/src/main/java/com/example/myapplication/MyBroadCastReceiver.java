package com.example.myapplication;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MyBroadCastReceiver extends BroadcastReceiver {
    private static final int NOTIFICATION_ID =0;

    private static MediaPlayer mediaPlayer;
        @Override
        public void onReceive(Context context, Intent intent) {

            if(mediaPlayer!=null){
                mediaPlayer.stop();
                mediaPlayer.release();
            }
            Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM );
            mediaPlayer=MediaPlayer.create(context,uri);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
            String classname = intent.getStringExtra("ClassName");
            Toast.makeText(context, " "+classname, Toast. LENGTH_SHORT ).show();
            Intent dismissintent = new Intent(context,DismissReceiver.class);
            PendingIntent pendingDismissIntent =
                    PendingIntent.getBroadcast(context,0,dismissintent,PendingIntent.FLAG_UPDATE_CURRENT );
            NotificationCompat.Action dismissAction =
                    new NotificationCompat.Action.Builder(1,"Off the Alarm",pendingDismissIntent).build();
                    NotificationCompat.Builder notificationBuilder =
                            new NotificationCompat.Builder(context,"default")
                                    .setSmallIcon(R.drawable. ic_launcher_foreground ).
                                    setContentTitle("ClassTime").setContentText(classname)
                                    .addAction(dismissAction).setAutoCancel(true);
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.notify( NOTIFICATION_ID ,notificationBuilder.build());
        }
        public static class DismissReceiver extends BroadcastReceiver{
            @Override
            public void onReceive(Context context, Intent intent) {
                mediaPlayer.stop();
                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
                notificationManagerCompat.cancel( NOTIFICATION_ID );
                Log.d("DismissReceiver","Notification Dismissed");
            }
        }
    }