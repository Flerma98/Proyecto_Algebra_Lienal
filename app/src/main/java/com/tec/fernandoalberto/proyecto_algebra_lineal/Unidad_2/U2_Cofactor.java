package com.tec.fernandoalberto.proyecto_algebra_lineal.Unidad_2;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tec.fernandoalberto.proyecto_algebra_lineal.AdapterDatosResultados;
import com.tec.fernandoalberto.proyecto_algebra_lineal.AdapterDatosTabla;
import com.tec.fernandoalberto.proyecto_algebra_lineal.MainActivity;
import com.tec.fernandoalberto.proyecto_algebra_lineal.R;
import com.tec.fernandoalberto.proyecto_algebra_lineal.Unidad_4.U4_Producto_Interno;

import java.util.ArrayList;

public class U2_Cofactor extends AppCompatActivity {

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
        setContentView(R.layout.activity_u2__cofactor);
        txtFilas = findViewById(R.id.txtCOFACTORFila);
        txtColumnas = findViewById(R.id.txtCOFACTORColumna);
        btnCrear = findViewById(R.id.btnCOFACTORCrear);
        btnObtener = findViewById(R.id.btnCOFACTORObtener);
        recycler1 = findViewById(R.id.COFACTORRecycler);
        Rrecycler = findViewById(R.id.COFACTORRESULTADORecycler);
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtFilas.getText().toString().length() == 0 || txtColumnas.getText().toString().length() == 0 || Integer.parseInt(txtFilas.getText().toString())!=Integer.parseInt(txtColumnas.getText().toString())) {
                    btnObtener.setEnabled(false);
                    Toast.makeText(U2_Cofactor.this, "Campos No Validos", Toast.LENGTH_SHORT).show();
                } else {
                    btnObtener.setEnabled(true);
                    filas = Integer.parseInt(txtFilas.getText().toString());
                    columnas = Integer.parseInt(txtColumnas.getText().toString());
                    cuadricula = filas * columnas;
                    listaDatos = new ArrayList<>();
                    for (int i = 0; i < cuadricula; i++) {
                        listaDatos.add("");
                    }
                    recycler1.setLayoutManager(new GridLayoutManager(U2_Cofactor.this, columnas));
                    adapter = new AdapterDatosTabla(listaDatos, filas, columnas);
                    recycler1.setAdapter(adapter);

                }
            }
        });

        btnObtener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    int contador = 0;
                    String[][] result = adapter.getData();
                    double[][] matriz = new double[result.length][result[0].length];

                    for (int i = 0; i < result.length; i++) {
                        for (int j = 0; j < result.length; j++) {
                            ConstraintLayout rootView = (ConstraintLayout) recycler1.getChildAt(contador++);
                            matriz[i][j] = Double.parseDouble(((EditText) rootView.findViewById(R.id.txtListaRecycler)).getText().toString());
                        }
                    }

                    double[][] cofactor = matrizCofactores(matriz);
                    arrayList = new ArrayList<>();
                    for (int i = 0; i < matriz.length; i++) {
                        for (int x = 0; x < matriz[0].length; x++) {
                            arrayList.add(String.valueOf(MainActivity.decimalToFraction(cofactor[i][x])));
                        }
                    }
                    Rrecycler.setLayoutManager(new GridLayoutManager(U2_Cofactor.this, columnas));
                    adapterR = new AdapterDatosResultados(arrayList, filas, columnas);
                    Rrecycler.setAdapter(adapterR);
                    Rrecycler.setEnabled(false);
                }catch (Exception e){
                    Toast.makeText(U2_Cofactor.this, "No se puede calcular esta matriz", Toast.LENGTH_SHORT).show();}
            }
        });
    }
    public double[][] matrizInversa(double[][] matriz) {
        double det=1/determinante(matriz);
        double[][] nmatriz=matrizAdjunta(matriz);
        multiplicarMatriz(det,nmatriz);
        return nmatriz;
    }

    public void multiplicarMatriz(double n, double[][] matriz) {
        for(int i=0;i<matriz.length;i++)
            for(int j=0;j<matriz.length;j++)
                matriz[i][j]*=n;
    }

    public double[][] matrizAdjunta(double[][] matriz){
        return matrizTranspuesta(matrizCofactores(matriz));
    }

    public double[][] matrizCofactores(double[][] matriz){
        double[][] nm=new double[matriz.length][matriz.length];
        for(int i=0;i<matriz.length;i++) {
            for(int j=0;j<matriz.length;j++) {
                double[][] det=new double[matriz.length-1][matriz.length-1];
                double detValor;
                for(int k=0;k<matriz.length;k++) {
                    if(k!=i) {
                        for(int l=0;l<matriz.length;l++) {
                            if(l!=j) {
                                int indice1=k<i ? k : k-1 ;
                                int indice2=l<j ? l : l-1 ;
                                det[indice1][indice2]=matriz[k][l];
                            }
                        }
                    }
                }
                detValor=determinante(det);
                nm[i][j]=detValor * (double)Math.pow(-1, i+j+2);
            }
        }
        return nm;
    }

    public double[][] matrizTranspuesta(double [][] matriz){
        double[][]nuevam=new double[matriz[0].length][matriz.length];
        for(int i=0; i<matriz.length; i++)
        {
            for(int j=0; j<matriz.length; j++)
                nuevam[i][j]=matriz[j][i];
        }
        return nuevam;
    }

    public double determinante(double[][] matriz)
    {
        double det;
        if(matriz.length==2)
        {
            det=(matriz[0][0]*matriz[1][1])-(matriz[1][0]*matriz[0][1]);
            return det;
        }
        double suma=0;
        for(int i=0; i<matriz.length; i++){
            double[][] nm=new double[matriz.length-1][matriz.length-1];
            for(int j=0; j<matriz.length; j++){
                if(j!=i){
                    for(int k=1; k<matriz.length; k++){
                        int indice=-1;
                        if(j<i)
                            indice=j;
                        else if(j>i)
                            indice=j-1;
                        nm[indice][k-1]=matriz[j][k];
                    }
                }
            }
            if(i%2==0)
                suma+=matriz[i][0] * determinante(nm);
            else
                suma-=matriz[i][0] * determinante(nm);
        }
        return suma;
    }
}
