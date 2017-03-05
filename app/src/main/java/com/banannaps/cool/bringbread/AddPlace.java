package com.banannaps.cool.bringbread;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.banannaps.cool.bringbread.Main.Llocs;

/**
 * Created by matias on 05/03/2017.
 */

public class AddPlace extends AppCompatActivity implements View.OnClickListener {
    Button btnOk, btnCancel;
    EditText etNom, etLat, etLon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_place);

        btnCancel = (Button) findViewById(R.id.btnCancelPlace);
        btnOk = (Button) findViewById(R.id.btnOkPlace);
        etLat = (EditText) findViewById(R.id.et_latitud);
        etLon = (EditText) findViewById(R.id.et_longitud);
        etNom = (EditText) findViewById(R.id.et_locName);

        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOkPlace:
                String localitzacio = etNom.getText().toString();
                double latitud = Double.parseDouble(etLat.getText().toString());
                double longitud = Double.parseDouble(etLon.getText().toString());
                Llocs.put(localitzacio, new double[] {latitud, longitud});

                finish();
                break;
            case R.id.btnCancelPlace:
                finish();
                break;
        }
    }
}
