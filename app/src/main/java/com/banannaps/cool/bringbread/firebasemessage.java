package com.banannaps.cool.bringbread;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import static com.banannaps.cool.bringbread.Main.locTasques;
import static com.banannaps.cool.bringbread.Main.nomTasques;


public class firebasemessage extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage message){
        String nomMissatge = message.getData().get("nom");
        String sLat = message.getData().get("lat");
        String sLon = message.getData().get("lon");

        double lat = Double.parseDouble(sLat);
        double lon = Double.parseDouble(sLon);

        nomTasques.add(nomMissatge);
        locTasques.add(sLat+", "+sLon);

    }
}
