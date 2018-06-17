package com.tec.fernandoalberto.proyecto_algebra_lineal;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

public class AdapterDatosTabla extends RecyclerView.Adapter<AdapterDatosTabla.ViewHolderDatos> implements View.OnClickListener{

    ArrayList<String> listDatos;
    int filas, columnas;
    private View.OnClickListener listener;

    public AdapterDatosTabla(ArrayList<String> listDatos, int filas, int columnas) {
        this.listDatos = listDatos;
        this.filas= filas;
        this.columnas= columnas;
    }

    //@NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_tabla,null,false);
        view.setOnClickListener(this);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.asignarDatos(listDatos.get(position));
    }

    public String[][] getData(){
        int cont =0;
        String[][] strings= new String[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                strings[i][j]= listDatos.get(cont++);
            }
        }
        return strings;
    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener= listener;
    }

    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        EditText dato;

        public ViewHolderDatos(View itemView) {
            super(itemView);
            dato= (EditText) itemView.findViewById(R.id.txtListaRecycler);
        }

        public String getData(){
            return dato.getText().toString();
        }

        public void asignarDatos(String s) {
            dato.setText(s);
        }
    }
}
