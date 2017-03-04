package com.banannaps.cool.bringbread;

import android.util.Log;
import android.widget.Toast;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class firebasemessage extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage message){

        Toast.makeText(this, message.toString(), Toast.LENGTH_SHORT).show();
    }
}
