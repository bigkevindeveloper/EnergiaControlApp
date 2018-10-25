package com.example.wind.energiacontrolapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class RegistroVista extends AppCompatActivity {

    TextView nombreLocal,horaInicio,horaFin,temp;
    SeekBar consumoLocal,potenciaLocal;
    Local local;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_vista);

        int clave = getIntent().getExtras().getInt("clave");
        SharedPreferences prefs = getSharedPreferences("LOCALES",Context.MODE_PRIVATE);

        nombreLocal = (TextView) findViewById(R.id.nameLocal);
        horaInicio = (TextView) findViewById(R.id.horaInicioLocal);
        horaFin = (TextView) findViewById(R.id.horaFinLocal);
        consumoLocal = (SeekBar)findViewById(R.id.barraConsumoLocal);
        potenciaLocal = (SeekBar) findViewById(R.id.barraPotenciaLocal);
        temp = (TextView) findViewById(R.id.temporizador);

        String valor = prefs.getString(String.valueOf(clave),"");
        Gson g = new Gson();

        local = g.fromJson(valor,Local.class);
        Rellenar(local);
    }

    public void Rellenar(Local local){
        nombreLocal.setText(local.getNombreLocal());
        String horaInicial = local.getHoraInicio()+":"+local.getMinutoInicio();
        String horaFinal = local.getHoraFinal()+":"+local.getMinutoFinal();
        horaInicio.setText(horaInicial);
        horaFin.setText(horaFinal);
        consumoLocal.setProgress(local.getConsumo());
        potenciaLocal.setProgress(local.getPotencia());
    }

    public void configurar(View v){
        SharedPreferences prefs = getSharedPreferences("LOCALES",Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = prefs.edit();
        local.setConsumo(consumoLocal.getProgress());
        local.setPotencia(potenciaLocal.getProgress());
        Gson gson = new Gson();
        String gsonObjeto = gson.toJson(local);

        edit.putString(String.valueOf(local.getIdLocal()),gsonObjeto);
        edit.commit();
        temporizador();
    }

    public void temporizador(){
        int segundos = ((local.getHoraFinal()-local.getHoraInicio())*60)*60;
        int hora,minuto;


        //Toast.makeText(getApplicationContext(),""+segundos,Toast.LENGTH_LONG).show();
    }
}
