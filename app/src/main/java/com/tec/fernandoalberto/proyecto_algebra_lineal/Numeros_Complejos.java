package com.tec.fernandoalberto.proyecto_algebra_lineal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tec.fernandoalberto.proyecto_algebra_lineal.Unidad_1.U1_ConversionesRPE;
import com.tec.fernandoalberto.proyecto_algebra_lineal.Unidad_1.U1_Modulo_Numero_Complejo;
import com.tec.fernandoalberto.proyecto_algebra_lineal.Unidad_1.U1_Operaciones_Basicas_Numeros_Complejos;
import com.tec.fernandoalberto.proyecto_algebra_lineal.Unidad_1.U1_Operaciones_Forma_Polar;
import com.tec.fernandoalberto.proyecto_algebra_lineal.Unidad_1.U1_Potencias_Raiz_NComplejos;
import com.tec.fernandoalberto.proyecto_algebra_lineal.Unidad_1.U1_Potencias_i;

import java.util.ArrayList;

public class Numeros_Complejos extends Fragment {

     private ArrayList<String> listaDatos;
     private RecyclerView recycler1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_numeros__complejos, container, false);
        recycler1= view.findViewById(R.id.FNCRecycler);
        listaDatos= new ArrayList<>();
        listaDatos.add("Operaciones Basicas con Numeros Complejos");
        listaDatos.add("Potencias de i");
        listaDatos.add("Modulo de un Numero Complejo");
        listaDatos.add("Conversiones (Rectangular - Polar - Exponencial)");
        listaDatos.add("Operaciones en Forma Polar");
        listaDatos.add("Potencias y Raíz de un Numero Complejo");
        recycler1.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        AdapterDatostxt adapter= new AdapterDatostxt(listaDatos);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String clase= listaDatos.get(recycler1.getChildAdapterPosition(view));
                switch (clase){
                    case "Operaciones Basicas con Numeros Complejos":
                        Intent i = new Intent(getActivity(), U1_Operaciones_Basicas_Numeros_Complejos.class);
                        getActivity().startActivity(i);
                        break;
                    case "Potencias de i":
                        startActivity(new Intent(getActivity(), U1_Potencias_i.class));
                        break;
                    case "Modulo de un Numero Complejo":
                        startActivity(new Intent(getActivity(), U1_Modulo_Numero_Complejo.class));
                        break;
                    case "Conversiones (Rectangular - Polar - Exponencial)":
                        startActivity(new Intent(getActivity(), U1_ConversionesRPE.class));
                        break;
                    case "Operaciones en Forma Polar":
                        startActivity(new Intent(getActivity(), U1_Operaciones_Forma_Polar.class));
                        break;
                    case "Potencias y Raíz de un Numero Complejo":
                        startActivity(new Intent(getActivity(), U1_Potencias_Raiz_NComplejos.class));
                        break;
                }
            }
        });
        recycler1.setAdapter(adapter);
        return view;
    }
}
