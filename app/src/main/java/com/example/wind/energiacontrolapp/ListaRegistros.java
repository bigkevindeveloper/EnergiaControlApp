package com.example.wind.energiacontrolapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Map;

public class ListaRegistros extends AppCompatActivity {
    ListView listaRegistros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_registros);
        listaRegistros = (ListView) findViewById(R.id.listaRegistros);

        final ArrayList<Local> locales = new ArrayList<>();
        //Recogeremos todos los datos dentro de los shared preferences.
        SharedPreferences prefs = getSharedPreferences("LOCALES",Context.MODE_PRIVATE);
        Map<String,?> keys = prefs.getAll();

        for(Map.Entry<String,?> entry : keys.entrySet()){
            //Recorremos el mapa de Strings
            Gson gson = new Gson(); //Intanciamos gson
            String nuevo = prefs.getString(entry.getKey(),"");
            Local x = gson.fromJson(nuevo,Local.class);
            locales.add(x);
        }

        LocalAdapter adapter = new LocalAdapter(this,locales);
        listaRegistros.setAdapter(adapter);
        listaRegistros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),""+locales.get(position).getNombreLocal(),Toast.LENGTH_LONG).show();
                int clave = locales.get(position).getIdLocal();
                //Retrasmo el cambio a la otra vista 3 segundos.
                try {
                    Toast.makeText(getApplicationContext(),"Cargando...",Toast.LENGTH_LONG).show();
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    //NADA
                }

                Intent i = new Intent(getApplicationContext(),RegistroVista.class);
                i.putExtra("clave",clave);
                startActivity(i);
            }
        });
    }
}



