package com.banannaps.cool.bringbread;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static java.lang.Integer.parseInt;

public class AfegirTasca extends AppCompatActivity implements View.OnClickListener{

    Button btnOk, btnCancel;
    EditText etNom, etLat, etLon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_afegir);

        btnOk = (Button) findViewById(R.id.btnOkDialog);
        btnCancel = (Button) findViewById(R.id.btnCancelDialog);
        etNom = (EditText) findViewById(R.id.et_nomTasca);
        etLat = (EditText) findViewById(R.id.et_latitud);
        etLon = (EditText) findViewById(R.id.et_longitud);

        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOkDialog:

                if (    !etNom.getText().toString().equals("") &&
                        !etLat.getText().toString().equals("") &&
                        !etLon.getText().toString().equals("")) {

                    String nom = etNom.getText().toString();
                    int lat = Integer.parseInt(etLat.getText().toString());
                    int lon = Integer.parseInt(etLon.getText().toString());

                    // Fer el push de les dades aqui
                    finish();
                } else {
                    Toast.makeText(getBaseContext(), "Hi ha camps en blanc", Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.btnCancelDialog:
                finish();
                break;
        }
    }
}
