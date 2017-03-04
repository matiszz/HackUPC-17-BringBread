package com.banannaps.cool.bringbread;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import static android.app.Notification.PRIORITY_MAX;

public class Main extends AppCompatActivity {
    ListView lv_tasques;
    FloatingActionButton btn_afegir;
    Button btnNotif;

    // El map Llocs com a clau el Nom del lloc, i un Vecotr de 2 doubles amb latitud i longitud.
    public static ArrayMap<String, double[]> Llocs = new ArrayMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setBtnAfegir();
        setListView();

        /////////////////////

        btnNotif = (Button) findViewById(R.id.btnNotific);
        btnNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rebreMissatge();
            }
        });

        /////////////////////
    }

    private void rebreMissatge() {

        int mId = 0;

        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher_round)
                        .setContentTitle("My notification")
                        .setContentText("Hello World!")
                        .setPriority(PRIORITY_MAX);

        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        String[] events = new String[6];
        // Sets a title for the Inbox in expanded layout
        inboxStyle.setBigContentTitle("Event tracker details:");
        // Moves events into the expanded layout
        for (int i=0; i < events.length; i++) {

            inboxStyle.addLine(events[i]);
        }
        // Moves the expanded layout object into the notification object.
        mBuilder.setStyle(inboxStyle);


        // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, Main.class);

        // The stack builder object will contain an artificial back stack for the
        // started Activity.
        // This ensures that navigating backward from the Activity leads out of
        // your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(Main.class);
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.
        mNotificationManager.notify(mId, mBuilder.build());
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
        final String[] nomTasques = new String[] { "Comprar pa", "Comprar gel", "Comprar vodka", "Comprar llet" };
        final String[] locTasques = new String[] { "Forn de pa", "Super", "Celler", "Super"};

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_2, android.R.id.text1, nomTasques) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView nomTasca = (TextView) view.findViewById(android.R.id.text1);
                TextView locTasca = (TextView) view.findViewById(android.R.id.text2);

                nomTasca.setText(nomTasques[position]);
                locTasca.setText(locTasques[position]);
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

        return super.onOptionsItemSelected(item);
    }

}