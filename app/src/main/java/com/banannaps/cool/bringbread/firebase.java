package com.banannaps.cool.bringbread;

import java.io.DataOutputStream;
import 	java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import org.json.JSONObject;

import static android.content.ContentValues.TAG;

/**
 * Created by aleix on 3/3/2017.
 */

public class firebase extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        sendRegistrationToServer(refreshedToken);
    }
    public void sendRegistrationToServer(String token){
        AsyncT task = new AsyncT("http://hackupc2017.herokuapp.com/register" +
                "?token="+token);
        task.execute();
    }
}
