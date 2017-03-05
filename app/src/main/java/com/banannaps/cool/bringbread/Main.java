package com.banannaps.cool.bringbread;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.app.Notification.PRIORITY_MAX;
import static com.banannaps.cool.bringbread.AfegirTasca.llocsPredefs;

public class Main extends AppCompatActivity {

    ListView lv_tasques;
    FloatingActionButton btn_afegir;
    static ArrayAdapter adaptador;

    // El map Llocs com a clau el Nom del lloc, i un Vector de 2 doubles amb latitud i longitud.
    public static ArrayMap<String, double[]> Llocs = new ArrayMap<>();
    public static List<Tasca> pendents = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setBtnAfegir();
        setListView();

//        startService(new Intent(this, BackgroundLocationService.class));
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.banannaps.cool.bringbread.Afegir");
        intentFilter.addAction("com.banannaps.cool.bringbread.Notificacio");
        intentFilter.addAction("com.banannaps.cool.bringbread.Update");
        intentFilter.addAction("com.banannaps.cool.bringbread.Esborra");
        MyBroadcastReceiver receiver = new MyBroadcastReceiver();
        registerReceiver(receiver, intentFilter);
    }

    private void setBtnAfegir() {
        btn_afegir = (FloatingActionButton) findViewById(R.id.btnAfegir);
        btn_afegir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main.this, AfegirTasca.class);
                startActivity(i);
            }
        });
    }

    public void setListView() {
        lv_tasques = (ListView) findViewById(R.id.lv_tasques);

        adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_2, android.R.id.text1, pendents) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView nomTasca = (TextView) view.findViewById(android.R.id.text1);
                TextView locTasca = (TextView) view.findViewById(android.R.id.text2);

                nomTasca.setText(pendents.get(position).getMessage());
                locTasca.setText(pendents.get(position).getLocation());
                return view;
            }
        };

        lv_tasques.setAdapter(adaptador);

        /* lv_tasques.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int tascaClicada = position;
                String itemValue = (String) lv_tasques.getItemAtPosition(position);
            }
        }); */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.btnDoSomething) {
            //rebreMissatge("Comprar pa", "Cafeteria");
            Intent intent = new Intent(this, AddPlace.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static String ACTION_FET = "Main.this";

    public void rebreMissatge(String missatge, String localitzacio) {
        // Set up notification
        android.support.v4.app.NotificationCompat.Builder notifAprop = new android.support.v4.app.NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.check)
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(),R.drawable.bread))
                .setContentTitle(missatge + " - " + localitzacio)
                .setContentText(getString(R.string.notif_text2))
                .setAutoCancel(true)
                .setPriority(PRIORITY_MAX);

        // intentClicat = Quan es clica. constPila = Acció quan torna.
        Intent intentClicat = new Intent(this, DialegNotificacio.class);
        intentClicat.putExtra("msg", missatge);
        intentClicat.putExtra("loc", localitzacio);

//        Intent per "Fet"
//        Intent fetIntent = new Intent(this, Main.class);
//        intentClicat.putExtra("msg", missatge);
//        intentClicat.putExtra("loc", localitzacio)
//        intentClicat.setAction(ACTION_FET);
//        PendingIntent pendingIntentFet = PendingIntent.getBroadcast(this, 12345, intentClicat, PendingIntent.FLAG_UPDATE_CURRENT);
//        notifAprop.addAction(R.drawable.check, "Fet", pendingIntentFet);

        android.support.v4.app.TaskStackBuilder constPila = android.support.v4.app.TaskStackBuilder.create(this);
        constPila.addParentStack(Main.class);
        constPila.addNextIntent(intentClicat);
        PendingIntent resultPendingIntent = constPila.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        notifAprop.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify((int) System.currentTimeMillis(), notifAprop.build());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {}
                return;
            }
        }
    }

    // Tot aixo es per refrescar automaticament el ListView quan arriba un missatge nou.
    private class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("ARRIBAT:", "Arriba al mybroadcast...");
            Bundle extras = intent.getExtras();

            if (intent.getAction().equals("com.banannaps.cool.bringbread.Afegir")) { //Afegir al listview
                //Quan be del servidor
                Log.d("ARRIBAT:", "Era un missatge");

                String localitzacio = extras.getString("loc");
                double latitud = Double.parseDouble(extras.getString("lat"));
                double longitud = Double.parseDouble(extras.getString("lon"));

                setListView();

                Llocs.put(localitzacio, new double[] {latitud, longitud});
                llocsPredefs = new ArrayList();
                if (llocsPredefs.contains(localitzacio)) llocsPredefs.add(localitzacio);

            } else if (intent.getAction().equals("com.banannaps.cool.bringbread.Notificacio")) {
                // Quan estàs a la location
                Log.d("ARRIBAT:", "Era una notificacio");

                String localitzacio = extras.getString("loc");
                String missatge = extras.getString("msg");
                rebreMissatge(missatge, localitzacio);

            } else if (intent.getAction().equals("com.banannaps.cool.bringbread.Update")) {
                // Actualitzar
                setListView();

            } else if (intent.getAction().equals("com.banannaps.cool.bringbread.Esborra")) {
                // Esborrar
                String localitzacio = extras.getString("loc");
                String missatge = extras.getString("msg");
                esborraAndRefresh(localitzacio,missatge);
            }
        }
    }

    public void esborraAndRefresh (String localitzacio, String missatge) {
        List<Tasca> aBorrar = new ArrayList<>();

        for(Tasca tasca : Main.pendents){
            if(tasca.getMessage().equals(missatge) && tasca.getLocation().equals(localitzacio))
                aBorrar.add(tasca);
        }
        for(Tasca tasca : aBorrar) Main.pendents.remove(tasca);
        setListView();
    }
}