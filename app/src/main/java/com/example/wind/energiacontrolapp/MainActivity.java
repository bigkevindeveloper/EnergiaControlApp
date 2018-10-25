package com.example.wind.energiacontrolapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import info.hoang8f.widget.FButton;

public class MainActivity extends AppCompatActivity {

    Button verRegistro, configuracionRegistro;
    boolean hayConexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        verRegistro = (Button) findViewById(R.id.btnVerRegistros);
        configuracionRegistro = (Button) findViewById(R.id.btnRegistrar);

        //COMPROBAR CONEXION A INTERNET.
        hayConexion = compruebaConexion(getApplicationContext());

        if(!hayConexion){
            Toast.makeText(getApplicationContext(),"No hay conexion!",Toast.LENGTH_LONG).show();
            finish();
        }
        else{
            Toast.makeText(getApplicationContext(),"Hay conexion!",Toast.LENGTH_SHORT).show();
        }



        //FUNCIONES CON BOTONES...!
        verRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),"Entrar ver registros",Toast.LENGTH_SHORT).show();
                Intent I = new Intent(getApplicationContext(),ListaRegistros.class);
                startActivity(I);

            }
        });

        configuracionRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Entrar configuracion",Toast.LENGTH_SHORT).show();
                Intent I = new Intent(getApplicationContext(),Registrar.class);
                startActivity(I);
            }
        });
    }

    public static boolean compruebaConexion(Context context)
    {
        boolean connected = false;
        ConnectivityManager connec = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        // Recupera todas las redes (tanto móviles como wifi)
        NetworkInfo[] redes = connec.getAllNetworkInfo();

        for (int i = 0; i < redes.length; i++) {
            // Si alguna red tiene conexión, se devuelve true
            if (redes[i].getState() == NetworkInfo.State.CONNECTED) {
                connected = true;
            }
        }
        return connected;
    }
}
