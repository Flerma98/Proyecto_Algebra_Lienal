package com.tec.fernandoalberto.proyecto_algebra_lineal;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tec.fernandoalberto.proyecto_algebra_lineal.AdapterDatosTabla;
import com.tec.fernandoalberto.proyecto_algebra_lineal.AdapterDatostxt;
import com.tec.fernandoalberto.proyecto_algebra_lineal.R;

import java.util.ArrayList;

public class Sistema_Ecuaciones_Lineales extends Fragment {

    private ArrayList<String> listaDatos;
    private RecyclerView recycler1;
    private EditText txtFilas, txtColumnas;
    private AdapterDatosTabla adapter;
    private Button btnCrear, btnObtener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_sistema__ecuaciones__lineales, container, false);
        txtFilas= view.findViewById(R.id.txtFSELFila);
        txtColumnas= view.findViewById(R.id.txtFSELColumna);
        btnCrear= view.findViewById(R.id.btnFSELCrear);
        btnObtener= view.findViewById(R.id.btnObtener);
        recycler1= view.findViewById(R.id.FSELRecycler);
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtFilas.getText().toString().length()==0 || txtColumnas.getText().toString().length()==0){
                    btnObtener.setEnabled(false);
                }else{
                    btnObtener.setEnabled(true);
                    int filas= Integer.parseInt(txtFilas.getText().toString());
                    int columnas= Integer.parseInt(txtColumnas.getText().toString()) + 1;
                    int cuadricula= filas * columnas;
                    listaDatos= new ArrayList<>();
                    for (int i=0; i<cuadricula; i++){
                        listaDatos.add("");
                    }
                    recycler1.setLayoutManager(new GridLayoutManager(getActivity(), columnas));
                    adapter= new AdapterDatosTabla(listaDatos, filas, columnas);
                    recycler1.setAdapter(adapter);

                }
            }
        });

        btnObtener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int contador = 0;
                String[][] result = adapter.getData();
                Double[][] matriz = new Double[result.length][result[0].length];

                for (int i = 0; i < result.length; i++) {
                    for (int j = 0; j <= result.length; j++) {
                        ConstraintLayout rootView = (ConstraintLayout) recycler1.getChildAt(contador++);
                        matriz[i][j] = Double.parseDouble(((EditText)rootView.findViewById(R.id.txtListaRecycler)).getText().toString());
                    }
                }

            }
        });
        return view;
    }
}
