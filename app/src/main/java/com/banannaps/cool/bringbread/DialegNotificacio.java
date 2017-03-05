package com.banannaps.cool.bringbread;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matias on 04/03/2017.
 */

public class DialegNotificacio extends AppCompatActivity implements View.OnClickListener {

    Button btnFet, btnSnooze;
    TextView tvNom;
    String loc, msg;
    boolean esborraDirecte = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_notification);


        btnFet = (Button) findViewById(R.id.btnFetDialog);
        btnSnooze = (Button) findViewById(R.id.btnSnoozeDialog);
        tvNom = (TextView) findViewById(R.id.tv_Dialog_Nom);

        btnFet.setOnClickListener(this);
        btnSnooze.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        loc = extras.getString("loc");
        msg = extras.getString("msg");
        esborraDirecte = extras.getBoolean("esb");

        if (esborraDirecte) esborra();

        tvNom.setText(msg + " a " + loc);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnFetDialog:
                esborra();
                finish();
                break;
            case R.id.btnSnoozeDialog:
                finish();
                break;
        }
    }

    private void esborra() {
        List<Tasca> aBorrar = new ArrayList<>();

        for(Tasca tasca : Main.pendents){
            if(tasca.getMessage().equals(msg) && tasca.getLocation().equals(loc))
                aBorrar.add(tasca);
        }

        for(Tasca tasca : aBorrar) Main.pendents.remove(tasca);

        Intent intent = new Intent();
        intent.setAction("com.banannaps.cool.bringbreag.Update");
        sendBroadcast(intent);
        AsyncT asyncT = new AsyncT("http://hackupc2017.herokuapp.com/delete?loc="+loc.replace(" ", "%20")+"&msg="+msg.replace(" ", "%20"));
        asyncT.execute();
    }

}
