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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class U2_Inversa extends AppCompatActivity {

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
        setContentView(R.layout.activity_u2__inversa);
        txtFilas = findViewById(R.id.txtInversaFila);
        txtColumnas = findViewById(R.id.txtInversaColumna);
        btnCrear = findViewById(R.id.btnInversaCrear);
        btnObtener = findViewById(R.id.btnInversa);
        recycler1 = findViewById(R.id.TABLAINVERSAecycler);
        Rrecycler = findViewById(R.id.InversaRecycler);
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtFilas.getText().toString().length()==0 || txtColumnas.getText().toString().length()==0 || Integer.parseInt(txtFilas.getText().toString())!=Integer.parseInt(txtColumnas.getText().toString())){
                    btnObtener.setEnabled(false);
                } else {
                    btnObtener.setEnabled(true);
                    filas = Integer.parseInt(txtFilas.getText().toString());
                    columnas = Integer.parseInt(txtColumnas.getText().toString());
                    cuadricula = filas * columnas;
                    listaDatos = new ArrayList<>();
                    for (int i = 0; i < cuadricula; i++) {
                        listaDatos.add("");
                    }
                    recycler1.setLayoutManager(new GridLayoutManager(U2_Inversa.this, columnas));
                    adapter = new AdapterDatosTabla(listaDatos, filas, columnas);
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
                        matriz[i][j] = Double.parseDouble(((EditText) rootView.findViewById(R.id.txtListaRecycler)).getText().toString());
                    }
                }
                try {
                    //Jama.Matrix matrix = new Jama.Matrix(matriz);
                    double[][] inversa = invert(matriz);/*matrix.inverse().getArray();*/
                    arrayList = new ArrayList<>();
                    for (int i = 0; i < matriz.length; i++) {
                        for (int x = 0; x < matriz[0].length; x++) {
                            arrayList.add(String.valueOf(MainActivity.decimalToFraction(inversa[i][x])));
                        }
                    }
                    Rrecycler.setLayoutManager(new GridLayoutManager(U2_Inversa.this, columnas));
                    adapterR = new AdapterDatosResultados(arrayList, filas, columnas);
                    Rrecycler.setAdapter(adapterR);
                }catch (Exception e){
                    Toast.makeText(U2_Inversa.this, "No se puede calcular esta matriz", Toast.LENGTH_SHORT).show();}
            }
        });
    }

    public static double[][] invert(double a[][])
    {
        int n = a.length;
        double x[][] = new double[n][n];
        double b[][] = new double[n][n];
        int index[] = new int[n];
        for(int i = 0; i < n; i++)
            b[i][i] = 1.0;

        gaussian(a, index);
        for(int i = 0; i < n - 1; i++)
        {
            for(int j = i + 1; j < n; j++)
            {
                for(int k = 0; k < n; k++)
                    b[index[j]][k] -= a[index[j]][i] * b[index[i]][k];

            }

        }

        for(int i = 0; i < n; i++)
        {
            x[n - 1][i] = b[index[n - 1]][i] / a[index[n - 1]][n - 1];
            for(int j = n - 2; j >= 0; j--)
            {
                x[j][i] = b[index[j]][i];
                for(int k = j + 1; k < n; k++)
                    x[j][i] -= a[index[j]][k] * x[k][i];

                x[j][i] /= a[index[j]][j];
            }

        }

        return x;
    }

    public static void gaussian(double a[][], int index[])
    {
        int n = index.length;
        double c[] = new double[n];
        for(int i = 0; i < n; i++)
            index[i] = i;

        for(int i = 0; i < n; i++)
        {
            double c1 = 0.0;
            for(int j = 0; j < n; j++)
            {
                double c0 = Math.abs(a[i][j]);
                if(c0 > c1)
                    c1 = c0;
            }

            c[i] = c1;
        }

        int k = 0;
        for(int j = 0; j < n - 1; j++)
        {
            double pi1 = 0.0;
            for(int i = j; i < n; i++)
            {
                double pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];
                if(pi0 > pi1)
                {
                    pi1 = pi0;
                    k = i;
                }
            }

            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for(int i = j + 1; i < n; i++)
            {
                double pj = a[index[i]][j] / a[index[j]][j];
                a[index[i]][j] = pj;
                for(int l = j + 1; l < n; l++)
                    a[index[i]][l] -= pj * a[index[j]][l];

            }

        }

    }

}
