package com.tec.fernandoalberto.proyecto_algebra_lineal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tec.fernandoalberto.proyecto_algebra_lineal.Unidad_2.U2_Inversa;
import com.tec.fernandoalberto.proyecto_algebra_lineal.Unidad_4.U4_Base;
import com.tec.fernandoalberto.proyecto_algebra_lineal.Unidad_4.U4_Combinacion_Lineal;
import com.tec.fernandoalberto.proyecto_algebra_lineal.Unidad_4.U4_Dimension;
import com.tec.fernandoalberto.proyecto_algebra_lineal.Unidad_4.U4_Independencia_Lineal;
import com.tec.fernandoalberto.proyecto_algebra_lineal.Unidad_4.U4_Producto_Interno;

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
         recycler1.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        AdapterDatostxt adapter= new AdapterDatostxt(listaDatos);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String clase= listaDatos.get(recycler1.getChildAdapterPosition(view));
                switch (clase){
                    case "Independencia Lineal":
                        startActivity(new Intent(getActivity(), U4_Independencia_Lineal.class));
                        break;
                    case "Combinación Lineal":
                        startActivity(new Intent(getActivity(), U4_Combinacion_Lineal.class));
                        break;
                    case "Base de una Matriz":
                        startActivity(new Intent(getActivity(), U4_Base.class));
                        break;
                    case "Dimensión de una Matríz":
                        startActivity(new Intent(getActivity(), U4_Dimension.class));
                        break;
                    case "Producto Interno":
                        startActivity(new Intent(getActivity(), U4_Producto_Interno.class));
                        break;
                }
            }
        });
        recycler1.setAdapter(adapter);
        return view;
    }
}
