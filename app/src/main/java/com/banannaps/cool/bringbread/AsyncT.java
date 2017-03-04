package com.banannaps.cool.bringbread;

import android.icu.util.Output;
import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by aleix on 4/3/2017.
 */

public class AsyncT extends AsyncTask<Void, Void, Void> {
    String token;

    public AsyncT(String token){
        super();
        this.token = token;
    }
    @Override
    protected Void doInBackground(Void... params){
        try {
            URL url = new URL("http://hackupc2017.herokuapp.com/register?token="+token);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
// here you are setting the `Content-Type` for the data you are sending which is `application/json`
            httpURLConnection.connect();
            httpURLConnection.getInputStream();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("token", token);
            wait(10);
            DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
            wr.writeBytes(jsonObject.toString());
            wr.flush();
            wr.close();

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
