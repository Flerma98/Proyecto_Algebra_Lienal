package com.tec.fernandoalberto.proyecto_algebra_lineal;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tec.fernandoalberto.proyecto_algebra_lineal.AdapterDatostxt;
import com.tec.fernandoalberto.proyecto_algebra_lineal.R;

import java.util.ArrayList;

public class Espacio_Vectorial extends Fragment {

    private ArrayList<String> listaDatos;
    private RecyclerView recycler1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_espacio__vectorial, container, false);
        recycler1= view.findViewById(R.id.FEVRecycler);
        listaDatos= new ArrayList<>();
        listaDatos.add("Independencia Lineal");
        listaDatos.add("Combinación Lineal");
        listaDatos.add("Base de una Matriz");
        listaDatos.add("Dimensión de una Matríz");
        listaDatos.add("Producto Interno");
         recycler1.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        AdapterDatostxt adapter= new AdapterDatostxt(listaDatos);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Selección: " + listaDatos.get(recycler1.getChildAdapterPosition(view)),Toast.LENGTH_SHORT).show();
            }
        });
        recycler1.setAdapter(adapter);
        return view;
    }
}
