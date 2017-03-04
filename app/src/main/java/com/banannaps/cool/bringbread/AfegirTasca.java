package com.banannaps.cool.bringbread;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import static com.banannaps.cool.bringbread.Main.Llocs;

public class AfegirTasca extends AppCompatActivity implements View.OnClickListener{

    Button btnOk, btnCancel;
    EditText etNom;
    Spinner sPlaces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_afegir);

        btnOk = (Button) findViewById(R.id.btnOkDialog);
        btnCancel = (Button) findViewById(R.id.btnCancelDialog);
        etNom = (EditText) findViewById(R.id.et_nomTasca);
        sPlaces = (Spinner) findViewById(R.id.sp_Places);

        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        setSpinnerPlaces();
    }

    private void setSpinnerPlaces() {
        final String[] llocsPredefs = new String[] { "Supermercat", "Panaderia", "Mercat" };
        Llocs.put("Supermercat", new double[] {41.387657, 2.114799});
        Llocs.put("Panaderia", new double[] {41.393678, 2.112148});
        Llocs.put("Mercat", new double[] {41.383895, 2.129782});

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, llocsPredefs);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sPlaces.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOkDialog:

                if (!etNom.getText().toString().equals("")){
                    String nom = etNom.getText().toString();
                    String lloc= sPlaces.getSelectedItem().toString();
                    double lat = Llocs.get(lloc)[0];
                    double lon = Llocs.get(lloc)[1];

                    Toast.makeText(this, ""+nom+", a "+lloc+" | LAT = "+lat+"; LON = "+lon+"", Toast.LENGTH_LONG).show();

                    //TODO: Fer el push de les dades aqui
                    AsyncT async = new AsyncT("http://hackupc2017.herokuapp.com/afegir?lat="+Double.toString(lat)+"&lon="+Double.toString(lon)+"&nom="+nom);
                    async.execute();

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
