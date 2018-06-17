package com.tec.fernandoalberto.proyecto_algebra_lineal;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

public class AdapterDatostxt extends RecyclerView.Adapter<AdapterDatostxt.ViewHolderDatos> implements View.OnClickListener{

    ArrayList<String> listDatos;
    private View.OnClickListener listener;

    public AdapterDatostxt(ArrayList<String> listDatos) {
        this.listDatos = listDatos;
    }

    //@NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_buttons,null,false);
        view.setOnClickListener(this);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.asignarDatos(listDatos.get(position));
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

        Button dato;

        public ViewHolderDatos(View itemView) {
            super(itemView);
            dato= (Button) itemView.findViewById(R.id.btnListaRecycler);
        }

        public void asignarDatos(String s) {
            dato.setText(s);
        }
    }
}
