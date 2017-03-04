package com.banannaps.cool.bringbread;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.ArrayList;

import static com.banannaps.cool.bringbread.AfegirTasca.llocsPredefs;
import static com.banannaps.cool.bringbread.Main.Llocs;
import static com.banannaps.cool.bringbread.Main.locTasques;
import static com.banannaps.cool.bringbread.Main.nomTasques;


public class firebasemessage extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage message){
        String nomMissatge = message.getData().get("nom");

        Log.d("Missatge rebut:", nomMissatge);

        String sLat = message.getData().get("lat");
        String sLon = message.getData().get("lon");
//        TODO: String nomLloc = message.getData().get("loc");

//        double lat = Double.parseDouble(sLat);
//        double lon = Double.parseDouble(sLon);

        nomTasques.add(nomMissatge);
        locTasques.add(sLat);//(sLat+", "+sLon);

//        TODO: locTasques.add(nomLloc);
//        TODO: Llocs.put(nomLloc, new double[] {lat, non});
//        TODO: llocsPredefs = new ArrayList();
//        TODO: if(llocsPredefs.contains(nomLloc)) llocsPredefs.add(nomLloc);

    }
}
