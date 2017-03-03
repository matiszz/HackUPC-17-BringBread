package com.banannaps.cool.bringbread;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.id.content;
import static android.R.id.list;

public class Main extends AppCompatActivity {
    ListView lv_tasques;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setListView();
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

    void setListView() {

        lv_tasques = (ListView) findViewById(R.id.lv_tasques);
        final String[] nomTasques = new String[] { "Comprar pa", "Comprar gel", "Comprar vodka", "Comprar llet" };

        final String[] locTasques = new String[] { "Forn de pa", "Super", "Celler", "Super"};

       // ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);

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
}