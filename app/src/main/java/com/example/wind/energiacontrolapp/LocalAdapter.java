package com.example.wind.energiacontrolapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class LocalAdapter extends ArrayAdapter<Local> {
    public LocalAdapter(Context context,List<Local> locales){
        super(context,0,locales);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View listItemView = convertView;
        if(listItemView==null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.localvista,parent,false
            );
        }
        Local currentLocal = getItem(position);
        TextView iedLocal = (TextView)listItemView.findViewById(R.id.idLocal);
        TextView nombredLocal = (TextView)listItemView.findViewById(R.id.nombreLocal);

        iedLocal.setText(""+currentLocal.getIdLocal());
        nombredLocal.setText(currentLocal.getNombreLocal());
        return listItemView;
    }
}
