package com.banannaps.cool.bringbread;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import static android.app.Notification.PRIORITY_MAX;

/**
 * Created by matias on 04/03/2017.
 */

public class Notificacio extends AppCompatActivity {

    public static String ACTION_FET = "Main.this";

    public void rebreMissatge(String nom, String lloc) {
        // Set up notification
        android.support.v4.app.NotificationCompat.Builder notifAprop = new android.support.v4.app.NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.check)
                //.setLargeIcon(R.drawable.bread)
                .setContentTitle(nom)
                .setContentText("A prop de "+lloc+"\n Fes swipe per descartar la tasca")
                .setAutoCancel(true)
                .setPriority(PRIORITY_MAX);

        // intentClicat = Quan es clica. constPila = Acció quan torna.
        Intent intentClicat = new Intent(this, DialegNotificacio.class);
        android.support.v4.app.TaskStackBuilder constPila = android.support.v4.app.TaskStackBuilder.create(this);
        constPila.addParentStack(Main.class);
        constPila.addNextIntent(intentClicat);
        PendingIntent resultPendingIntent = constPila.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        notifAprop.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //Intent per "Fet"
        Intent fetIntent = new Intent();
        fetIntent.setAction(ACTION_FET);
        PendingIntent pendingIntentFet = PendingIntent.getBroadcast(this, 12345, fetIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        notifAprop.addAction(R.drawable.check, "Fet", pendingIntentFet);

        mNotificationManager.notify((int) System.currentTimeMillis(), notifAprop.build());
    }
}
