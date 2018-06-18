package com.tec.fernandoalberto.proyecto_algebra_lineal.Unidad_2;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tec.fernandoalberto.proyecto_algebra_lineal.AdapterDatosResultados;
import com.tec.fernandoalberto.proyecto_algebra_lineal.AdapterDatosTabla;
import com.tec.fernandoalberto.proyecto_algebra_lineal.MainActivity;
import com.tec.fernandoalberto.proyecto_algebra_lineal.R;

import java.util.ArrayList;

public class U2_Transpuesta extends AppCompatActivity {

    private ArrayList<String> listaDatos, arrayList;
    private RecyclerView recycler1, Rrecycler;
    private EditText txtFilas, txtColumnas;
    private AdapterDatosTabla adapter;
    private AdapterDatosResultados adapterR;
    private Button btnCrear, btnObtener;
    private int filas, columnas, cuadricula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u2__transpuesta);
        txtFilas= findViewById(R.id.txtTFila);
        txtColumnas= findViewById(R.id.txtTColumna);
        btnCrear= findViewById(R.id.btnTCrear);
        btnObtener= findViewById(R.id.btnTObtener);
        recycler1= findViewById(R.id.TABLARecycler);
        Rrecycler= findViewById(R.id.TRESULTADORecycler);
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtFilas.getText().toString().length()==0 || txtColumnas.getText().toString().length()==0){
                    btnObtener.setEnabled(false);
                }else{
                    btnObtener.setEnabled(true);
                    filas= Integer.parseInt(txtFilas.getText().toString());
                    columnas= Integer.parseInt(txtColumnas.getText().toString());
                    cuadricula= filas * columnas;
                    listaDatos= new ArrayList<>();
                    for (int i=0; i<cuadricula; i++){
                        listaDatos.add("");
                    }
                    recycler1.setLayoutManager(new GridLayoutManager(U2_Transpuesta.this, columnas));
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
                double[][] matriz = new double[result.length][result[0].length];

                for (int i = 0; i < result.length; i++) {
                    for (int j = 0; j < result.length; j++) {
                        ConstraintLayout rootView = (ConstraintLayout) recycler1.getChildAt(contador++);
                        matriz[i][j] = Double.parseDouble(((EditText)rootView.findViewById(R.id.txtListaRecycler)).getText().toString());
                    }
                }

                Jama.Matrix matrix = new Jama.Matrix(matriz);
                double[][] transpuesta= matrix.transpose().getArray();
                arrayList= new ArrayList<>();
                for (int i = 0; i < matriz.length; i++) {
                    for (int x = 0; x < matriz[0].length; x++) {
                        arrayList.add(String.valueOf(MainActivity.decimalToFraction(transpuesta[i][x])));
                    }
                }
                Rrecycler.setLayoutManager(new GridLayoutManager(U2_Transpuesta.this, columnas));
                adapterR= new AdapterDatosResultados(arrayList, filas, columnas);
                Rrecycler.setAdapter(adapterR);
                Rrecycler.setEnabled(false);
            }
        });
    }
}
