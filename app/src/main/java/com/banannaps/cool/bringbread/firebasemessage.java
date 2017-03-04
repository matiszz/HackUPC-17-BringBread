package com.banannaps.cool.bringbread;

import android.app.Service;
import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.ArrayList;

import static com.banannaps.cool.bringbread.AfegirTasca.llocsPredefs;
import static com.banannaps.cool.bringbread.Main.Llocs;
import static com.banannaps.cool.bringbread.Main.adaptador;
import static com.banannaps.cool.bringbread.Main.locTasques;
import static com.banannaps.cool.bringbread.Main.nomTasques;

public class firebasemessage extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage message){
        String nomMissatge = message.getData().get("nom");

        Log.d("Missatge rebut:", nomMissatge);

        String sLat = message.getData().get("lat");
        String sLon = message.getData().get("lon");
        String nomLloc = message.getData().get("loc");

        Intent intent = new Intent();
        intent.putExtra("nom", nomMissatge);
        intent.putExtra("lat", sLat);
        intent.putExtra("lon", sLon);
        intent.putExtra("loc", nomLloc);
        intent.setAction("com.banannaps.cool.bringbread.onMessageRecieved");
        sendBroadcast(intent);

        double[] loc = {Double.parseDouble(sLat), Double.parseDouble(sLon)};
//        if(Main.pendents.isEmpty()){

            Main.pendents.put(nomLloc, loc);
            startService(new Intent(this, BackgroundLocationService.class));
//        }
    }
}
