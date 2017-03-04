package com.banannaps.cool.bringbread;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import static android.app.Notification.PRIORITY_MAX;

public class firebasemessage extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage message){


    }
}
