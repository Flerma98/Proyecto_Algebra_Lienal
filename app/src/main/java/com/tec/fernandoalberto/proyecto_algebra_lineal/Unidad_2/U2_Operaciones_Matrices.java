package com.tec.fernandoalberto.proyecto_algebra_lineal.Unidad_2;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.tec.fernandoalberto.proyecto_algebra_lineal.AdapterDatosResultados;
import com.tec.fernandoalberto.proyecto_algebra_lineal.AdapterDatosTabla;
import com.tec.fernandoalberto.proyecto_algebra_lineal.MainActivity;
import com.tec.fernandoalberto.proyecto_algebra_lineal.R;

import java.util.ArrayList;

import Jama.Matrix;

public class U2_Operaciones_Matrices extends AppCompatActivity {

    private EditText txtF1, txtC1, txtF2, txtC2;
    private Spinner spnOpciones;
    private Button btnCrear1, btnCrear2, btnResultado;
    private ArrayList<String> listaDatos1, listaDatos2, arrayList;
    private AdapterDatosTabla adapter1, adapter2;
    private AdapterDatosResultados adapterR;
    private RecyclerView recycler1, recycler2, Rrecycler;
    private int filas1, columnas1, cuadricula1, filas2, columnas2, cuadricula2;
    private Boolean t1, t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u2__operaciones__matrices);
        txtF1= findViewById(R.id.txtOPE1Fila);
        txtC1= findViewById(R.id.txtOPE1Columna);
        txtF2= findViewById(R.id.txtOPE2Fila);
        txtC2= findViewById(R.id.txtOPE2Columna);
        btnCrear1= findViewById(R.id.btnOPE1Crear);
        btnCrear2= findViewById(R.id.btnOPE2Crear);
        btnResultado= findViewById(R.id.btnResolverOperaciones);
        recycler1= findViewById(R.id.OPE1Recycler);
        recycler2= findViewById(R.id.OPE2Recycler);
        Rrecycler= findViewById(R.id.ResultadoOPERecycler);
        spnOpciones= findViewById(R.id.spnOperacionMatrices);
        t1= false;
        t2= false;
        String[] opcionesDe = {"+", "-", "*"};
        spnOpciones.setAdapter(new ArrayAdapter<String>(this, R.layout.spinner_texto, opcionesDe));
        btnCrear1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                if(txtF1.getText().toString().length()==0 || txtC1.getText().toString().length()==0){
                    t1=false;
                    Toast.makeText(U2_Operaciones_Matrices.this, "Campos no validos", Toast.LENGTH_SHORT).show();
                    btnResultado.setEnabled(false);
                } else {
                    t1=true;
                    if(t1&&t2){
                        btnResultado.setEnabled(true);
                    }
                    filas1 = Integer.parseInt(txtF1.getText().toString());
                    columnas1 = Integer.parseInt(txtC1.getText().toString());
                    cuadricula1 = filas1 * columnas1;
                    listaDatos1 = new ArrayList<>();
                    for (int i = 0; i < cuadricula1; i++) {
                        listaDatos1.add("");
                    }
                    recycler1.setLayoutManager(new GridLayoutManager(U2_Operaciones_Matrices.this, columnas1));
                    adapter1 = new AdapterDatosTabla(listaDatos1, filas1, columnas1);
                    recycler1.setAdapter(adapter1);
                }
            }catch (Exception e){}
            }
        });
        btnCrear2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                if(txtF2.getText().toString().length()==0 || txtC2.getText().toString().length()==0){
                    t2=false;
                    Toast.makeText(U2_Operaciones_Matrices.this, "Campos no validos", Toast.LENGTH_SHORT).show();
                } else {
                    t2=true;
                    if(t1&&t2){
                        btnResultado.setEnabled(true);
                    }
                    filas2 = Integer.parseInt(txtF2.getText().toString());
                    columnas2 = Integer.parseInt(txtC2.getText().toString());
                    cuadricula2 = filas2 * columnas2;
                    listaDatos2 = new ArrayList<>();
                    for (int i = 0; i < cuadricula2; i++) {
                        listaDatos2.add("");
                    }
                    recycler2.setLayoutManager(new GridLayoutManager(U2_Operaciones_Matrices.this, columnas2));
                    adapter2 = new AdapterDatosTabla(listaDatos2, filas2, columnas2);
                    recycler2.setAdapter(adapter2);
                }
            }catch (Exception e){}
            }
        });
        btnResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    if(spnOpciones.getSelectedItem().equals("+")){
                        if(filas1==filas2&&columnas1==columnas2){
                            //Tabla1
                            int contador1 = 0;
                            String[][] result1 = adapter1.getData();
                            double[][] matriz1 = new double[filas1][columnas1];
                            for (int i = 0; i < result1.length; i++) {
                                for (int j = 0; j < result1.length; j++) {
                                    ConstraintLayout rootView1 = (ConstraintLayout) recycler1.getChildAt(contador1++);
                                    matriz1[i][j] = Double.parseDouble(((EditText) rootView1.findViewById(R.id.txtListaRecycler)).getText().toString());
                                }
                            }
                            //Tabla2
                            int contador2 = 0;
                            String[][] result2 = adapter2.getData();
                            double[][] matriz2 = new double[filas2][columnas2];
                            for (int i = 0; i < result2.length; i++) {
                                for (int j = 0; j < result2.length; j++) {
                                    ConstraintLayout rootView2 = (ConstraintLayout) recycler2.getChildAt(contador2++);
                                    matriz2[i][j] = Double.parseDouble(((EditText) rootView2.findViewById(R.id.txtListaRecycler)).getText().toString());
                                }
                            }
                            double[][] r= new double[matriz1.length][matriz1[0].length];
                            for(int i = 0; i < filas1; i++)
                            {
                                for(int j = 0; j < columnas1; j++)
                                    r[i][j] = matriz1[i][j] + matriz2[i][j];

                            }
                            arrayList = new ArrayList<>();
                            for (int i = 0; i < matriz1.length; i++) {
                                for (int x = 0; x < matriz1[0].length; x++) {
                                    arrayList.add(String.valueOf(MainActivity.decimalToFraction(r[i][x])));
                                }
                            }
                            Rrecycler.setLayoutManager(new GridLayoutManager(U2_Operaciones_Matrices.this, columnas2));
                            adapterR = new AdapterDatosResultados(arrayList, filas1, columnas1);
                            Rrecycler.setAdapter(adapterR);
                        }else{ Toast.makeText(U2_Operaciones_Matrices.this, "No se pueden sumar estas matrices", Toast.LENGTH_SHORT).show();}
                    }
                    if(spnOpciones.getSelectedItem().equals("-")){
                        if(filas1==filas2&&columnas1==columnas2){
                            //Tabla1
                            int contador1 = 0;
                            String[][] result1 = adapter1.getData();
                            double[][] matriz1 = new double[filas1][columnas1];
                            for (int i = 0; i < result1.length; i++) {
                                for (int j = 0; j < result1.length; j++) {
                                    ConstraintLayout rootView1 = (ConstraintLayout) recycler1.getChildAt(contador1++);
                                    matriz1[i][j] = Double.parseDouble(((EditText) rootView1.findViewById(R.id.txtListaRecycler)).getText().toString());
                                }
                            }
                            //Tabla2
                            int contador2 = 0;
                            String[][] result2 = adapter2.getData();
                            double[][] matriz2 = new double[filas2][columnas2];
                            for (int i = 0; i < result2.length; i++) {
                                for (int j = 0; j < result2.length; j++) {
                                    ConstraintLayout rootView2 = (ConstraintLayout) recycler2.getChildAt(contador2++);
                                    matriz2[i][j] = Double.parseDouble(((EditText) rootView2.findViewById(R.id.txtListaRecycler)).getText().toString());
                                }
                            }
                            double[][] r= new double[matriz1.length][matriz1[0].length];
                                    for(int i = 0; i < filas1; i++)
                                    {
                                        for(int j = 0; j < columnas1; j++)
                                            r[i][j] = matriz1[i][j] - matriz2[i][j];
                                    }
                            arrayList = new ArrayList<>();
                            for (int i = 0; i < matriz1.length; i++) {
                                for (int x = 0; x < matriz1[0].length; x++) {
                                    arrayList.add(String.valueOf(MainActivity.decimalToFraction(r[i][x])));
                                }
                            }
                            Rrecycler.setLayoutManager(new GridLayoutManager(U2_Operaciones_Matrices.this, columnas2));
                            adapterR = new AdapterDatosResultados(arrayList, filas1, columnas1);
                            Rrecycler.setAdapter(adapterR);
                        }else{ Toast.makeText(U2_Operaciones_Matrices.this, "No se pueden restar estas matrices", Toast.LENGTH_SHORT).show();}
                    }


                    if(spnOpciones.getSelectedItem().equals("*")){
                        if(filas2==columnas1){
                            //Tabla1
                            int contador1 = 0;
                            String[][] result1 = adapter1.getData();
                            double[][] matriz1 = new double[filas1][columnas1];
                            for (int i = 0; i < filas1; i++) {
                                for (int j = 0; j < columnas1; j++) {
                                    ConstraintLayout rootView1 = (ConstraintLayout) recycler1.getChildAt(contador1++);
                                    matriz1[i][j] = Double.parseDouble(((EditText) rootView1.findViewById(R.id.txtListaRecycler)).getText().toString());
                                }
                            }
                            //Tabla2
                            int contador2 = 0;
                            String[][] result2 = adapter2.getData();
                            double[][] matriz2 = new double[filas2][columnas2];
                            for (int i = 0; i < filas2; i++) {
                                for (int j = 0; j < columnas2; j++) {
                                    ConstraintLayout rootView2 = (ConstraintLayout) recycler2.getChildAt(contador2++);
                                    matriz2[i][j] = Double.parseDouble(((EditText) rootView2.findViewById(R.id.txtListaRecycler)).getText().toString());
                                }
                            }
                            double[][] mR=new double[filas1][columnas2];
                            for(int i=0;i<filas1;i++){
                                for(int j=0;j<columnas2;j++){
                                    for(int h=0;h<columnas1;h++){
                                        mR[i][j]+=matriz1[i][h]*matriz2[h][j];
                                    }
                                }
                            }
                            arrayList = new ArrayList<>();
                            for (int i = 0; i < matriz1.length; i++) {
                                for (int x = 0; x < matriz2[0].length; x++) {
                                    arrayList.add(String.valueOf(MainActivity.decimalToFraction(mR[i][x])));
                                }
                            }
                            Rrecycler.setLayoutManager(new GridLayoutManager(U2_Operaciones_Matrices.this, columnas2));
                            adapterR = new AdapterDatosResultados(arrayList, filas1, columnas2);
                            Rrecycler.setAdapter(adapterR);
                        }else{ Toast.makeText(U2_Operaciones_Matrices.this, "No se pueden multiplicar estas matrices", Toast.LENGTH_SHORT).show();}
                    }
                }catch (Exception e){}
            }
        });
    }
}
