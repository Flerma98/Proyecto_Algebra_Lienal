package com.tec.fernandoalberto.proyecto_algebra_lineal;

import android.content.Context;
import android.content.Intent;
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
import com.tec.fernandoalberto.proyecto_algebra_lineal.Unidad_2.U2_Adjunta;
import com.tec.fernandoalberto.proyecto_algebra_lineal.Unidad_2.U2_Cofactor;
import com.tec.fernandoalberto.proyecto_algebra_lineal.Unidad_2.U2_Determinante;
import com.tec.fernandoalberto.proyecto_algebra_lineal.Unidad_2.U2_Inversa;
import com.tec.fernandoalberto.proyecto_algebra_lineal.Unidad_2.U2_Menores;
import com.tec.fernandoalberto.proyecto_algebra_lineal.Unidad_2.U2_Operaciones_Matrices;
import com.tec.fernandoalberto.proyecto_algebra_lineal.Unidad_2.U2_Rango;
import com.tec.fernandoalberto.proyecto_algebra_lineal.Unidad_2.U2_Transpuesta;
import com.tec.fernandoalberto.proyecto_algebra_lineal.Unidad_4.U4_Independencia_Lineal;

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
                        startActivity(new Intent(getActivity(), U2_Transpuesta.class));
                        break;
                    case "Base de una Matriz":
                        startActivity(new Intent(getActivity(), U2_Rango.class));
                        break;
                    case "Dimensión de una Matríz":
                        startActivity(new Intent(getActivity(), U2_Inversa.class));
                        break;
                    case "Producto Interno":
                        startActivity(new Intent(getActivity(), U2_Determinante.class));
                        break;
                }
            }
        });
        recycler1.setAdapter(adapter);
        return view;
    }
}
