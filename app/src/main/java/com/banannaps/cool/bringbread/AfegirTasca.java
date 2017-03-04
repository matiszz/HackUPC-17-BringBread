package com.banannaps.cool.bringbread;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class AfegirTasca extends AppCompatActivity {

    Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_afegir);

         btnOk = (Button) findViewById(R.id.btnOkDialog);
    }
}
