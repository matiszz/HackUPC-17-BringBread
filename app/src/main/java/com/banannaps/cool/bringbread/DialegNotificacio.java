package com.banannaps.cool.bringbread;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by matias on 04/03/2017.
 */

public class DialegNotificacio extends AppCompatActivity implements View.OnClickListener {

    Button btnFet, btnSnooze;
    TextView tvNom, tvLoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_notification);

        btnFet = (Button) findViewById(R.id.btnFetDialog);
        btnSnooze = (Button) findViewById(R.id.btnSnoozeDialog);
        tvNom = (TextView) findViewById(R.id.tv_Dialog_Nom);
        tvLoc = (TextView) findViewById(R.id.tv_Dialog_Loc);

        btnFet.setOnClickListener(this);
        btnSnooze.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnFetDialog:
                finish();
                break;
            case R.id.btnSnoozeDialog:
                finish();
                break;
        }
    }
}
