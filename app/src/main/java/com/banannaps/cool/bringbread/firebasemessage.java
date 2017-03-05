package com.banannaps.cool.bringbread;

import android.app.Service;
import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;
import static com.banannaps.cool.bringbread.AfegirTasca.llocsPredefs;
import static com.banannaps.cool.bringbread.Main.Llocs;
import static com.banannaps.cool.bringbread.Main.adaptador;

public class firebasemessage extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage message) {
        String tipus = message.getData().get("tipus");
        if (tipus.equals("novaTasca")) {
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
            intent.setAction("com.banannaps.cool.bringbread.Afegir");
            sendBroadcast(intent);

            double[] loc = {Double.parseDouble(sLat), Double.parseDouble(sLon)};
            Tasca tasca = new Tasca(Double.parseDouble(sLat), Double.parseDouble(sLon), nomMissatge, nomLloc);
            Main.pendents.add(tasca);
            if (Main.pendents.size() == 1) {
                startService(new Intent(this, BackgroundLocationService.class));
            }
        }
        else if(tipus.equals("borraTasca")){
            String loc = message.getData().get("loc");
            String msg = message.getData().get("msg");
            Intent intent = new Intent();
            intent.putExtra("loc", loc);
            intent.putExtra("msg", msg);

        }
    }
}
