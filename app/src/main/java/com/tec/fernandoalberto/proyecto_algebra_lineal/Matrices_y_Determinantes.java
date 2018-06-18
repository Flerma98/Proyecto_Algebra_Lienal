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
import com.tec.fernandoalberto.proyecto_algebra_lineal.Unidad_1.U1_ConversionesRPE;
import com.tec.fernandoalberto.proyecto_algebra_lineal.Unidad_1.U1_Modulo_Numero_Complejo;
import com.tec.fernandoalberto.proyecto_algebra_lineal.Unidad_1.U1_Operaciones_Basicas_Numeros_Complejos;
import com.tec.fernandoalberto.proyecto_algebra_lineal.Unidad_1.U1_Operaciones_Forma_Polar;
import com.tec.fernandoalberto.proyecto_algebra_lineal.Unidad_1.U1_Potencias_Raiz_NComplejos;
import com.tec.fernandoalberto.proyecto_algebra_lineal.Unidad_1.U1_Potencias_i;
import com.tec.fernandoalberto.proyecto_algebra_lineal.Unidad_2.U2_Adjunta;
import com.tec.fernandoalberto.proyecto_algebra_lineal.Unidad_2.U2_Cofactor;
import com.tec.fernandoalberto.proyecto_algebra_lineal.Unidad_2.U2_Determinante;
import com.tec.fernandoalberto.proyecto_algebra_lineal.Unidad_2.U2_Inversa;
import com.tec.fernandoalberto.proyecto_algebra_lineal.Unidad_2.U2_Menores;
import com.tec.fernandoalberto.proyecto_algebra_lineal.Unidad_2.U2_Rango;
import com.tec.fernandoalberto.proyecto_algebra_lineal.Unidad_2.U2_Transpuesta;

import java.util.ArrayList;

public class Matrices_y_Determinantes extends Fragment {

    private ArrayList<String> listaDatos;
    private RecyclerView recycler1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_matrices_y__determinantes, container, false);
        recycler1= view.findViewById(R.id.FMDRecycler);
        listaDatos= new ArrayList<>();
        listaDatos.add("Operaciones con Matrices");
        listaDatos.add("Transpuesta de una Matriz");
        listaDatos.add("Rango de una Matriz");
        listaDatos.add("Inversa de una Matriz");
        listaDatos.add("Determinante de una Matriz");
        listaDatos.add("Menores de una Matriz");
        listaDatos.add("Cofactor de una Matriz");
        listaDatos.add("Adjunta de una Matriz");
        recycler1.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        AdapterDatostxt adapter= new AdapterDatostxt(listaDatos);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String clase= listaDatos.get(recycler1.getChildAdapterPosition(view));
                switch (clase){
                    case "Operaciones con Matrices":
                        startActivity(new Intent(getActivity(), U1_Operaciones_Basicas_Numeros_Complejos.class));
                        break;
                    case "Transpuesta de una Matriz":
                        startActivity(new Intent(getActivity(), U2_Transpuesta.class));
                        break;
                    case "Rango de una Matriz":
                        startActivity(new Intent(getActivity(), U2_Rango.class));
                        break;
                    case "Inversa de una Matriz":
                        startActivity(new Intent(getActivity(), U2_Inversa.class));
                        break;
                    case "Determinante de una Matriz":
                        startActivity(new Intent(getActivity(), U2_Determinante.class));
                        break;
                    case "Menores de una Matriz":
                        startActivity(new Intent(getActivity(), U2_Menores.class));
                        break;
                    case "Cofactor de una Matriz":
                        startActivity(new Intent(getActivity(), U2_Cofactor.class));
                        break;
                    case "Adjunta de una Matriz":
                        startActivity(new Intent(getActivity(), U2_Adjunta.class));
                        break;
                }
            }
        });
        recycler1.setAdapter(adapter);
        return view;
    }
}
