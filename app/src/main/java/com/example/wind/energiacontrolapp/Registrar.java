package com.example.wind.energiacontrolapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;

public class Registrar extends AppCompatActivity {

    Button btnAñadir,btnModificar;
    EditText nombreLocal,horaInicio,minutoInicio,horaFinal,minutoFinal,campoId;
    public ArrayList<Local> listaLocales;
    SeekBar consumo,potencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        btnAñadir = (Button) findViewById(R.id.btnAñadirLocal);
        btnModificar = (Button) findViewById(R.id.btnBorrarLocal);

        nombreLocal = (MaterialEditText) findViewById(R.id.edtNombre);
        horaInicio = (MaterialEditText) findViewById(R.id.edtHoraInicio);
        minutoInicio = (MaterialEditText) findViewById(R.id.edtMinutoInicio);
        horaFinal = (MaterialEditText) findViewById(R.id.edtHoraFinal);
        minutoFinal = (MaterialEditText) findViewById(R.id.edtMinutoFinal);

        campoId = (MaterialEditText) findViewById(R.id.edtId);

        consumo = (SeekBar) findViewById(R.id.barraConsumo);
        potencia = (SeekBar) findViewById(R.id.barraPotencia);

        //Inicializamos lista de objetos

        btnAñadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Vamos a guardar un objeto en un archivo de preferencias.
                int hi = Integer.parseInt(horaInicio.getText().toString());
                int mi = Integer.parseInt(minutoInicio.getText().toString());
                int hf = Integer.parseInt(horaFinal.getText().toString());
                int mf = Integer.parseInt(minutoFinal.getText().toString());
                int potenciaX = potencia.getProgress();
                int consumoX = consumo.getProgress();

                Local x = new Local(nombreLocal.getText().toString(),hi,mi,hf,mf,potenciaX,consumoX);
                //Creamos el objeto encargado de convertir a gson nuestro objeto Local
                Gson gson = new Gson();
                String gsonObjeto = gson.toJson(x);
                SharedPreferences preferencias = getSharedPreferences("LOCALES",Context.MODE_PRIVATE);
                //Creamos el editor encargado de realizar cambio sobre el archivo de preferencias.
                SharedPreferences.Editor editor = preferencias.edit();
                editor.putString(String.valueOf(x.getIdLocal()),gsonObjeto);
                editor.commit();

                comprobarRegistro(String.valueOf(x.getIdLocal()));

            }
        });

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Obtenemos el nombre del registro a borrar.
                SharedPreferences pref = getSharedPreferences("LOCALES",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                String key = campoId.getText().toString();
                editor.remove(key);
                editor.commit();
                //Toast.makeText(getApplicationContext(),"Borrado!",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void comprobarRegistro(String clave) {
        SharedPreferences preferencias = getSharedPreferences("LOCALES",Context.MODE_PRIVATE);
        String json = preferencias.getString(clave,"");
        Gson gson = new Gson(); //Instancia Gson.
        Local y = gson.fromJson(json,Local.class);
        //Toast.makeText(getApplicationContext(),""+y.getNombreLocal(),Toast.LENGTH_LONG).show();
    }
}
